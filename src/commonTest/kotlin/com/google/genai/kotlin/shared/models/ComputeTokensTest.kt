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

package com.google.genai.kotlin.shared.models

import com.google.genai.kotlin.shared.SHARED_MODEL
import com.google.genai.kotlin.shared.SharedTestBase
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class ComputeTokensTest : SharedTestBase() {
  /**
   * Canonical models/compute_tokens: test_compute_tokens.
   * exceptionIfMldev: "only supported in Gemini Enterprise Agent Platform".
   */
  @Test
  fun testComputeTokens() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.models.ComputeTokensTest.testComputeTokens.$suffix"
      val client = createClient(enterprise, testName)

      if (!enterprise) {
        // computeTokens is Agent Platform only; the Gemini API must reject it.
        val e =
          kotlin.runCatching { client.models.computeTokens(model = SHARED_MODEL, text = "The quick brown fox jumps over the lazy dog.") }
            .exceptionOrNull()
        assertNotNull(e, "Expected the Gemini API backend to reject computeTokens")
        assertTrue(
          e.message!!.contains("only supported in Gemini Enterprise Agent Platform"),
          "Unexpected message: ${e.message}",
        )
        return@forEach
      }

      runLive {
        val response = client.models.computeTokens(model = SHARED_MODEL, text = "The quick brown fox jumps over the lazy dog.")
        assertNotNull(response.tokensInfo)
      }
    }
  }
}
