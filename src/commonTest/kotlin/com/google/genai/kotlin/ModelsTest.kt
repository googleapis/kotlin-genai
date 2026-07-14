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
import com.google.genai.kotlin.types.EmbedContentConfig
import com.google.genai.kotlin.types.FileData
import com.google.genai.kotlin.types.FunctionDeclaration
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.GoogleSearch
import com.google.genai.kotlin.types.HarmBlockThreshold
import com.google.genai.kotlin.types.HarmCategory
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.PrebuiltVoiceConfig
import com.google.genai.kotlin.types.SafetySetting
import com.google.genai.kotlin.types.Schema
import com.google.genai.kotlin.types.SpeechConfig
import com.google.genai.kotlin.types.ThinkingConfig
import com.google.genai.kotlin.types.ThinkingLevel
import com.google.genai.kotlin.types.Tool
import com.google.genai.kotlin.types.Type
import com.google.genai.kotlin.types.VoiceConfig
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private const val MODEL_NAME = "gemini-3-flash-preview"
private const val AUDIO_MODEL_NAME = "gemini-3.1-flash-tts-preview"
private const val IMAGE_MODEL_NAME = "gemini-3.1-flash-image-preview"

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

private fun runLongTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 300.seconds, testBody = testBody)

class ModelsTest : BaseTestServer() {

  @Test
  fun testGenerateContentSimple() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentSimple.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(model = MODEL_NAME, text = "What is the capital of France?")

      assertContains(response.text ?: "", "Paris")
    }
  }

  @Test
  fun testGenerateContentStreamSimple() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentStreamSimple.$suffix"

      val client = createClient(enterprise, testName)

      val flow =
        client.models.generateContentStream(
          model = MODEL_NAME,
          text = "Tell me a story about Paris in 200 words.",
        )

      var fullResponse = ""
      flow.collect { response -> fullResponse += response.text ?: "" }

      assertContains(fullResponse, "Paris")
    }
  }

  @Test
  fun testGenerateContentMultipleParts() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentMultipleParts.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          content =
            Content(
              role = "user",
              parts = listOf(Part(text = "Hello, "), Part(text = "what is the capital of France?")),
            ),
        )

      assertNotNull(response.text)
      assertContains(response.text!!, "Paris")
    }
  }

  @Test
  fun testGenerateContentGoogleSearch() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentGoogleSearch.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "What's the weather like in Melbourne?",
          config = GenerateContentConfig(tools = listOf(Tool(googleSearch = GoogleSearch()))),
        )

      assertNotNull(response.text)
      assertNotNull(response.candidates!![0].groundingMetadata)
    }
  }

  @Test
  fun testGenerateContentStreamGoogleSearch() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentStreamGoogleSearch.$suffix"

      val client = createClient(enterprise, testName)

      val flow =
        client.models.generateContentStream(
          model = MODEL_NAME,
          text = "What's the weather like in Melbourne?",
          config = GenerateContentConfig(tools = listOf(Tool(googleSearch = GoogleSearch()))),
        )

      var foundGroundingMetadata = false
      flow.collect { chunk ->
        chunk.candidates?.firstOrNull()?.groundingMetadata?.let { foundGroundingMetadata = true }
      }
      assertTrue(foundGroundingMetadata)
    }
  }

  @Test
  fun testGenerateContentWrongModelName() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWrongModelName.$suffix"

      val client = createClient(enterprise, testName)

      val exception =
        assertFailsWith<ClientException> {
          client.models.generateContent(
            model = "wrong-model-name",
            text = "What is the capital of France?",
          )
        }

      assertContains(exception.message ?: "", "wrong-model-name")
      assertEquals(404, exception.code)
    }
  }

  @Test
  fun testGenerateContentResponseSchema() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentResponseSchema.$suffix"

      val client = createClient(enterprise, testName)

      val countryInfoSchema =
        Schema(
          type = Type.OBJECT,
          properties =
            mapOf(
              "title" to Schema(type = Type.STRING),
              "population" to Schema(type = Type.INTEGER),
              "capital" to Schema(type = Type.STRING),
            ),
          required = listOf("title", "population", "capital"),
        )

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "Give me information about Australia",
          config =
            GenerateContentConfig(
              responseMimeType = "application/json",
              responseSchema = countryInfoSchema,
            ),
        )

      val text = response.text ?: ""
      assertContains(text, "{")
      assertContains(text, "}")
      assertContains(text, "Australia")
    }
  }

  @Test
  fun testGenerateContentEnumConstraint() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentEnumConstraint.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = "gemini-3.5-flash",
          text = "What is your favorite ice cream flavor among chocolate, vanilla, and strawberry?",
          config =
            GenerateContentConfig(
              responseMimeType = "application/json",
              responseSchema =
                Schema(type = Type.STRING, enum = listOf("chocolate", "vanilla", "strawberry")),
            ),
        )

      assertNotNull(response.text)
      assertTrue(
        response.text!!.contains("chocolate") ||
          response.text!!.contains("vanilla") ||
          response.text!!.contains("strawberry")
      )
    }
  }

  @Test
  fun testGenerateContentResponseSchemaWithDefault() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentResponseSchemaWithDefault.$suffix"

      val client = createClient(enterprise, testName)

      val restaurantInfoSchema =
        Schema(
          type = Type.OBJECT,
          properties =
            mapOf(
              "name" to Schema(type = Type.STRING),
              "city" to
                Schema(
                  type = Type.STRING,
                  default = kotlinx.serialization.json.JsonPrimitive("New York"),
                ),
            ),
          required = listOf("name", "city"),
        )

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "Can you recommend a restaurant for me in New York?",
          config =
            GenerateContentConfig(
              responseMimeType = "application/json",
              responseSchema = restaurantInfoSchema,
            ),
        )

      assertNotNull(response.text)
      assertContains(response.text!!, "New York")
    }
  }

  @Test
  fun testGenerateContentWithSpeechConfig() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithSpeechConfig.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = AUDIO_MODEL_NAME,
          text = "Produce a speech response saying \"Cheese\"",
          config =
            GenerateContentConfig(
              responseModalities = listOf("audio"),
              speechConfig =
                SpeechConfig(
                  voiceConfig =
                    VoiceConfig(prebuiltVoiceConfig = PrebuiltVoiceConfig(voiceName = "charon"))
                ),
            ),
        )

      assertNotNull(response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.inlineData)
    }
  }

  @Test
  fun testGenerateContentResponseModalityImage() = runLongTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentResponseModalityImage.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = IMAGE_MODEL_NAME,
          text = "Can you introduce cat and generate a small image of it?",
          config = GenerateContentConfig(responseModalities = listOf("TEXT", "IMAGE")),
        )

      val parts = response.candidates?.firstOrNull()?.content?.parts
      assertNotNull(parts)
      assertTrue(
        parts.any { it.inlineData != null && it.inlineData?.mimeType?.contains("image") == true }
      )
      assertTrue(parts.any { it.text != null })
    }
  }

  @Test
  fun testGenerateContentStreamResponseModalityImage() = runLongTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentStreamResponseModalityImage.$suffix"

      val client = createClient(enterprise, testName)

      val flow =
        client.models.generateContentStream(
          model = IMAGE_MODEL_NAME,
          text = "Can you introduce cat and generate a small image of it?",
          config = GenerateContentConfig(responseModalities = listOf("TEXT", "IMAGE")),
        )

      val allParts = mutableListOf<Part>()
      flow.collect { response ->
        response.candidates?.firstOrNull()?.content?.parts?.let { allParts.addAll(it) }
      }

      assertTrue(allParts.isNotEmpty())
      assertTrue(
        allParts.any { it.inlineData != null && it.inlineData?.mimeType?.contains("image") == true }
      )
      assertTrue(allParts.any { it.text != null })
    }
  }

  @Test
  fun testGenerateContentSafetySettings() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentSafetySettings.$suffix"

      val client = createClient(enterprise, testName)

      val safetySettings =
        listOf(
          SafetySetting(
            category = HarmCategory.HARM_CATEGORY_HATE_SPEECH,
            threshold = HarmBlockThreshold.BLOCK_LOW_AND_ABOVE,
          )
        )

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "What hate speech is prohibited by responsible AI?",
          config = GenerateContentConfig(safetySettings = safetySettings),
        )

      assertNotNull(response.candidates)
      assertNotNull(response.candidates!![0].safetyRatings)
    }
  }

  @Test
  fun testGenerateContentWithSystemInstruction() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithSystemInstruction.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "Say hello",
          config =
            GenerateContentConfig(
              systemInstruction =
                Content(
                  parts = listOf(Part(text = "You are a pirate. Always reply in pirate speak."))
                )
            ),
        )

      val text = response.text ?: ""
      assertTrue(
        text.contains("Ahoy", ignoreCase = true) || text.contains("Matey", ignoreCase = true),
        "Expected response to contain 'Ahoy' or 'Matey', but was: \"$text\"",
      )
    }
  }

  @Test
  fun testGenerateContentWithCommonConfig() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithCommonConfig.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "List the numbers from 1 to 5",
          config =
            GenerateContentConfig(
              temperature = 0.1,
              topK = 1.0,
              maxOutputTokens = 100,
              stopSequences = listOf("3"),
            ),
        )

      val text = response.text ?: ""
      assertTrue(text.isNotEmpty())
      assertFalse(text.contains("3"))
      assertFalse(text.contains("4"))
    }
  }

  @Test
  fun testGenerateContentFunctionCall() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentFunctionCall.$suffix"

      val client = createClient(enterprise, testName)

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

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "What's the weather like in Melbourne?",
          config =
            GenerateContentConfig(
              tools = listOf(Tool(functionDeclarations = listOf(getWeatherDeclaration)))
            ),
        )

      assertNotNull(response.functionCalls)
      assertEquals("GetWeather", response.functionCalls!![0].name)
    }
  }

  @Test
  fun testGenerateContentStreamFunctionCall() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentStreamFunctionCall.$suffix"

      val client = createClient(enterprise, testName)

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
      val flow =
        client.models.generateContentStream(
          model = MODEL_NAME,
          text = "What's the weather like in Melbourne?",
          config =
            GenerateContentConfig(
              tools = listOf(Tool(functionDeclarations = listOf(getWeatherDeclaration)))
            ),
        )

      var foundFunctionCall = false
      flow.collect { chunk ->
        chunk.functionCalls?.let { calls ->
          if (calls.isNotEmpty()) {
            assertEquals("GetWeather", calls[0].name)
            foundFunctionCall = true
          }
        }
      }
      assertTrue(foundFunctionCall)
    }
  }

  @Test
  fun testGenerateContentWithThinkingConfig() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithThinkingConfig.$suffix"

      val client = createClient(enterprise, testName)

      val response =
        client.models.generateContent(
          model = MODEL_NAME,
          text = "What is the capital of France?",
          config =
            GenerateContentConfig(
              thinkingConfig =
                ThinkingConfig(thinkingLevel = ThinkingLevel.LOW, includeThoughts = true)
            ),
        )

      assertContains(response.text ?: "", "Paris")
      val parts = response.candidates?.firstOrNull()?.content?.parts
      assertTrue(parts!!.any { it.thought == true }, "Response should contain a thinking part")
    }
  }

  @Test
  fun testGenerateContentWithImageBytes() = runLongTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithImageBytes.$suffix"
      val client = createClient(enterprise, testName)
      val content =
        Content(
          role = "user",
          parts =
            listOf(
              Part(inlineData = Blob(mimeType = "image/png", data = googlePngBytes)),
              Part(text = "Describe this image"),
            ),
        )

      val response = client.models.generateContent(model = MODEL_NAME, content = content)
      assertContains(response.text ?: "", "Google")
    }
  }

  @Test
  fun testGenerateContentWithImageUrl() = runLongTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithImageUrl.$suffix"
      val client = createClient(enterprise, testName)
      val content =
        Content(
          role = "user",
          parts =
            listOf(
              Part(
                fileData =
                  FileData(
                    fileUri = "gs://cloud-samples-data/generative-ai/image/scones.jpg",
                    mimeType = "image/jpeg",
                  )
              ),
              Part(text = "Describe this image"),
            ),
        )

      if (enterprise) {
        val response = client.models.generateContent(model = MODEL_NAME, content = content)
        assertContains(response.text ?: "", "scones")
      } else {
        val exception =
          assertFailsWith<ClientException> {
            client.models.generateContent(model = MODEL_NAME, content = content)
          }
        assertEquals(400, exception.code)
      }
    }
  }

  @Test
  fun testGenerateContentWithVideoYouTubeLink() = runLongTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithVideoYouTubeLink.$suffix"
      val client = createClient(enterprise, testName)

      val content =
        Content(
          role = "user",
          parts =
            listOf(
              Part(
                fileData =
                  FileData(
                    fileUri = "https://www.youtube.com/watch?v=9hE5-98ZeCg",
                    mimeType = "video/mp4",
                  )
              ),
              Part(text = "Please summarize the video in 3 sentences."),
            ),
        )

      val response = client.models.generateContent(model = MODEL_NAME, content = content)

      assertContains(response.text ?: "", "Gemini")
    }
  }

  @Ignore
  @Test
  fun testGenerateContentWithCachedContent() = runLongTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testGenerateContentWithCachedContent.$suffix"
      val client = createClient(enterprise, testName)

      val cachedContent =
        if (enterprise) "cachedContents/2522030231007526912"
        else "cachedContents/f4sg24rqje1qiire2bgpvphlq33kxg6o95moyret"

      val response =
        client.models.generateContent(
          model = "gemini-3.5-flash",
          text = "Summarize the PDF",
          config = GenerateContentConfig(cachedContent = cachedContent),
        )

      assertNotNull(response.text)
    }
  }

  @Test
  fun testEmbedContentGemini1() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testEmbedContentGemini1.$suffix"

      val client =
        createClient(
          enterprise,
          testName,
          locationOverride = if (enterprise) "us-central1" else null,
        )

      val response1 =
        client.models.embedContent(
          model = "gemini-embedding-001",
          text = "What is the capital of France?",
          config = EmbedContentConfig(outputDimensionality = 10),
        )

      assertNotNull(response1.embeddings)
      assertTrue(response1.embeddings!!.isNotEmpty())
      assertEquals(10, response1.embeddings!![0].values!!.size)

      val response2 =
        client.models.embedContent(
          model = "gemini-embedding-001",
          contents =
            listOf(
              Content(parts = listOf(Part(text = "Hello"))),
              Content(parts = listOf(Part(text = "World"))),
            ),
          config = EmbedContentConfig(outputDimensionality = 10),
        )
      assertNotNull(response2.embeddings)
      assertTrue(response2.embeddings!!.size == 2)
      assertEquals(10, response2.embeddings!![0].values!!.size)
      assertEquals(10, response2.embeddings!![1].values!!.size)
    }
  }

  @Test
  fun testEmbedContentGemini2() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testEmbedContentGemini2.$suffix"

      val client =
        createClient(
          enterprise,
          testName,
        )

      val response1 =
        client.models.embedContent(
          model = "gemini-embedding-2",
          text = "What is the capital of France?",
          config = EmbedContentConfig(outputDimensionality = 10),
        )

      assertNotNull(response1.embeddings)
      assertTrue(response1.embeddings!!.isNotEmpty())
      assertEquals(10, response1.embeddings!![0].values!!.size)

      if (enterprise) {
        val statistics = response1.embeddings!![0].statistics
        assertNotNull(statistics)
        assertNotNull(statistics.tokenCount)
        assertTrue(statistics.tokenCount!! > 0)
        assertNotNull(statistics.tokensDetails)
        assertTrue(statistics.tokensDetails!!.isNotEmpty())

        val exception =
          assertFailsWith<IllegalArgumentException> {
            client.models.embedContent(
              model = "gemini-embedding-2",
              contents =
                listOf(
                  Content(parts = listOf(Part(text = "Hello"))),
                  Content(parts = listOf(Part(text = "World"))),
                ),
              config = EmbedContentConfig(outputDimensionality = 10),
            )
          }
        assertContains(exception.message ?: "", "only supports one content")
      } else {
        val response2 =
          client.models.embedContent(
            model = "gemini-embedding-2",
            contents =
              listOf(
                Content(parts = listOf(Part(text = "Hello"))),
                Content(parts = listOf(Part(text = "World"))),
              ),
            config = EmbedContentConfig(outputDimensionality = 10),
          )
        assertNotNull(response2.embeddings)
        assertTrue(response2.embeddings!!.size == 2)
        assertEquals(10, response2.embeddings!![0].values!!.size)
        assertEquals(10, response2.embeddings!![1].values!!.size)
      }
    }
  }

  @Test
  fun testEmbedContentGemini2MaaS() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testEmbedContentGemini2MaaS.$suffix"

      val client =
        createClient(
          enterprise,
          testName,
          locationOverride = if (enterprise) "us-central1" else null,
        )

      if (enterprise) {
        val response =
          client.models.embedContent(
            model = "publishers/intfloat/models/multilingual-e5-large-instruct-maas",
            text = "What is the capital of France?",
            config = EmbedContentConfig(outputDimensionality = 10),
          )
        assertNotNull(response.embeddings)
        assertTrue(response.embeddings!!.isNotEmpty())
        assertEquals(1024, response.embeddings!![0].values!!.size)
      } else {
        val exception =
          assertFailsWith<ClientException> {
            client.models.embedContent(
              model = "publishers/intfloat/models/multilingual-e5-large-instruct-maas",
              text = "What is the capital of France?",
              config = EmbedContentConfig(outputDimensionality = 10),
            )
          }
        assertContains(exception.message ?: "", "404")
      }
    }
  }

  @Test
  fun testEmbedContentGemini2WithGcsImageAndConfig() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testEmbedContentGemini2WithGcsImageAndConfig.$suffix"

      val client =
        createClient(
          enterprise,
          testName,
        )

      val response =
        client.models.embedContent(
          model = "gemini-embedding-2",
          contents =
            listOf(
              Content(
                parts =
                  listOf(
                    Part(text = "Similar things to the following image:"),
                    Part(
                      fileData =
                        FileData(
                          fileUri = "gs://cloud-samples-data/generative-ai/image/a-man-and-a-dog.png",
                          mimeType = "image/png",
                        )
                    ),
                  )
              )
            ),
          config =
            EmbedContentConfig(
              outputDimensionality = 10,
              title = "test_title",
              taskType = "RETRIEVAL_DOCUMENT",
            ),
        )

      assertNotNull(response.embeddings)
      assertTrue(response.embeddings!!.isNotEmpty())
      assertEquals(10, response.embeddings!![0].values!!.size)
    }
  }

  @Test
  fun testEmbedContentGemini2MultiModal() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testEmbedContentGemini2MultiModal.$suffix"

      val client =
        createClient(
          enterprise,
          testName,
        )

      val response =
        client.models.embedContent(
          model = "gemini-embedding-2",
          contents =
            listOf(
              Content(
                parts =
                  listOf(
                    Part(text = "The jetpack is cool"),
                    Part(inlineData = Blob(mimeType = "image/png", data = googlePngBytes)),
                  )
              )
            ),
          config = EmbedContentConfig(outputDimensionality = 20),
        )

      assertNotNull(response.embeddings)
      assertTrue(response.embeddings!!.isNotEmpty())
      assertEquals(20, response.embeddings!![0].values!!.size)

      if (enterprise) {
        val statistics = response.embeddings!![0].statistics
        assertNotNull(statistics)
        assertNotNull(statistics.tokenCount)
        assertTrue(statistics.tokenCount!! > 0)
        assertNotNull(statistics.tokensDetails)
        assertTrue(statistics.tokensDetails!!.size >= 2)
      }
    }
  }

  @Test
  fun testEmbedContentGemini2VertexOnlyConfig() = runTest {
    listOf(false, true).forEach { enterprise ->
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "ModelsTest.testEmbedContentGemini2VertexOnlyConfig.$suffix"

      val client =
        createClient(
          enterprise,
          testName,
        )

      if (enterprise) {
        val response =
          client.models.embedContent(
            model = "gemini-embedding-2",
            text = "What is the capital of France?",
            config =
              EmbedContentConfig(
                outputDimensionality = 10,
                autoTruncate = true,
              ),
          )
        assertNotNull(response.embeddings)
        assertTrue(response.embeddings!!.isNotEmpty())
        assertEquals(10, response.embeddings!![0].values!!.size)
      } else {
        val exception =
          assertFailsWith<IllegalArgumentException> {
            client.models.embedContent(
              model = "gemini-embedding-2",
              text = "What is the capital of France?",
              config =
                EmbedContentConfig(
                  outputDimensionality = 10,
                  autoTruncate = true,
                ),
            )
          }
        assertContains(exception.message ?: "", "autoTruncate parameter is not supported in Gemini API.")
      }
    }
  }

}
