/*
 * Copyright 2025 Google LLC
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

import com.google.genai.kotlin.types.HttpOptions
import com.google.genai.kotlin.types.HttpRetryOptions
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.junit.Test

/** Tests for the HTTP retry behaviour of [ApiClient]. */
class ApiClientRetryTest {

  /** Replays a scripted sequence of status codes, one per request, and counts the calls. */
  private class ScriptedEngine(private vararg val statuses: HttpStatusCode) {
    var calls = 0
      private set

    val bodies = mutableListOf<String>()

    val engine = MockEngine { request ->
      val index = calls
      calls++
      bodies.add((request.body as? io.ktor.content.TextContent)?.text ?: "")
      val status = statuses.getOrElse(index) { statuses.last() }
      respond(
        content = """{"error": {"code": ${status.value}, "message": "scripted"}}""",
        status = status,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }
  }

  /** Retry options with the backoff zeroed, so tests do not sleep. */
  private fun noBackoff(attempts: Int, statusCodes: List<Int>? = null) =
    HttpRetryOptions(
      attempts = attempts,
      initialDelay = 0.0,
      maxDelay = 0.0,
      jitter = 0.0,
      httpStatusCodes = statusCodes,
    )

  private val tooManyRequests = HttpStatusCode(429, "Too Many Requests")

  @Test
  fun testNoRetryOptions_makesASingleAttempt() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.ServiceUnavailable, HttpStatusCode.OK)

    ApiClient(apiKey = "k", engine = scripted.engine).use { client ->
      client.request("POST", "test/path")
    }

    assertEquals(1, scripted.calls)
  }

  @Test
  fun testRetryOptions_retriesUntilSuccess() = runTest {
    val scripted =
      ScriptedEngine(
        HttpStatusCode.ServiceUnavailable,
        HttpStatusCode.ServiceUnavailable,
        HttpStatusCode.OK,
      )

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 5)),
        engine = scripted.engine,
      )
      .use { client ->
        val response = client.request("POST", "test/path")
        assertEquals(200, response.statusCode)
      }

    assertEquals(3, scripted.calls)
  }

  @Test
  fun testRetryOptions_stopsAfterConfiguredAttempts() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.ServiceUnavailable)

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 2)),
        engine = scripted.engine,
      )
      .use { client ->
        val response = client.request("POST", "test/path")
        // The typed exception only surfaces when the body is read.
        assertEquals(503, response.statusCode)
        assertFailsWith<ServerException> { response.body() }
      }

    assertEquals(2, scripted.calls)
  }

  @Test
  fun testAttempts0Or1_makeASingleAttempt() = runTest {
    for (attempts in listOf(0, 1)) {
      val scripted = ScriptedEngine(HttpStatusCode.ServiceUnavailable, HttpStatusCode.OK)

      ApiClient(
          apiKey = "k",
          httpOptions = HttpOptions(retryOptions = noBackoff(attempts = attempts)),
          engine = scripted.engine,
        )
        .use { client -> client.request("POST", "test/path") }

      assertEquals(1, scripted.calls, "attempts=$attempts should make one call")
    }
  }

  @Test
  fun testNonRetryableStatus_isNotRetried() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.BadRequest, HttpStatusCode.OK)

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 5)),
        engine = scripted.engine,
      )
      .use { client ->
        val response = client.request("POST", "test/path")
        assertFailsWith<ClientException> { response.body() }
      }

    assertEquals(1, scripted.calls)
  }

  @Test
  fun testTooManyRequests_isRetryableByDefault() = runTest {
    val scripted = ScriptedEngine(tooManyRequests, HttpStatusCode.OK)

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 3)),
        engine = scripted.engine,
      )
      .use { client -> assertEquals(200, client.request("POST", "test/path").statusCode) }

    assertEquals(2, scripted.calls)
  }

  @Test
  fun testExplicitStatusCodes_replaceTheDefaultSet() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.ServiceUnavailable, HttpStatusCode.OK)

    ApiClient(
        apiKey = "k",
        httpOptions =
          HttpOptions(retryOptions = noBackoff(attempts = 5, statusCodes = listOf(429))),
        engine = scripted.engine,
      )
      .use { client -> assertEquals(503, client.request("POST", "test/path").statusCode) }

    assertEquals(1, scripted.calls)
  }

  @Test
  fun testExplicitStatusCodes_canAddANonDefaultCode() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.BadRequest, HttpStatusCode.OK)

    ApiClient(
        apiKey = "k",
        httpOptions =
          HttpOptions(retryOptions = noBackoff(attempts = 3, statusCodes = listOf(400))),
        engine = scripted.engine,
      )
      .use { client -> assertEquals(200, client.request("POST", "test/path").statusCode) }

    assertEquals(2, scripted.calls)
  }

  @Test
  fun testRequestRetryOptions_overrideClientRetryOptions() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.ServiceUnavailable)

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 5)),
        engine = scripted.engine,
      )
      .use { client ->
        client.request(
          "POST",
          "test/path",
          httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 2)),
        )
      }

    assertEquals(2, scripted.calls)
  }

  @Test
  fun testRequestRetryOptions_applyWhenTheClientSetsNone() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.ServiceUnavailable)

    ApiClient(apiKey = "k", engine = scripted.engine).use { client ->
      client.request(
        "POST",
        "test/path",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 3)),
      )
    }

    assertEquals(3, scripted.calls)
  }

  @Test
  fun testRetriedRequest_resendsTheBody() = runTest {
    val scripted = ScriptedEngine(HttpStatusCode.ServiceUnavailable, HttpStatusCode.OK)

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 3)),
        engine = scripted.engine,
      )
      .use { client ->
        client.request("POST", "test/path", body = buildJsonObject { put("key", "value") })
      }

    assertEquals(2, scripted.bodies.size)
    assertEquals(scripted.bodies[0], scripted.bodies[1])
    assertTrue(scripted.bodies[0].contains("\"key\""), "body was ${scripted.bodies[0]}")
  }

  @Test
  fun testRequestStream_retriesTheInitialSend() = runTest {
    var calls = 0
    val engine = MockEngine { _ ->
      calls++
      if (calls < 3) {
        respond(
          content = """{"error": {"code": 503, "message": "scripted"}}""",
          status = HttpStatusCode.ServiceUnavailable,
          headers = headersOf(HttpHeaders.ContentType, "application/json"),
        )
      } else {
        respond(
          content = "data: {\"text\": \"hello\"}\n\n",
          status = HttpStatusCode.OK,
          headers = headersOf(HttpHeaders.ContentType, "text/event-stream"),
        )
      }
    }

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 5)),
        engine = engine,
      )
      .use { client ->
        val chunks = client.requestStream("POST", "test/path").toList()
        assertEquals(1, chunks.size)
      }

    assertEquals(3, calls)
  }

  @Test
  fun testRequestStream_surfacesTheTypedErrorOnceExhausted() = runTest {
    var calls = 0
    val engine = MockEngine { _ ->
      calls++
      respond(
        content = """{"error": {"code": 503, "message": "scripted"}}""",
        status = HttpStatusCode.ServiceUnavailable,
        headers = headersOf(HttpHeaders.ContentType, "application/json"),
      )
    }

    ApiClient(
        apiKey = "k",
        httpOptions = HttpOptions(retryOptions = noBackoff(attempts = 2)),
        engine = engine,
      )
      .use { client ->
        assertFailsWith<ServerException> { client.requestStream("POST", "test/path").toList() }
      }

    assertEquals(2, calls)
  }

  @Test
  fun testRetryBackoffMillis_matchesThePythonFormula() {
    // min(initialDelay * expBase^(attempt-1) + U(0, jitter), maxDelay)
    val deterministic =
      HttpRetryOptions(initialDelay = 0.5, expBase = 3.0, jitter = 0.0, maxDelay = 60.0)
    assertEquals(500L, retryBackoffMillis(1, deterministic))
    assertEquals(1500L, retryBackoffMillis(2, deterministic))
    assertEquals(4500L, retryBackoffMillis(3, deterministic))

    val capped = HttpRetryOptions(maxDelay = 2.5)
    assertEquals(2500L, retryBackoffMillis(10, capped))

    val zeroed = HttpRetryOptions(initialDelay = 0.0, maxDelay = 0.0, jitter = 0.0)
    assertEquals(0L, retryBackoffMillis(4, zeroed))

    val defaults = HttpRetryOptions()
    repeat(50) {
      val first = retryBackoffMillis(1, defaults)
      assertTrue(first in 1000L..2000L, "first retry delay out of range: $first")
      val third = retryBackoffMillis(3, defaults)
      assertTrue(third in 4000L..5000L, "third retry delay out of range: $third")
    }
  }
}
