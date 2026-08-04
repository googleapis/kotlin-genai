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
import com.google.genai.kotlin.types.EmbedContentConfig
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class EmbedTest : SharedTestBase() {
  /** Canonical models/embed: test_embed (gemini-embedding-001). */
  @Test
  fun testEmbed() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.models.EmbedTest.testEmbed.$suffix"
      val client = createClient(enterprise, testName)

      runLive {
        val response =
          client.models.embedContent(
            model = "gemini-embedding-001",
            text = "Hello world!",
            config = EmbedContentConfig(outputDimensionality = 10),
          )
        assertNotNull(response.embeddings)
        assertTrue(response.embeddings!!.isNotEmpty(), "Expected at least one embedding")
      }
    }
  }

  /** Canonical models/embed: test_embed_gemini_embedding_2 (global-only model). */
  @Test
  fun testEmbedGeminiEmbedding2() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.models.EmbedTest.testEmbedGeminiEmbedding2.$suffix"
      val client = createClient(enterprise, testName)

      runLive {
        val response =
          client.models.embedContent(
            model = "gemini-embedding-2",
            text = "Hello world!",
            config = EmbedContentConfig(outputDimensionality = 10),
          )
        assertNotNull(response.embeddings)
        assertTrue(response.embeddings!!.isNotEmpty(), "Expected at least one embedding")
      }
    }
  }
}
