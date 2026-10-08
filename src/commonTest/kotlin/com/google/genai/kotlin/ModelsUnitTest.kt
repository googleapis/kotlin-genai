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

import com.google.genai.kotlin.types.ClientOptions
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FinishReason
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.http.headersOf
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import kotlinx.serialization.json.putJsonArray
import kotlinx.serialization.json.putJsonObject

private const val MODEL = "gemini-3-flash-preview"

private const val RESPONSE =
  """{"candidates":[{"content":{"parts":[{"text":"ok"}],"role":"model"}}]}"""

private const val STREAM_RESPONSE = "data: $RESPONSE\n\n"

private const val ERROR_CHUNK =
  """{"error":{"code":429,"message":"Quota exceeded","status":"RESOURCE_EXHAUSTED"}}"""

/** "token", base64-encoded the way the SDK sends bytes. */
private const val TOKEN = "dG9rZW4="

private val CONTINUING = GenerateContentConfig(automaticContinuation = true, temperature = 0.5)

/** A response carrying [text], stopped for [finishReason], with a continuation [token] if given. */
private fun reply(text: String, finishReason: String, token: String? = null): String =
  buildJsonObject {
      putJsonArray("candidates") {
        addJsonObject {
          putJsonObject("content") {
            putJsonArray("parts") { addJsonObject { put("text", text) } }
            put("role", "model")
          }
          put("finishReason", finishReason)
          if (token != null) {
            put("continuationToken", token)
          }
        }
      }
    }
    .toString()

/**
 * Tests for [Models] that assert what goes out on the wire, using a mock engine instead of the
 * test-server. See [ModelsTest] for the record/replay tests.
 */
class ModelsUnitTest {

  private val sentBodies = mutableListOf<JsonObject>()

  /** Returns a client whose requests get [responses], one per request, in order. */
  private fun client(vararg responses: String): Client {
    val pending = ArrayDeque(responses.toList())
    val engine = MockEngine { request ->
      sentBodies += Json.parseToJsonElement((request.body as TextContent).text).jsonObject
      respond(
        pending.removeFirst(),
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }
    return Client(
      apiKey = "test-api-key",
      clientOptions = ClientOptions(customHttpClient = engine),
      environment = mockk<Environment>().also { every { it.get(any()) } returns null },
    )
  }

  /** The role of each entry of `contents` in the request that was sent, in order. */
  private fun sentRoles(): List<String?> =
    sentBodies.single()["contents"]!!.jsonArray.map {
      it.jsonObject["role"]?.jsonPrimitive?.content
    }

  @Test
  fun testGenerateContent_unsetRoleIsSentAsUser() = runTest {
    client(RESPONSE).use { client ->
      client.models.generateContent(MODEL, Content(parts = listOf(Part(text = "Hello"))))
    }

    assertEquals(listOf("user"), sentRoles())
  }

  @Test
  fun testGenerateContentStream_unsetRoleIsSentAsUser() = runTest {
    client(STREAM_RESPONSE).use { client ->
      val content = Content(parts = listOf(Part(text = "Hello")))
      client.models.generateContentStream(MODEL, content).collect {}
    }

    assertEquals(listOf("user"), sentRoles())
  }

  @Test
  fun testGenerateContent_contentListKeepsRolesAsGiven() = runTest {
    // A list of contents is a conversation, not one message, so an unset role is left alone rather
    // than relabelled "user" -- doing that would turn a model turn into a user turn.
    client(RESPONSE).use { client ->
      client.models.generateContent(
        MODEL,
        listOf(
          Content(parts = listOf(Part(text = "Hello"))),
          Content(role = "model", parts = listOf(Part(text = "Hi"))),
        ),
      )
    }

    assertEquals(listOf(null, "model"), sentRoles())
  }

  @Test
  fun testGenerateContentStream_errorChunkThrows() = runTest {
    client("data: $ERROR_CHUNK\n\n").use { client ->
      val thrown =
        assertFailsWith<ClientException> {
          client.models
            .generateContentStream(MODEL, Content(parts = listOf(Part(text = "Hello"))))
            .collect {}
        }
      assertEquals(429, thrown.code)
      assertEquals("RESOURCE_EXHAUSTED", thrown.status)
    }
  }

  @Test
  fun testGenerateContentStream_errorAfterAGoodChunkThrowsAndKeepsWhatArrived() = runTest {
    client(STREAM_RESPONSE + "data: $ERROR_CHUNK\n\n").use { client ->
      val seen = mutableListOf<String?>()
      assertFailsWith<ClientException> {
        client.models
          .generateContentStream(MODEL, Content(parts = listOf(Part(text = "Hello"))))
          .collect { seen.add(it.text) }
      }
      assertEquals(listOf<String?>("ok"), seen)
    }
  }

  @Test
  fun testGenerateContent_automaticContinuationResendsTheRequestWithTheToken() = runTest {
    val response =
      client(reply("Hello ", "CONTINUATION", TOKEN), reply("world", "STOP")).use { client ->
        client.models.generateContent(MODEL, "Write a long story.", CONTINUING)
      }

    assertEquals("Hello world", response.text)
    assertEquals(FinishReason.STOP, response.finishReason)
    assertEquals(2, sentBodies.size)
    val (first, second) = sentBodies
    assertNull(first["continuationToken"])
    assertEquals(TOKEN, second["continuationToken"]?.jsonPrimitive?.content)
    // Apart from the token, the second request is the first one: no earlier output is appended.
    assertEquals(first, JsonObject(second - "continuationToken"))
    assertFalse(sentBodies.any { "automaticContinuation" in it.toString() })
  }

  @Test
  fun testGenerateContent_automaticContinuationSendsMaxOutputTokensUntilTheServerStops() = runTest {
    // The server counts maxOutputTokens across the requests and ends with MAX_TOKENS once it is
    // spent, which ends the continuation.
    val response =
      client(reply("Hello ", "CONTINUATION", TOKEN), reply("world", "MAX_TOKENS", TOKEN)).use {
        client ->
        client.models.generateContent(
          MODEL,
          "Write a long story.",
          CONTINUING.copy(maxOutputTokens = 10),
        )
      }

    assertEquals(FinishReason.MAX_TOKENS, response.finishReason)
    assertEquals(
      listOf("10", "10"),
      sentBodies.map { it["generationConfig"]?.jsonObject?.get("maxOutputTokens").toString() },
    )
  }

  @Test
  fun testGenerateContent_sendsOnceWithoutAutomaticContinuation() = runTest {
    client(reply("Hello ", "CONTINUATION", TOKEN)).use { client ->
      client.models.generateContent(MODEL, "Write a long story.")
    }

    assertEquals(1, sentBodies.size)
  }

  @Test
  fun testGenerateContent_automaticContinuationSetToFalseSendsOnce() = runTest {
    client(reply("Hello ", "CONTINUATION", TOKEN)).use { client ->
      client.models.generateContent(
        MODEL,
        "Write a long story.",
        CONTINUING.copy(automaticContinuation = false),
      )
    }

    assertEquals(1, sentBodies.size)
  }

  @Test
  fun testGenerateContentStream_automaticContinuationSetToFalseSendsOnce() = runTest {
    client("data: ${reply("Hello ", "CONTINUATION", TOKEN)}\n\n").use { client ->
      client.models
        .generateContentStream(
          MODEL,
          "Write a long story.",
          CONTINUING.copy(automaticContinuation = false),
        )
        .toList()
    }

    assertEquals(1, sentBodies.size)
  }

  @Test
  fun testGenerateContentStream_sendsOnceWithoutAutomaticContinuation() = runTest {
    client("data: ${reply("Hello ", "CONTINUATION", TOKEN)}\n\n").use { client ->
      client.models.generateContentStream(MODEL, "Write a long story.").toList()
    }

    assertEquals(1, sentBodies.size)
  }

  @Test
  fun testGenerateContentStream_automaticContinuationContinuesInANewStream() = runTest {
    val chunks =
      client(
          "data: ${reply("Hello ", "CONTINUATION", TOKEN)}\n\n",
          "data: ${reply("world", "STOP")}\n\n",
        )
        .use { client ->
          client.models.generateContentStream(MODEL, "Write a long story.", CONTINUING).toList()
        }

    assertEquals(listOf<String?>("Hello ", "world"), chunks.map { it.text })
    assertEquals(2, sentBodies.size)
    val (first, second) = sentBodies
    assertEquals(TOKEN, second["continuationToken"]?.jsonPrimitive?.content)
    assertEquals(first, JsonObject(second - "continuationToken"))
  }
}
