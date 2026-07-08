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

import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import com.google.genai.kotlin.types.HttpOptions
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.content.TextContent
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Test

class ApiClientTest {

  @Serializable data class DummyRequest(val message: String)

  private fun createMockEngine(
    responseContent: String = "{}",
    status: HttpStatusCode = HttpStatusCode.OK,
    handler: ((HttpRequestData) -> Unit)? = null,
  ): MockEngine {
    return MockEngine { request ->
      handler?.invoke(request)
      respond(
        content = responseContent,
        status = status,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }
  }

  @Test
  fun testRequest_withApiKey_addsHeader() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      client.request("GET", "test/path")
    }

    assertNotNull(capturedRequest)
    assertEquals("test-api-key", capturedRequest!!.headers["x-goog-api-key"])
  }

  @Test
  fun testRequest_withCredentials_addsBearerToken() = runTest {
    val mockCredentials = GoogleCredentials.create(AccessToken("test-token", null))

    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(credentials = mockCredentials, engine = engine).use { client ->
      client.request("GET", "test/path")
    }

    assertNotNull(capturedRequest)
    assertEquals("Bearer test-token", capturedRequest!!.headers["Authorization"])
  }

  @Test
  fun testRequest_gemini_constructsCorrectUrl() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      client.request("GET", "models/test-model")
    }

    assertNotNull(capturedRequest)
    assertEquals("generativelanguage.googleapis.com", capturedRequest!!.url.host)
    assertEquals("/v1beta/models/test-model", capturedRequest!!.url.encodedPath)
    assertEquals("https", capturedRequest!!.url.protocol.name)
  }

  @Test
  fun testRequest_enterprise_constructsCorrectUrl() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(
        project = "test-project",
        location = "us-central1",
        enterprise = true,
        engine = engine,
      )
      .use { client -> client.request("POST", "publishers/google/models/test-model:predict") }

    assertNotNull(capturedRequest)
    assertEquals("us-central1-aiplatform.googleapis.com", capturedRequest!!.url.host)
    assertEquals(
      "/v1beta1/projects/test-project/locations/us-central1/publishers/google/models/test-model:predict",
      capturedRequest!!.url.encodedPath,
    )
    assertEquals("https", capturedRequest!!.url.protocol.name)
  }

  @Test
  fun testRequest_sendsCorrectBody() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    val jsonBody = buildJsonObject { put("message", "hello") }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      client.request("POST", "test/path", jsonBody)
    }

    assertNotNull(capturedRequest)
    assertEquals(HttpMethod.Post, capturedRequest!!.method)

    val actualBody = capturedRequest!!.body as TextContent
    assertEquals("application/json", actualBody.contentType.toString())
    assertEquals("""{"message":"hello"}""", actualBody.text)
  }

  @Test
  fun testRequestStream_yieldsLinesSuccessfully() = runTest {
    val mockStreamResponse = "data: chunk 1\n\ndata: chunk 2\n\ndata: chunk 3\n\n"

    val engine = MockEngine {
      respond(
        content = ByteReadChannel(mockStreamResponse),
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      // toList() collects the Flow<String> into a List<String>
      val results = client.requestStream("GET", "test/stream").toList()

      assertEquals(3, results.size)
      assertEquals("chunk 1", results[0])
      assertEquals("chunk 2", results[1])
      assertEquals("chunk 3", results[2])
    }
  }

  @Test
  fun testRequestStream_accumulatesMultilineData() = runTest {
    val mockStreamResponse = "data: line 1\ndata: line 2\n\ndata: line 3\n\n"

    val engine = MockEngine {
      respond(
        content = ByteReadChannel(mockStreamResponse),
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      val results = client.requestStream("GET", "test/stream").toList()

      assertEquals(2, results.size)
      assertEquals("line 1\nline 2", results[0])
      assertEquals("line 3", results[1])
    }
  }

  @Test
  fun testRequestStream_withBody_sendsCorrectRequest() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = MockEngine { request ->
      capturedRequest = request
      respond(
        content = ByteReadChannel("response line"),
        status = HttpStatusCode.OK,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }

    val jsonBody = buildJsonObject { put("prompt", "stream this") }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      client.requestStream("POST", "test/stream", jsonBody).toList()
    }

    assertNotNull(capturedRequest)
    assertEquals(HttpMethod.Post, capturedRequest!!.method)

    val actualBody = capturedRequest!!.body as TextContent
    assertEquals("application/json", actualBody.contentType.toString())
    assertEquals("""{"prompt":"stream this"}""", actualBody.text)
  }

  @Test
  fun testRequestStream_throwsClientException() = runTest {
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

    val engine = MockEngine {
      respond(
        content = ByteReadChannel(errorJson),
        status = HttpStatusCode.BadRequest,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      val exception =
        assertFailsWith<ClientException> { client.requestStream("GET", "test/stream").toList() }
      assertEquals(400, exception.code)
      assertEquals("INVALID_ARGUMENT", exception.status)
      assertEquals("400 INVALID_ARGUMENT: Invalid argument", exception.message)
    }
  }

  @Test
  fun testRequest_withClientHttpOptions_overridesDefaults() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }
    val httpOptions = HttpOptions(baseUrl = "https://custom.api.com", apiVersion = "v2")

    ApiClient(apiKey = "test-api-key", httpOptions = httpOptions, engine = engine).use { client ->
      client.request("GET", "test/path")
    }

    assertNotNull(capturedRequest)
    assertEquals("custom.api.com", capturedRequest!!.url.host)
    assertEquals("/v2/test/path", capturedRequest!!.url.encodedPath)
  }

  @Test
  fun testRequest_withRequestHttpOptions_overridesClientOptions() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }
    val clientOptions = HttpOptions(baseUrl = "https://client.api.com", apiVersion = "v1")
    val requestOptions = HttpOptions(baseUrl = "https://request.api.com", apiVersion = "v2")

    ApiClient(apiKey = "test-api-key", httpOptions = clientOptions, engine = engine).use { client ->
      client.request("GET", "test/path", httpOptions = requestOptions)
    }

    assertNotNull(capturedRequest)
    assertEquals("request.api.com", capturedRequest!!.url.host)
    assertEquals("/v2/test/path", capturedRequest!!.url.encodedPath)
  }

  @Test
  fun testRequest_withHttpOptionsHeaders_mergesHeaders() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }
    val clientOptions = HttpOptions(headers = mapOf("X-Client-Header" to "client-val"))
    val requestOptions = HttpOptions(headers = mapOf("X-Request-Header" to "request-val"))

    ApiClient(apiKey = "test-api-key", httpOptions = clientOptions, engine = engine).use { client ->
      client.request("GET", "test/path", httpOptions = requestOptions)
    }

    assertNotNull(capturedRequest)
    assertEquals("client-val", capturedRequest!!.headers["X-Client-Header"])
    assertEquals("request-val", capturedRequest!!.headers["X-Request-Header"])
    assertEquals("genai-kotlin/0.1.0", capturedRequest!!.headers["x-goog-api-client"])
  }

  @Test
  fun testRequest_withHttpOptionsHeaders_appendsSpecificHeaders() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }
    val clientOptions = HttpOptions(headers = mapOf("x-goog-api-client" to "client-val"))
    val requestOptions = HttpOptions(headers = mapOf("x-goog-api-client" to "request-val"))

    ApiClient(apiKey = "test-api-key", httpOptions = clientOptions, engine = engine).use { client ->
      client.request("GET", "test/path", httpOptions = requestOptions)
    }

    assertNotNull(capturedRequest)
    // Defaults are "genai-kotlin/0.1.0".
    // clientOptions will append to defaults: "genai-kotlin/0.1.0 client-val"
    // requestOptions will append to clientOptions: "genai-kotlin/0.1.0 client-val request-val"
    assertEquals(
      "genai-kotlin/0.1.0 client-val request-val",
      capturedRequest!!.headers["x-goog-api-client"],
    )
  }

  @Test
  fun testRequest_withEnterpriseUsLocation_usesSpecialBaseUrl() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(project = "test-project", location = "us", enterprise = true, engine = engine).use {
      client ->
      client.request("GET", "publishers/google/models/test-model:predict")
    }

    assertNotNull(capturedRequest)
    // Host should be "aiplatform.us.rep.googleapis.com"
    assertEquals("aiplatform.us.rep.googleapis.com", capturedRequest!!.url.host)
  }

  @Test
  fun testRequest_defaultGeminiHeaders_usesCorrectHeaders() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      client.request("GET", "models/test-model")
    }

    assertNotNull(capturedRequest)
    assertEquals("genai-kotlin/0.1.0", capturedRequest!!.headers["x-goog-api-client"])
  }

  @Test
  fun testRequest_enterprise_defaultGlobalUrl() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(project = "test-project", location = "global", enterprise = true, engine = engine)
      .use { client -> client.request("GET", "publishers/google/models/test-model:predict") }

    assertNotNull(capturedRequest)
    assertEquals("aiplatform.googleapis.com", capturedRequest!!.url.host)
    // The path starts with "/" so split("/")[1] is "v1beta1"
    assertEquals("v1beta1", capturedRequest!!.url.encodedPath.split("/")[1])
  }

  @Test
  fun testRequest_enterprise_defaultNullLocationUsesGlobalUrl() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }

    ApiClient(project = "test-project", location = null, enterprise = true, engine = engine).use {
      client ->
      client.request("GET", "publishers/google/models/test-model:predict")
    }

    assertNotNull(capturedRequest)
    assertEquals("aiplatform.googleapis.com", capturedRequest!!.url.host)
    assertEquals("v1beta1", capturedRequest!!.url.encodedPath.split("/")[1])
  }

  @Test
  fun testRequest_preventsDuplicateHeaderValues() = runTest {
    var capturedRequest: HttpRequestData? = null
    val engine = createMockEngine { capturedRequest = it }
    val requestOptions = HttpOptions(headers = mapOf("Test-Name" to "test-name-val"))

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      client.request("GET", "test/path", httpOptions = requestOptions)
    }

    assertNotNull(capturedRequest)
    val headerValues = capturedRequest!!.headers.getAll("Test-Name")
    assertEquals(1, headerValues?.size ?: 0)
    assertEquals("test-name-val", headerValues?.firstOrNull())
  }

  @Test
  fun testRequest_withRequestHttpOptions_appliesTimeout() = runTest {
    val engine = MockEngine { _ ->
      kotlinx.coroutines.delay(1000)
      respond("ok")
    }

    val requestOptions = HttpOptions(timeout = 100)

    ApiClient(apiKey = "test-api-key", engine = engine).use { client ->
      assertFailsWith<io.ktor.client.plugins.HttpRequestTimeoutException> {
        client.request("GET", "test/path", httpOptions = requestOptions)
      }
    }
  }
}
