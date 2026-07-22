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

import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.Tool
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TransformerTest {
  private val vertexClient =
    ApiClient(
      apiKey = null,
      project = "test-project",
      location = "us-central1",
      credentials = null,
      enterprise = true,
    )

  private val mldevClient =
    ApiClient(
      apiKey = "fake-api-key",
      project = null,
      location = null,
      credentials = null,
      enterprise = false,
    )

  @Test
  fun testTContents_null() {
    assertNull(Transformers.tContents(null))
  }

  @Test
  fun testTContents_string() {
    val result = Transformers.tContents("Hello world") as List<*>
    assertEquals(1, result.size)
    val content = result[0] as Content
    assertEquals("Hello world", content.parts?.first()?.text)
  }

  @Test
  fun testTContents_contentObject() {
    val content = Content(parts = listOf(Part(text = "Hello")))
    val result = Transformers.tContents(content) as List<*>
    assertEquals(1, result.size)
    assertEquals(content, result[0])
  }

  @Test
  fun testTContents_passThrough() {
    val list = listOf(Content(parts = listOf(Part(text = "1"))))
    val result = Transformers.tContents(list)
    assertEquals(list, result)
  }

  @Test
  fun testTContent_null() {
    assertNull(Transformers.tContent(null))
  }

  @Test
  fun testTContent_string() {
    val result = Transformers.tContent("Hello") as Content
    assertEquals("Hello", result.parts?.first()?.text)
  }

  @Test
  fun testTContent_map() {
    val map = mapOf("parts" to listOf(mapOf("text" to "Mapped text")))
    val result = Transformers.tContent(map)
    assertEquals(map, result)
  }

  @Test
  fun testTSchema_map() {
    val map = mapOf("type" to "STRING", "description" to "A string")

    val result = Transformers.tSchema(map)

    kotlin.test.assertNotNull(result)
    assertEquals("STRING", result["type"])
    assertEquals("A string", result["description"])
  }

  @Test
  fun testTSchema_invalidType() {
    assertFailsWith<IllegalArgumentException> { Transformers.tSchema(listOf("invalid")) }
  }

  @Test
  fun testTSpeechConfig_string() {
    val result = Transformers.tSpeechConfig("Puck")
    kotlin.test.assertNotNull(result)
    val voiceConfig = result["voiceConfig"] as? Map<*, *>
    val prebuiltVoiceConfig = voiceConfig?.get("prebuiltVoiceConfig") as? Map<*, *>
    assertEquals("Puck", prebuiltVoiceConfig?.get("voiceName"))
  }

  @Test
  fun testTSpeechConfig_map() {
    val map = mapOf("voiceConfig" to mapOf("prebuiltVoiceConfig" to mapOf("voiceName" to "Aoede")))
    val result = Transformers.tSpeechConfig(map)
    kotlin.test.assertNotNull(result)
    val voiceConfig = result["voiceConfig"] as? Map<*, *>
    val prebuiltVoiceConfig = voiceConfig?.get("prebuiltVoiceConfig") as? Map<*, *>
    assertEquals("Aoede", prebuiltVoiceConfig?.get("voiceName"))
  }

  @Test
  fun testTSpeechConfig_invalidType() {
    assertFailsWith<IllegalArgumentException> { Transformers.tSpeechConfig(42.0) }
  }

  @Test
  fun testTTool_invalidType() {
    assertFailsWith<IllegalArgumentException> { Transformers.tTool("Not a tool") }
  }

  @Test
  fun testTTools_passThrough() {
    val tools = listOf(Tool())
    assertEquals(tools, Transformers.tTools(tools))
  }

  @Test
  fun testTModel_validationFailures() {
    assertFailsWith<IllegalArgumentException>("model is required.") {
      Transformers.tModel(vertexClient, null)
    }
    assertFailsWith<IllegalArgumentException>("model is required.") {
      Transformers.tModel(vertexClient, "")
    }
    assertFailsWith<IllegalArgumentException>("invalid model parameter.") {
      Transformers.tModel(vertexClient, "model..name")
    }
    assertFailsWith<IllegalArgumentException>("invalid model parameter.") {
      Transformers.tModel(vertexClient, "model?name=1")
    }
  }

  @Test
  fun testTModel_mldev() {
    assertEquals("models/gemini-3.5-flash", Transformers.tModel(mldevClient, "gemini-3.5-flash"))
    assertEquals("models/gemini", Transformers.tModel(mldevClient, "models/gemini"))
    assertEquals("tunedModels/my-model", Transformers.tModel(mldevClient, "tunedModels/my-model"))
  }

  @Test
  fun testTModel_vertex() {
    // Standard model
    assertEquals(
      "publishers/google/models/gemini-3.5-flash",
      Transformers.tModel(vertexClient, "gemini-3.5-flash"),
    )
    // Third-party publisher
    assertEquals(
      "publishers/anthropic/models/claude-3",
      Transformers.tModel(vertexClient, "anthropic/claude-3"),
    )
    // Already fully qualified
    assertEquals(
      "publishers/google/models/gemini",
      Transformers.tModel(vertexClient, "publishers/google/models/gemini"),
    )
    assertEquals(
      "projects/my-project/models/my-tuned",
      Transformers.tModel(vertexClient, "projects/my-project/models/my-tuned"),
    )
  }

  @Test
  fun testTCachesModel_mldev() {
    assertEquals(
      "models/gemini-3.5-flash",
      Transformers.tCachesModel(mldevClient, "gemini-3.5-flash"),
    )
    assertEquals("models/gemini", Transformers.tCachesModel(mldevClient, "models/gemini"))
  }

  @Test
  fun testTCachesModel_vertex() {
    val expectedPrefix = "projects/test-project/locations/us-central1"

    // Standard model without models/ or publishers/ prefix
    assertEquals(
      "$expectedPrefix/publishers/google/models/gemini-3.5-flash",
      Transformers.tCachesModel(vertexClient, "gemini-3.5-flash"),
    )

    // Starting with publishers/
    assertEquals(
      "$expectedPrefix/publishers/google/models/gemini-3.5-flash",
      Transformers.tCachesModel(vertexClient, "publishers/google/models/gemini-3.5-flash"),
    )

    // Starting with models/
    assertEquals(
      "$expectedPrefix/publishers/google/models/gemini-3.5-flash",
      Transformers.tCachesModel(vertexClient, "models/gemini-3.5-flash"),
    )
  }

  @Test
  fun testTCachedContentName_mldev() {
    // Should prepend collection if no slashes exist
    assertEquals(
      "cachedContents/my-cache",
      Transformers.tCachedContentName(mldevClient, "my-cache"),
    )
    // Should leave alone if collection is already there
    assertEquals(
      "cachedContents/my-cache",
      Transformers.tCachedContentName(mldevClient, "cachedContents/my-cache"),
    )
  }

  @Test
  fun testTCachedContentName_vertex() {
    val expectedPrefix = "projects/test-project/locations/us-central1"

    // Simple ID
    assertEquals(
      "$expectedPrefix/cachedContents/my-cache",
      Transformers.tCachedContentName(vertexClient, "my-cache"),
    )

    // Starting with prefix
    assertEquals(
      "$expectedPrefix/cachedContents/my-cache",
      Transformers.tCachedContentName(vertexClient, "cachedContents/my-cache"),
    )

    // Starting with locations/
    assertEquals(
      "projects/test-project/locations/us-central1/cachedContents/my-cache",
      Transformers.tCachedContentName(vertexClient, "locations/us-central1/cachedContents/my-cache"),
    )

    // Already fully qualified
    assertEquals(
      "projects/other-project/cachedContents/my-cache",
      Transformers.tCachedContentName(
        vertexClient,
        "projects/other-project/cachedContents/my-cache",
      ),
    )
  }

  @Test
  fun testTIsVertexEmbedContentModel() {
    assertEquals(true, Transformers.tIsVertexEmbedContentModel("gemini-embedding-2-preview"))
    assertEquals(false, Transformers.tIsVertexEmbedContentModel("gemini-embedding-001"))
    assertEquals(true, Transformers.tIsVertexEmbedContentModel("gemini-3.5-flash"))
    assertEquals(false, Transformers.tIsVertexEmbedContentModel("textembedding-gecko@001"))
  }

  @Test
  fun testTContentsForEmbed_mldev() {
    val result = Transformers.tContentsForEmbed(mldevClient, "Hello world") as List<*>
    assertEquals(1, result.size)
    val content = result[0] as Map<*, *>
    val parts = content["parts"] as List<*>
    val part = parts[0] as Map<*, *>
    assertEquals("Hello world", part["text"])
  }

  @Test
  fun testTContentsForEmbed_vertex() {
    val result = Transformers.tContentsForEmbed(vertexClient, "Hello world") as List<*>
    assertEquals(1, result.size)
    assertEquals("Hello world", result[0])

    val contentList = listOf(Content(parts = listOf(Part(text = "Hello content"))))
    val result2 = Transformers.tContentsForEmbed(vertexClient, contentList) as List<*>
    assertEquals(1, result2.size)
    assertEquals("Hello content", result2[0])
  }

  @Test
  fun testTFileName_null() {
    assertNull(Transformers.tFileName(null))
  }

  @Test
  fun testTFileName_string() {
    assertEquals("abc", Transformers.tFileName("abc"))
    assertEquals("abc", Transformers.tFileName("files/abc"))
    assertEquals("abc", Transformers.tFileName("https://generativelanguage.googleapis.com/v1beta/files/abc"))
  }

  @Test
  fun testTFileName_file() {
    val file = com.google.genai.kotlin.types.File(name = "files/abc")
    assertEquals("abc", Transformers.tFileName(file))
  }

  @Test
  fun testTFileName_fileWithoutName() {
    val file = com.google.genai.kotlin.types.File()
    assertFailsWith<IllegalArgumentException> { Transformers.tFileName(file) }
  }

  @Test
  fun testTFileName_invalidUri() {
    assertFailsWith<IllegalArgumentException> {
      Transformers.tFileName("https://generativelanguage.googleapis.com/v1beta/files/!!!")
    }
  }
}
