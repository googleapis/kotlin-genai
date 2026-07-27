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

import com.google.genai.kotlin.types.AudioTranscriptionConfig
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FunctionDeclaration
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.GoogleSearch
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.LiveServerMessage
import com.google.genai.kotlin.types.Modality
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.Schema
import com.google.genai.kotlin.types.Tool
import com.google.genai.kotlin.types.Type
import java.io.EOFException
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonPrimitive

private const val GEMINI_MODEL_NAME = "gemini-3.1-flash-live-preview"
private const val VERTEX_MODEL_NAME = "gemini-live-2.5-flash-native-audio"

private suspend fun Flow<LiveServerMessage>.collectSafely(
  action: suspend (value: LiveServerMessage) -> Unit
) {
  try {
    collect(action)
  } catch (e: EOFException) {
    // test-server abruptly closes the connection after replaying all frames, which throws
    // EOFException
  }
}

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

class LiveTest : BaseTestServer() {

  @Test
  fun testTextInputSimple() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testTextInputSimple.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )
      val session = client.live.connect(modelName)

      session.sendRealtimeInput(text = "Hello what should we talk about?")

      var isSetupComplete = false
      var isAudioReceived = false
      var isTurnComplete = false

      session.receive().collectSafely { serverMessage ->
        serverMessage.setupComplete?.let { isSetupComplete = true }
        serverMessage.serverContent?.run {
          if (serverMessage.data != null) {
            isAudioReceived = true
          }
          if (turnComplete == true || interrupted == true) {
            isTurnComplete = true
            session.closeSession()
          }
        }
      }

      assertTrue(isSetupComplete, "Expected to receive setupComplete")
      assertTrue(isAudioReceived, "Expected to receive audio inlineData")
      assertTrue(isTurnComplete, "Expected turn to complete")
    }
  }

  @Test
  fun testAudioInputSimple() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testAudioInputSimple.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )
      val session = client.live.connect(modelName)

      session.sendRealtimeInput(
        audio =
          Blob(
            mimeType = "audio/pcm;rate=16000",
            data = readTestResource("hello_are_you_there.pcm"),
          )
      )
      if (!enterprise) {
        session.sendRealtimeInput(audioStreamEnd = true)
      }

      var isSetupComplete = false
      var isAudioReceived = false
      var isTurnComplete = false

      session.receive().collectSafely { serverMessage ->
        serverMessage.setupComplete?.let { isSetupComplete = true }

        serverMessage.serverContent?.run {
          if (serverMessage.data != null) {
            isAudioReceived = true
          }
          if (turnComplete == true || interrupted == true) {
            isTurnComplete = true
            session.closeSession()
          }
        }
      }

      assertTrue(isSetupComplete, "Expected to receive setupComplete")
      assertTrue(isAudioReceived, "Expected to receive audio inlineData")
      assertTrue(isTurnComplete, "Expected turn to complete")
    }
  }

  @Test
  fun testVideoInputSimple() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testVideoInputSimle.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )
      val session = client.live.connect(modelName)

      session.sendRealtimeInput(text = "What is in this image?")
      session.sendRealtimeInput(
        video = Blob(mimeType = "image/jpeg", data = readTestResource("google.jpg"))
      )

      var isSetupComplete = false
      var finalTurnComplete = false

      session.receive().collectSafely { serverMessage ->
        serverMessage.setupComplete?.let { isSetupComplete = true }

        serverMessage.serverContent?.run {
          if (turnComplete == true || interrupted == true) {
            finalTurnComplete = true
            session.closeSession()
          }
        }
      }

      assertTrue(isSetupComplete, "Expected to receive setupComplete")
      assertTrue(finalTurnComplete, "Expected final turn to complete")
    }
  }

  @Test
  fun testMultiTurnConversation() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testMultiTurnConversation.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )
      val session = client.live.connect(modelName)

      session.sendRealtimeInput(
        audio =
          Blob(
            mimeType = "audio/pcm;rate=16000",
            data = readTestResource("hello_are_you_there.pcm"),
          )
      )
      if (!enterprise) {
        session.sendRealtimeInput(audioStreamEnd = true)
      }

      var isSetupComplete = false
      var isAudioReceived = false
      var turnsCompleted = 0

      session.receive().collectSafely { serverMessage ->
        serverMessage.setupComplete?.let { isSetupComplete = true }

        serverMessage.serverContent?.run {
          if (serverMessage.data != null) {
            isAudioReceived = true
          }
          if (turnComplete == true || interrupted == true) {
            turnsCompleted++
            if (turnsCompleted == 1) {
              session.sendRealtimeInput(text = "Introduce Google in 2 sentences.")
            } else if (turnsCompleted == 2) {
              session.closeSession()
            }
          }
        }
      }

      assertTrue(isSetupComplete, "Expected to receive setupComplete")
      assertTrue(isAudioReceived, "Expected to receive audio inlineData")
      assertTrue(turnsCompleted == 2, "Expected 2 turns to complete")
    }
  }

  @Test
  fun testFunctionCalling() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testFunctionCalling.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )

      val getWeatherDeclaration =
        FunctionDeclaration(
          name = "GetWeather",
          description = "return the real time weather of the location",
          parameters =
            Schema(
              type = Type.OBJECT,
              properties = mapOf("location" to Schema(type = Type.STRING)),
              required = listOf("location"),
            ),
        )

      val config =
        LiveConnectConfig(
          tools = listOf(Tool(functionDeclarations = listOf(getWeatherDeclaration)))
        )

      val session = client.live.connect(modelName, config)

      session.sendRealtimeInput(text = "What is the weather in Seattle?")

      var isSetupComplete = false
      var toolCallReceived = false
      var toolResponseSent = false
      var finalTurnComplete = false

      session.receive().collectSafely { serverMessage ->
        serverMessage.setupComplete?.let { isSetupComplete = true }

        serverMessage.toolCall?.let { toolCall ->
          toolCallReceived = true
          val functionResponses =
            toolCall.functionCalls?.map { call ->
              FunctionResponse(
                id = call.id,
                name = call.name,
                response = mapOf("temperature" to JsonPrimitive("72F")),
              )
            }
          if (functionResponses != null) {
            session.sendToolResponse(functionResponses)
            toolResponseSent = true
          }
        }

        serverMessage.serverContent?.run {
          if (turnComplete == true || interrupted == true) {
            if (toolResponseSent) {
              finalTurnComplete = true
              session.closeSession()
            }
          }
        }
      }

      assertTrue(isSetupComplete, "Expected to receive setupComplete")
      assertTrue(toolCallReceived, "Expected to receive toolCall")
      assertTrue(toolResponseSent, "Expected to send toolResponse")
      assertTrue(finalTurnComplete, "Expected final turn to complete")
    }
  }

  @Test
  fun testSendFunctionResponseNoIdThrowError() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testSendFunctionResponseNoIdThrowError.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )

      val session = client.live.connect(modelName)

      val exception =
        assertFailsWith<IllegalArgumentException> {
          session.sendToolResponse(
            FunctionResponse(name = "name", response = mapOf("temperature" to JsonPrimitive("72F")))
          )
        }
      assertTrue(
        exception.message!!.contains("The 'id' field in FunctionResponse must be populated")
      )

      session.closeSession()
    }
  }

  @Test
  fun testGoogleSearchToolAndSystemInstruction() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testGoogleSearchToolAndSystemInstruction.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )

      val config =
        LiveConnectConfig(
          tools = listOf(Tool(googleSearch = GoogleSearch())),
          systemInstruction =
            Content(
              parts =
                listOf(
                  Part(text = "When you search on Google, always saying your searching criteria.")
                )
            ),
        )

      val session = client.live.connect(modelName, config)

      session.sendRealtimeInput(text = "Search for recent news about Google.")

      var isSetupComplete = false
      var finalTurnComplete = false
      var hasGroundingMetadata = false

      session.receive().collectSafely { serverMessage ->
        serverMessage.setupComplete?.let { isSetupComplete = true }

        serverMessage.serverContent?.run {
          groundingMetadata?.let { hasGroundingMetadata = true }
          if (turnComplete == true || interrupted == true) {
            finalTurnComplete = true
            session.closeSession()
          }
        }
      }

      assertTrue(isSetupComplete, "Expected to receive setupComplete")
      assertTrue(finalTurnComplete, "Expected final turn to complete")
      assertTrue(
        hasGroundingMetadata,
        "Expected to have grounding metadata for GoogleSearch Tool usage",
      )
    }
  }

  @Test
  fun testAudioInputAndOutputTranscriptions() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testAudioInputAndOutputTranscriptions.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )

      val config =
        LiveConnectConfig(
          inputAudioTranscription = AudioTranscriptionConfig(),
          outputAudioTranscription = AudioTranscriptionConfig(),
          responseModalities = listOf(Modality.AUDIO),
        )
      val session = client.live.connect(modelName, config)

      session.sendRealtimeInput(
        audio =
          Blob(
            mimeType = "audio/pcm;rate=16000",
            data = readTestResource("hello_are_you_there.pcm"),
          )
      )
      if (!enterprise) {
        session.sendRealtimeInput(audioStreamEnd = true)
      }

      var hasInputTranscription = false
      var hasOutputTranscription = false
      var isTurnComplete = false

      session.receive().collectSafely { serverMessage ->
        serverMessage.serverContent?.run {
          inputTranscription?.let { hasInputTranscription = true }
          outputTranscription?.let { hasOutputTranscription = true }

          if (turnComplete == true || interrupted == true) {
            isTurnComplete = true
            session.closeSession()
          }
        }
      }

      assertTrue(isTurnComplete, "Expected turn to complete")
      assertTrue(hasInputTranscription, "Expected to receive input transcription")
      assertTrue(hasOutputTranscription, "Expected to receive output transcription")
    }
  }

  @Test
  fun testSendRealtimeInputInvalidParametersThrowError() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "LiveTest.testSendRealtimeInputInvalidParametersThrowError.$suffix"
      val locationOverride = if (enterprise) "us-central1" else null
      val modelName = if (enterprise) VERTEX_MODEL_NAME else GEMINI_MODEL_NAME

      val client =
        createClient(
          enterprise = enterprise,
          testName = testName,
          locationOverride = locationOverride,
        )

      val session = client.live.connect(modelName)

      val exceptionZero = assertFailsWith<IllegalArgumentException> { session.sendRealtimeInput() }
      assertTrue(exceptionZero.message!!.contains("Exactly one of the parameters"))

      val exceptionMulti =
        assertFailsWith<IllegalArgumentException> {
          session.sendRealtimeInput(text = "Hello", audioStreamEnd = true)
        }
      assertTrue(exceptionMulti.message!!.contains("Exactly one of the parameters"))

      session.closeSession()
    }
  }
}
