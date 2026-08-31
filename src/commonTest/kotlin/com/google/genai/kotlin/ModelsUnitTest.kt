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
import com.google.genai.kotlin.types.Part
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.content.TextContent
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

private const val MODEL = "gemini-3-flash-preview"

private const val RESPONSE =
  """{"candidates":[{"content":{"parts":[{"text":"ok"}],"role":"model"}}]}"""

private const val STREAM_RESPONSE = "data: $RESPONSE\n\n"

/**
 * Tests for [Models] that assert what goes out on the wire, using a mock engine instead of the
 * test-server. See [ModelsTest] for the record/replay tests.
 */
class ModelsUnitTest {

  private var sentBody: String? = null

  private fun client(response: String): Client {
    val engine = MockEngine { request ->
      sentBody = (request.body as TextContent).text
      respond(response, headers = headersOf(HttpHeaders.ContentType, "application/json"))
    }
    return Client(
      apiKey = "test-api-key",
      clientOptions = ClientOptions(customHttpClient = engine),
      environment = mockk<Environment>().also { every { it.get(any()) } returns null },
    )
  }

  /** The role of each entry of `contents` in the request that was sent, in order. */
  private fun sentRoles(): List<String?> =
    Json.parseToJsonElement(sentBody!!).jsonObject["contents"]!!.jsonArray.map {
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
}
