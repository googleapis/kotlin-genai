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

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ApiResponseTest {

  private fun createMockEngine(responseContent: String, status: HttpStatusCode): MockEngine {
    return MockEngine { _ ->
      respond(
        content = responseContent,
        status = status,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }
  }

  @Test
  fun `body() returns content for 2xx response`() = runTest {
    val engine = createMockEngine("{\"result\": \"ok\"}", HttpStatusCode.OK)
    val client = ApiClient(apiKey = "test", engine = engine)

    val response = client.request("GET", "test")
    assertEquals("{\"result\": \"ok\"}", response.body())
  }

  @Test
  fun `body() throws ClientException for 4xx response`() = runTest {
    val errorJson =
      """
      {
        "error": {
          "code": 400,
          "message": "Invalid argument",
          "status": "INVALID_ARGUMENT"
        }
      }
      """
        .trimIndent()
    val engine = createMockEngine(errorJson, HttpStatusCode.BadRequest)
    val client = ApiClient(apiKey = "test", engine = engine)

    val response = client.request("GET", "test")
    val exception = assertFailsWith<ClientException> { response.body() }
    assertEquals(400, exception.code)
    assertEquals("INVALID_ARGUMENT", exception.status)
    assertEquals("400 INVALID_ARGUMENT: Invalid argument", exception.message)
  }

  @Test
  fun `body() throws ServerException for 5xx response`() = runTest {
    val errorJson =
      """
      {
        "error": {
          "code": 500,
          "message": "Internal error",
          "status": "INTERNAL"
        }
      }
      """
        .trimIndent()
    val engine = createMockEngine(errorJson, HttpStatusCode.InternalServerError)
    val client = ApiClient(apiKey = "test", engine = engine)

    val response = client.request("GET", "test")
    val exception = assertFailsWith<ServerException> { response.body() }
    assertEquals(500, exception.code)
    assertEquals("INTERNAL", exception.status)
    assertEquals("500 INTERNAL: Internal error", exception.message)
  }

  @Test
  fun `body() throws GenAiApiException for other error codes with fallback`() = runTest {
    val engine = MockEngine { _ ->
      respond(
        content = "Not Found",
        status = HttpStatusCode.NotFound,
        headers = headersOf(HttpHeaders.ContentType, "text/plain"),
      )
    }
    val client = ApiClient(apiKey = "test", engine = engine)

    val response = client.request("GET", "test")
    val exception = assertFailsWith<ClientException> { response.body() }
    assertEquals(404, exception.code)
    assertEquals("UNKNOWN", exception.status)
    assertEquals("404 UNKNOWN: Unknown error", exception.message)
  }
}
