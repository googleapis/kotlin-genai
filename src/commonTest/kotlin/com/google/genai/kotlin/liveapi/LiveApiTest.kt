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

/** One backend under test, and the live model it serves. */
private data class LiveBackend(
  val suffix: String,
  val model: String,
  val enterprise: Boolean,
  /**
   * Region to pin the enterprise client to, or null to take GOOGLE_CLOUD_LOCATION as-is. This also
   * selects the test-server proxy port, so it has to match the model's actual host.
   */
  val locationOverride: String? = null,
)

/**
 * The backends under test. Live models are backend specific, and both are audio-native and reject a
 * TEXT response modality, so these tests request AUDIO and enable output transcription.
 *
 * The Vertex model is not served on the global endpoint, where setup is rejected with 1008
 * "Publisher model ... was not found". It is available in us-central1, us-east5 and europe-west4,
 * so it is pinned to us-central1 -- which routes through proxy port 1454
 * (us-central1-aiplatform.googleapis.com) rather than the global 1455, exactly as LiveTest does for
 * the same model.
 */
private val LIVE_BACKENDS =
  listOf(
    LiveBackend("mldev", "gemini-3.1-flash-live-preview", enterprise = false),
    LiveBackend(
      "vertex",
      "gemini-live-2.5-flash-native-audio",
      enterprise = true,
      locationOverride = "us-central1",
    ),
  )

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

/*
 * These tests treat `turnComplete` alone as the end of a model turn, and deliberately not
 * `interrupted`, which reports that a *previous* generation was cut short and which Vertex emits
 * whenever new client content arrives. The Go, Java and JS live suites key on `turnComplete` too.
 */

class LiveApiTest : BaseTestServer() {

  /**
   * True when the running job has selected the other backend. Required, not cosmetic: each live job
   * only has credentials for its own backend. `kotlin.test` has no runtime skip, so callers return
   * early.
   */
  private fun skipBackend(enterprise: Boolean): Boolean {
    val vertexOnly = !System.getenv("GOOGLE_GENAI_RUN_VERTEX_ONLY_IN_API_MODE").isNullOrEmpty()
    val geminiOnly = !System.getenv("GOOGLE_GENAI_RUN_GEMINI_ONLY_IN_API_MODE").isNullOrEmpty()

    if (enterprise && geminiOnly) {
      println("   === Skipping Vertex AI live test (GEMINI ONLY config enabled)")
      return true
    }
    if (!enterprise && vertexOnly) {
      println("   === Skipping Gemini API live test (VERTEX ONLY config enabled)")
      return true
    }
    return false
  }

  @Test
  fun testTextInputProducesAudioAndTranscription() = runTest {
    LIVE_BACKENDS.forEach { backend ->
      if (skipBackend(backend.enterprise)) return@forEach
      val client =
        createClient(
          enterprise = backend.enterprise,
          testName = "LiveApiTest.testTextInput.${backend.suffix}",
          locationOverride = backend.locationOverride,
        )
      val session = client.live.connect(backend.model, liveConfig())

      session.sendClientContent(turns = userTurn("Say hello."))

      var audioBytes = 0
      val transcript = StringBuilder()
      session.receive().collect { message ->
        message.serverContent?.run {
          outputTranscription?.text?.let { transcript.append(it) }
          message.data?.let { audioBytes += it.size }
          if (turnComplete == true) {
            session.closeSession()
          }
        }
      }

      assertTrue(audioBytes > 0, "Expected audio output from the model (${backend.suffix})")
      assertTrue(
        transcript.toString().isNotBlank(),
        "Expected an output transcription (${backend.suffix})",
      )
    }
  }

  @Test
  fun testMultiTurnRetainsContext() = runTest {
    LIVE_BACKENDS.forEach { backend ->
      if (skipBackend(backend.enterprise)) return@forEach
      val client =
        createClient(
          enterprise = backend.enterprise,
          testName = "LiveApiTest.testMultiTurn.${backend.suffix}",
          locationOverride = backend.locationOverride,
        )
      val session = client.live.connect(backend.model, liveConfig())

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
          if (turnComplete == true) {
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

      assertTrue(
        firstTranscript.toString().isNotBlank(),
        "Expected a response to the first turn (${backend.suffix})",
      )
      assertTrue(
        secondTurnAudioBytes > 0,
        "Expected audio output on the second turn (${backend.suffix})",
      )
      assertTrue(
        secondTranscript.contains("42"),
        "Expected the second turn to recall context from the first (${backend.suffix}), " +
          "transcript was \"$secondTranscript\"",
      )
    }
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
    LIVE_BACKENDS.forEach { backend ->
      if (skipBackend(backend.enterprise)) return@forEach
      val client =
        createClient(
          enterprise = backend.enterprise,
          testName = "LiveApiTest.testFunctionCalling.${backend.suffix}",
          locationOverride = backend.locationOverride,
        )
      val session = client.live.connect(backend.model, liveConfig(tools = listOf(tool)))

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
          if (turnComplete == true) {
            if (respondedToTool) {
              session.closeSession()
            }
          }
        }
      }

      assertTrue(respondedToTool, "Expected the model to request the tool (${backend.suffix})")
      assertTrue(
        toolCallName == "turn_on_the_lights",
        "Unexpected tool call name (${backend.suffix}): $toolCallName",
      )
      // Both backends must accept the tool result and complete the turn, but only the Gemini API
      // returns assertable content: Vertex emits an empty transcription and no audio.
      if (!backend.enterprise) {
        assertTrue(
          followUpTranscript.toString().isNotBlank(),
          "Expected the model to respond after the tool result (${backend.suffix})",
        )
      }
    }
  }

  // No error-pathway test: this SDK does not validate FunctionResponse ids, and connecting with
  // a nonexistent model hangs rather than failing, so there is nothing to assert on until the SDK
  // propagates live setup failures.
}
