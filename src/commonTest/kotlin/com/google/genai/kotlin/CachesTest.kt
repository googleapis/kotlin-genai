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

import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CreateCachedContentConfig
import com.google.genai.kotlin.types.FileData
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.UpdateCachedContentConfig
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private const val MODEL_NAME = "gemini-3.5-flash"
private const val MLDEV_CACHED_CONTENT_NAME =
  "cachedContents/qfc05moswux9x0jtb8mct5ph1c2pvi978d7qsm3a"
private const val VERTEX_CACHED_CONTENT_NAME = "cachedContents/8046196551279706112"

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

class CachesTest : BaseTestServer() {

  @Test
  fun testCreateCachedContentWithInlineBytes() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "CachesTest.testCreateCachedContentWithInlineBytes.$suffix"

      val client = createClient(enterprise, testName)

      val bytesData =
        Part(
          inlineData =
            Blob(
              mimeType = "text/plain",
              data = "Hello Gemini ".repeat(100_000).encodeToByteArray(),
            )
        )

      val response =
        client.caches.create(
          model = MODEL_NAME,
          config =
            CreateCachedContentConfig(
              contents = listOf(Content(role = "user", parts = listOf(bytesData))),
              systemInstruction =
                Content(
                  role = "user",
                  parts = listOf(Part(text = "You are a pirate. Always reply in pirate speak.")),
                ),
              displayName = "cached content inline bytes",
              ttl = 86400.seconds,
            ),
        )

      assertTrue(response.name!!.contains("cachedContents/"))
      assertTrue(response.model!!.contains(MODEL_NAME))
      assertTrue(response.displayName == "cached content inline bytes")
    }
  }

  @Test
  fun testCreateCachedContentWithGcsFile() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "CachesTest.testCreateCachedContentWithGcsFile.$suffix"

      val client = createClient(enterprise, testName)

      val gcsFileData =
        Part(
          fileData =
            FileData(
              fileUri = "gs://cloud-samples-data/generative-ai/pdf/2403.05530.pdf",
              mimeType = "application/pdf",
            )
        )

      val config =
        CreateCachedContentConfig(
          contents = listOf(Content(role = "user", parts = listOf(gcsFileData))),
          systemInstruction =
            Content(
              role = "user",
              parts = listOf(Part(text = "You are a pirate. Always reply in pirate speak.")),
            ),
          displayName = "cached content gcs file",
          ttl = 86400.seconds,
        )

      if (enterprise) {
        val response = client.caches.create(model = MODEL_NAME, config = config)

        assertTrue(response.name!!.contains("cachedContents/"))
        assertTrue(response.model!!.contains(MODEL_NAME))
        assertTrue(response.displayName == "cached content gcs file")
      } else {
        // GCS URL is only supported in Gemini Enterprise API.
        try {
          client.caches.create(model = MODEL_NAME, config = config)
          assertTrue(false, "Expected ClientException")
        } catch (e: ClientException) {
          assertEquals(400, e.code)
        }
      }
    }
  }

  @Test
  fun testCreateCachedContentWithFileAPI() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "CachesTest.testCreateCachedContentWithFileAPI.$suffix"

      val client = createClient(enterprise, testName)

      val fileData =
        Part(
          fileData =
            FileData(
              fileUri = "https://generativelanguage.googleapis.com/v1beta/files/v7ha4im3cnwu",
              mimeType = "application/pdf",
            )
        )

      val config =
        CreateCachedContentConfig(
          contents = listOf(Content(role = "user", parts = listOf(fileData))),
          systemInstruction =
            Content(
              role = "user",
              parts = listOf(Part(text = "You are a pirate. Always reply in pirate speak.")),
            ),
          displayName = "cached content file API",
          ttl = 86400.seconds,
        )

      if (!enterprise) {
        val response = client.caches.create(model = MODEL_NAME, config = config)

        assertTrue(response.name!!.contains("cachedContents/"))
        assertTrue(response.model!!.contains(MODEL_NAME))
        assertTrue(response.displayName == "cached content file API")
      } else {
        // File API is only supported in Gemini API.
        try {
          client.caches.create(model = MODEL_NAME, config = config)
          assertTrue(false, "Expected ServerException")
        } catch (e: ServerException) {
          assertEquals(500, e.code)
        }
      }
    }
  }

  @Test
  fun testGetCachedContent() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "CachesTest.testGetCachedContent.$suffix"

      val client = createClient(enterprise, testName)

      val name = if (enterprise) VERTEX_CACHED_CONTENT_NAME else MLDEV_CACHED_CONTENT_NAME

      val response = client.caches.get(name = name)

      assertTrue(response.name!!.contains(name))
      assertTrue(response.model!!.contains(MODEL_NAME))
    }
  }

  @Test
  fun testUpdateCachedContent() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "CachesTest.testUpdateCachedContent.$suffix"

      val client = createClient(enterprise, testName)

      val name = if (enterprise) VERTEX_CACHED_CONTENT_NAME else MLDEV_CACHED_CONTENT_NAME

      val response =
        client.caches.update(name = name, config = UpdateCachedContentConfig(ttl = 7600.seconds))

      assertTrue(response.name!!.contains(name))
      assertTrue(response.model!!.contains(MODEL_NAME))
    }
  }

  @Test
  fun testDeleteCachedContent() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "CachesTest.testDeleteCachedContent.$suffix"

      val client = createClient(enterprise, testName)

      val name = if (enterprise) VERTEX_CACHED_CONTENT_NAME else MLDEV_CACHED_CONTENT_NAME

      client.caches.delete(name = name)
    }
  }
}
