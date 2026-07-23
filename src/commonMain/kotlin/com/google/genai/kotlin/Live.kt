/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.kotlin

import com.google.genai.kotlin.types.ActivityEnd
import com.google.genai.kotlin.types.ActivityStart
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.GenerationConfig
import com.google.genai.kotlin.types.LiveClientContent
import com.google.genai.kotlin.types.LiveClientMessage
import com.google.genai.kotlin.types.LiveClientRealtimeInput
import com.google.genai.kotlin.types.LiveClientSetup
import com.google.genai.kotlin.types.LiveClientToolResponse
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.LiveServerMessage
import com.google.genai.kotlin.types.Modality
import com.google.genai.kotlin.types.Part
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readBytes
import io.ktor.websocket.readText
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/** The Live API client for establishing WebSocket connections to Gemini models. */
class Live internal constructor(private val apiClient: ApiClient) {

  /**
   * Connects to the Live API and establishes a WebSocket session.
   *
   * @param model The model to connect to (e.g. "gemini-3.1-flash-live-preview").
   * @param config Optional [LiveConnectConfig] for connection configuration.
   * @return A [LiveSession] representing the active connection.
   */
  suspend fun connect(model: String, config: LiveConnectConfig? = null): LiveSession {
    var transformedModel = Transformers.tModel(apiClient, model)
    if (
      apiClient.enterprise && apiClient.apiKey == null && !transformedModel.startsWith("projects/")
    ) {
      val project =
        apiClient.project
          ?: throw IllegalStateException(
            "project is required for Gemini Enterprise Agent Platform Live API."
          )
      val location =
        apiClient.location
          ?: throw IllegalStateException(
            "location is required for Gemini Enterprise Agent Platform Live API."
          )
      transformedModel = "projects/$project/locations/$location/$transformedModel"
    }
    val session = apiClient.openWebSocketSession(config?.httpOptions)
    val liveSession = LiveSession(session, apiClient)

    // Perform initial setup
    val generationConfig =
      GenerationConfig(
        // Fallback to Audio modality if not specified by users. This aligns with the Python SDK
        // behavior.
        responseModalities = config?.responseModalities ?: listOf(Modality.AUDIO),
        temperature = config?.temperature,
        topP = config?.topP,
        topK = config?.topK,
        maxOutputTokens = config?.maxOutputTokens,
        mediaResolution = config?.mediaResolution,
        seed = config?.seed,
        speechConfig = config?.speechConfig,
        thinkingConfig = config?.thinkingConfig,
      )
    val setup =
      LiveClientSetup(
        model = transformedModel,
        generationConfig = generationConfig,
        systemInstruction = config?.systemInstruction,
        tools = config?.tools,
        sessionResumption = config?.sessionResumption,
        contextWindowCompression = config?.contextWindowCompression,
        inputAudioTranscription = config?.inputAudioTranscription,
        outputAudioTranscription = config?.outputAudioTranscription,
        proactivity = config?.proactivity,
        explicitVadSignal = config?.explicitVadSignal,
        avatarConfig = config?.avatarConfig,
        safetySettings = config?.safetySettings,
      )
    liveSession.send(LiveClientMessage(setup = setup))

    return liveSession
  }
}

/** Represents an active WebSocket session with the Live API. */
class LiveSession
internal constructor(
  private val session: DefaultClientWebSocketSession,
  private val apiClient: ApiClient,
) : AutoCloseable {

  /**
   * Receives a flow of messages from the server.
   *
   * @return A [Flow] of [LiveServerMessage] objects.
   * @throws GenAiApiException if the connection is closed abnormally.
   */
  fun receive(): Flow<LiveServerMessage> = flow {
    try {
      for (frame in session.incoming) {
        val jsonStr =
          when (frame) {
            is Frame.Text -> frame.readText()
            is Frame.Binary -> frame.readBytes().decodeToString()
            else -> continue
          }
        val responseMap = Common.jsonStringToMap(jsonStr)
        val convertedMap =
          if (apiClient.enterprise) {
            LiveConverters.liveServerMessageFromVertex(responseMap, null)
          } else {
            LiveConverters.liveServerMessageFromMldev(responseMap, null)
          }
        val message = Common.mapToDataClass<LiveServerMessage>(convertedMap)
        emit(message)
      }
      val finalCloseReason = session.closeReason.await()
      if (finalCloseReason != null && finalCloseReason.code != CloseReason.Codes.NORMAL.code) {
        throw GenAiApiException(
          finalCloseReason.code.toInt(),
          "ConnectionClosed",
          finalCloseReason.message,
        )
      }
    } catch (e: Exception) {
      val closeReason = session.closeReason.await()
      if (closeReason != null && closeReason.code != CloseReason.Codes.NORMAL.code) {
        throw GenAiApiException(closeReason.code.toInt(), "ConnectionClosed", closeReason.message)
      }
      throw e
    }
  }

  /** Sends a generic [LiveClientMessage] to the server. */
  internal suspend fun send(message: LiveClientMessage) {
    val parameterMap = Common.dataClassToMap(message)
    val convertedMap =
      if (apiClient.enterprise) {
        LiveConverters.liveClientMessageToVertex(parameterMap, null)
      } else {
        LiveConverters.liveClientMessageToMldev(parameterMap, null)
      }
    val jsonStr = Common.mapToJsonObject(convertedMap).toString()
    session.send(Frame.Text(jsonStr))
  }

  /**
   * Sends standard client content (text, blobs) to the model.
   *
   * @param turns The client content to send.
   * @param turnComplete Indicates whether the turn is complete. Defaults to true.
   */
  suspend fun sendClientContent(turns: List<Content>? = null, turnComplete: Boolean = true) {
    val message =
      LiveClientMessage(
        clientContent = LiveClientContent(turns = turns, turnComplete = turnComplete)
      )
    send(message)
  }

  /**
   * Sends standard client content (text, blobs) to the model.
   *
   * @param turn The client content to send.
   * @param turnComplete Indicates whether the turn is complete. Defaults to true.
   */
  suspend fun sendClientContent(turn: Content, turnComplete: Boolean = true) {
    sendClientContent(listOf(turn), turnComplete)
  }

  /**
   * Sends standard client content as text to the model.
   *
   * @param text The string content to send.
   * @param turnComplete Indicates whether the turn is complete. Defaults to true.
   */
  suspend fun sendClientContent(text: String, turnComplete: Boolean = true) {
    sendClientContent(listOf(Content(parts = listOf(Part(text = text)))), turnComplete)
  }

  /**
   * Sends real-time input (audio, video, etc.) to the model.
   *
   * Exactly one of the parameters should be set.
   *
   * @param media A [Blob] containing the media data (e.g. audio, video) to send.
   * @param audio A [Blob] containing audio data to send.
   * @param audioStreamEnd A boolean flag indicating the end of an audio stream.
   * @param video A [Blob] containing video data to send.
   * @param text A string containing text to send.
   * @param activityStart An [ActivityStart] indicator to signify the beginning of an activity.
   * @param activityEnd An [ActivityEnd] indicator to signify the conclusion of an activity.
   */
  suspend fun sendRealtimeInput(
    media: Blob? = null,
    audio: Blob? = null,
    audioStreamEnd: Boolean? = null,
    video: Blob? = null,
    text: String? = null,
    activityStart: ActivityStart? = null,
    activityEnd: ActivityEnd? = null,
  ) {
    val nonNullFields =
      listOfNotNull(media, audio, audioStreamEnd, video, text, activityStart, activityEnd).size
    require(nonNullFields == 1) {
      "Exactly one of the parameters (media, audio, audioStreamEnd, video, text, activityStart, activityEnd) must be set."
    }
    val input =
      LiveClientRealtimeInput(
        mediaChunks = media?.let { listOf(it) },
        audio = audio,
        audioStreamEnd = audioStreamEnd,
        video = video,
        text = text,
        activityStart = activityStart,
        activityEnd = activityEnd,
      )
    val message = LiveClientMessage(realtimeInput = input)
    send(message)
  }

  /**
   * Sends a tool response to the model after executing a tool call.
   *
   * @param functionResponses The function responses to send.
   */
  suspend fun sendToolResponse(functionResponses: List<FunctionResponse>) {
    if (!apiClient.enterprise) {
      functionResponses.forEach { functionResponse ->
        requireNotNull(functionResponse.id) {
          "The 'id' field in FunctionResponse must be populated when using the Gemini Developer API."
        }
      }
    }
    val response = LiveClientToolResponse(functionResponses = functionResponses)
    val message = LiveClientMessage(toolResponse = response)
    send(message)
  }

  /**
   * Forcefully closes the WebSocket session synchronously.
   *
   * This method abruptly cancels the connection without a proper WebSocket close handshake. It is
   * provided to satisfy the [AutoCloseable] interface. For a clean, graceful shutdown, prefer using
   * the suspending [closeSession] method.
   */
  override fun close() {
    session.cancel()
  }

  /**
   * Gracefully closes the WebSocket session.
   *
   * This is a suspending method that performs a proper WebSocket closing handshake, ensuring the
   * server is notified that the connection is ending cleanly.
   */
  suspend fun closeSession() {
    session.close()
  }
}
