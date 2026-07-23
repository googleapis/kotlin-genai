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

import com.google.genai.kotlin.types.ActivityStart
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.LiveServerMessage
import com.google.genai.kotlin.types.Modality
import com.google.genai.kotlin.types.Part
import java.io.EOFException
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
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

private fun runLongTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 300.seconds, testBody = testBody)

class LiveTest : BaseTestServer() {

  @Test
  fun testTextInputSimple() = runLongTest {
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
          isAudioReceived = true
          if (turnComplete == true || interrupted == true) {
            isTurnComplete = true
            session.closeSession()
          }
        }
      }

      assertTrue(isSetupComplete)
      assertTrue(isAudioReceived)
      assertTrue(isTurnComplete)
    }
  }
}
