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

package com.google.genai.kotlin.shared.caches

import com.google.genai.kotlin.shared.SHARED_MODEL
import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CreateCachedContentConfig
import com.google.genai.kotlin.types.FileData
import com.google.genai.kotlin.types.Part
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class CreateGetDeleteTest : SharedTestBase() {
  private fun gcsContents() =
    List(5) {
      Content(
        role = "user",
        parts =
          listOf(
            Part(
              fileData =
                FileData(
                  fileUri = "gs://cloud-samples-data/generative-ai/image/a-man-and-a-dog.png",
                  mimeType = "image/png",
                )
            )
          ),
      )
    }

  // The Gemini API does not accept gs:// URIs, so the mldev half uses inline bytes.
  private fun inlineContents() =
    listOf(
      Content(
        role = "user",
        parts =
          listOf(
            Part(
              inlineData =
                Blob(mimeType = "text/plain", data = "Hello Gemini ".repeat(100_000).encodeToByteArray())
            )
          ),
      )
    )

  private fun cacheConfig(enterprise: Boolean) =
    CreateCachedContentConfig(
      contents = if (enterprise) gcsContents() else inlineContents(),
      ttl = 7200.seconds,
    )

  /** Canonical caches/create_get_delete: test_create_get_delete. */
  @Test
  fun testCreateGetDelete() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.caches.CreateGetDeleteTest.testCreateGetDelete.$suffix"
      val client = createClient(enterprise, testName)

      runLive {
        val cache = client.caches.create(model = SHARED_MODEL, config = cacheConfig(enterprise))
        assertNotNull(cache.name)

        // Delete in a finally: a cached content resource is billed for its full TTL.
        try {
          assertTrue(cache.name!!.contains("cachedContents/"))
          val got = client.caches.get(name = cache.name!!)
          assertEquals(cache.name, got.name)
        } finally {
          tryCleanup { client.caches.delete(name = cache.name!!) }
        }
      }
    }
  }
}
