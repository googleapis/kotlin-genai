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

package com.google.genai.kotlin.liveapi

import com.google.genai.kotlin.BaseTestServer
import com.google.genai.kotlin.types.AudioTranscriptionConfig
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FunctionDeclaration
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.Modality
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.Schema
import com.google.genai.kotlin.types.Tool
import com.google.genai.kotlin.types.Type
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonPrimitive

/**
 * API-mode integration tests for the live (bidirectional WebSocket) module. These ship no
 * recordings and are excluded from replay mode; they reach the live service through the test-server
 * proxy. See go/genai-sdk:integration-testing.
 */

/**
 * The live model served on the Gemini API. It is audio-native and rejects a TEXT response modality,
 * so these tests request AUDIO and enable output transcription for an assertable text signal.
 */
private const val LIVE_MODEL = "gemini-3.1-flash-live-preview"

/** A live turn is an open-ended stream, so every test is bounded. */
private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 240.seconds, testBody = testBody)

private fun liveConfig(tools: List<Tool>? = null) =
  LiveConnectConfig(
    responseModalities = listOf(Modality.AUDIO),
    outputAudioTranscription = AudioTranscriptionConfig(),
    tools = tools,
  )

private fun userTurn(text: String) =
  listOf(Content(role = "user", parts = listOf(Part(text = text))))

class LiveApiTest : BaseTestServer() {

  @Test
  fun testTextInputProducesAudioAndTranscription() = runTest {
    val client = createClient(enterprise = false, testName = "LiveApiTest.testTextInput.mldev")
    val session = client.live.connect(LIVE_MODEL, liveConfig())

    session.sendClientContent(turns = userTurn("Say hello."))

    var audioBytes = 0
    val transcript = StringBuilder()
    session.receive().collect { message ->
      message.serverContent?.run {
        outputTranscription?.text?.let { transcript.append(it) }
        message.data?.let { audioBytes += it.size }
        if (turnComplete == true || interrupted == true) {
          session.closeSession()
        }
      }
    }

    assertTrue(audioBytes > 0, "Expected audio output from the model")
    assertTrue(transcript.toString().isNotBlank(), "Expected an output transcription")
  }

  @Test
  fun testMultiTurnRetainsContext() = runTest {
    val client = createClient(enterprise = false, testName = "LiveApiTest.testMultiTurn.mldev")
    val session = client.live.connect(LIVE_MODEL, liveConfig())

    session.sendClientContent(turns = userTurn("Remember the number 42. Just acknowledge it."))

    var turnsSeen = 0
    var secondTurnAudioBytes = 0
    val firstTranscript = StringBuilder()
    val secondTranscript = StringBuilder()

    session.receive().collect { message ->
      message.serverContent?.run {
        val transcript = if (turnsSeen == 0) firstTranscript else secondTranscript
        outputTranscription?.text?.let { transcript.append(it) }
        if (turnsSeen == 1) {
          message.data?.let { secondTurnAudioBytes += it.size }
        }
        if (turnComplete == true || interrupted == true) {
          turnsSeen++
          if (turnsSeen == 1) {
            // Ask the follow-up on the same session, then keep collecting.
            session.sendClientContent(turns = userTurn("What number did I ask you to remember?"))
          } else {
            session.closeSession()
          }
        }
      }
    }

    assertTrue(firstTranscript.toString().isNotBlank(), "Expected a response to the first turn")
    assertTrue(secondTurnAudioBytes > 0, "Expected audio output on the second turn")
    assertTrue(
      secondTranscript.contains("42"),
      "Expected the second turn to recall context from the first, transcript was " +
        "\"$secondTranscript\"",
    )
  }

  @Test
  fun testFunctionCallingCompletesRoundTrip() = runTest {
    val tool =
      Tool(
        functionDeclarations =
          listOf(
            FunctionDeclaration(
              name = "turn_on_the_lights",
              description = "Turns the lights on in the room.",
              parameters = Schema(type = Type.OBJECT, properties = emptyMap()),
            )
          )
      )
    val client =
      createClient(enterprise = false, testName = "LiveApiTest.testFunctionCalling.mldev")
    val session = client.live.connect(LIVE_MODEL, liveConfig(tools = listOf(tool)))

    session.sendClientContent(turns = userTurn("Please turn on the lights."))

    var toolCallName: String? = null
    var respondedToTool = false
    val followUpTranscript = StringBuilder()

    session.receive().collect { message ->
      val call = message.toolCall?.functionCalls?.firstOrNull()
      if (call != null && !respondedToTool) {
        toolCallName = call.name
        respondedToTool = true
        session.sendToolResponse(
          FunctionResponse(
            id = call.id,
            name = call.name,
            response = mapOf("result" to JsonPrimitive("ok")),
          )
        )
        return@collect
      }
      message.serverContent?.run {
        if (respondedToTool) {
          outputTranscription?.text?.let { followUpTranscript.append(it) }
        }
        if (turnComplete == true || interrupted == true) {
          if (respondedToTool) {
            session.closeSession()
          }
        }
      }
    }

    assertTrue(respondedToTool, "Expected the model to request the tool")
    assertTrue(toolCallName == "turn_on_the_lights", "Unexpected tool call name: $toolCallName")
    assertTrue(
      followUpTranscript.toString().isNotBlank(),
      "Expected the model to respond after the tool result",
    )
  }

  // No error-pathway test: this SDK does not validate FunctionResponse ids, and connecting with
  // a nonexistent model hangs rather than failing, so there is nothing to assert on until the SDK
  // propagates live setup failures.
}
