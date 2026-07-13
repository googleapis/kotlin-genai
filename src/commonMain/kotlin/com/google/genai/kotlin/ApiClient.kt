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

import com.google.genai.kotlin.types.HttpOptions
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.timeout
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

/** Internal HTTP client wrapper for the Gen AI APIs. */
internal class ApiClient(
  internal val apiKey: String? = null,
  internal val project: String? = null,
  internal val location: String? = null,
  internal val credentials: GoogleCredentials? = null,
  internal val enterprise: Boolean = false,
  internal val httpOptions: HttpOptions? = null,
  internal val engine: HttpClientEngine = getDefaultEngine(),
) : AutoCloseable {

  private val json = Json {
    ignoreUnknownKeys = true
    encodeDefaults = false
    explicitNulls = false
    prettyPrint = false
  }
  private val clientHttpOptions: HttpOptions = run {
    val defaultBaseUrl =
      if (enterprise) {
        if (location == "global" || location == null) "https://aiplatform.googleapis.com"
        else if (location == "us") "https://aiplatform.us.rep.googleapis.com"
        else "https://$location-aiplatform.googleapis.com"
      } else {
        "https://generativelanguage.googleapis.com"
      }
    val defaultApiVersion = if (enterprise) "v1beta1" else "v1beta"
    val defaultHeaders = mapOf("x-goog-api-client" to "genai-kotlin/0.1.0")

    val base =
      HttpOptions(
        baseUrl = defaultBaseUrl,
        apiVersion = defaultApiVersion,
        headers = defaultHeaders,
      )

    base.merge(httpOptions)
  }

  private val client =
    HttpClient(engine) {
      defaultRequest { contentType(ContentType.Application.Json) }
      install(HttpTimeout) {
        requestTimeoutMillis = Long.MAX_VALUE
        connectTimeoutMillis = Long.MAX_VALUE
        socketTimeoutMillis = Long.MAX_VALUE
      }
    }

  /** The core request method. */
  suspend fun request(
    method: String,
    path: String,
    body: JsonObject? = null,
    httpOptions: HttpOptions? = null,
  ): ApiResponse {
    val response = client.request { buildRequest(method, path, body, httpOptions) }
    return ApiResponse(response)
  }

  /**
   * Executes a streaming request and returns the response as a [Flow] of lines.
   *
   * Throws [GenAiApiException] (or its subclasses) if the HTTP status code indicates an error (>=
   * 300).
   *
   * @param method The HTTP method (e.g., "POST").
   * @param path The URL path.
   * @param body The request body as a [JsonObject].
   * @param httpOptions Custom HTTP options for this request.
   * @return A [Flow] of strings, where each string represents a line from the response stream.
   */
  fun requestStream(
    method: String,
    path: String,
    body: JsonObject? = null,
    httpOptions: HttpOptions? = null,
  ): Flow<String> = flow {
    client
      .prepareRequest { buildRequest(method, path, body, httpOptions) }
      .execute { httpResponse ->
        val status = httpResponse.status.value
        if (status >= 300) {
          val errorBody = httpResponse.bodyAsText()
          GenAiApiException.throwFromResponse(status, errorBody)
        }
        val channel = httpResponse.bodyAsChannel()
        val buffer = StringBuilder()
        while (!channel.isClosedForRead) {
          val line = channel.readUTF8Line() ?: break
          when {
            line.isEmpty() && buffer.isNotEmpty() -> {
              emit(buffer.toString().trim())
              buffer.clear()
            }
            line.startsWith("data:") -> {
              buffer.append(line.removePrefix("data:").trim())
              buffer.append("\n")
            }
          }
        }
      }
  }

  /** Builds the request message for both unary and streaming requests. */
  private fun HttpRequestBuilder.buildRequest(
    method: String,
    path: String,
    body: JsonObject?,
    requestHttpOptions: HttpOptions? = null,
  ) {
    this.method = HttpMethod.parse(method.uppercase())

    var resolvedPath = path
    val queryBaseModel =
      method.uppercase() == "GET" && resolvedPath.startsWith("publishers/google/models")
    if (enterprise && apiKey == null && !resolvedPath.startsWith("projects/") && !queryBaseModel) {
      resolvedPath = "projects/$project/locations/$location/$resolvedPath"
    }

    val mergedOptions = clientHttpOptions.merge(requestHttpOptions)

    mergedOptions.timeout?.let { timeoutMs ->
      timeout {
        requestTimeoutMillis = timeoutMs.toLong()
      }
    }

    val baseUrl = mergedOptions.baseUrl ?: throw IllegalStateException("baseUrl is required")
    val cleanBaseUrl = baseUrl.removeSuffix("/")
    val apiVersion =
      mergedOptions.apiVersion ?: throw IllegalStateException("apiVersion is required")

    val requestUrl =
      if (apiVersion.isEmpty()) {
        "$cleanBaseUrl/$resolvedPath"
      } else {
        "$cleanBaseUrl/$apiVersion/$resolvedPath"
      }
    url(requestUrl)

    // Headers from HttpOptions
    mergedOptions.headers?.forEach { (k, v) ->
      if (k != "Content-Type") {
        headers[k] = v
      }
    }

    // Auth
    if (apiKey != null) {
      header("x-goog-api-key", apiKey)
    } else if (credentials != null) {
      credentials.applyToRequest(this)
    }

    // Body
    if (body != null) {
      setBody(body.toString())
    }
  }

  override fun close() {
    client.close()
  }
}

/** Merges two [HttpOptions] instances, with [other] overriding [this]. */
private fun HttpOptions.merge(other: HttpOptions?): HttpOptions {
  if (other == null) return this
  return this.copy(
    baseUrl = other.baseUrl ?: this.baseUrl,
    apiVersion = other.apiVersion ?: this.apiVersion,
    headers = mergeHeaders(this.headers, other.headers),
    timeout = other.timeout ?: this.timeout,
  )
}

/** Merges two [Map<String, String>] instances, with [override] overriding [base]. */
private fun mergeHeaders(
  base: Map<String, String>?,
  override: Map<String, String>?,
): Map<String, String>? {
  if (base == null) return override
  if (override == null) return base

  val result = base.toMutableMap()
  override.forEach { (key, value) ->
    if (key == "user-agent" || key == "x-goog-api-client") {
      val existing = result[key]
      if (existing != null) {
        result[key] = "$existing $value"
      } else {
        result[key] = value
      }
    } else {
      result[key] = value
    }
  }
  return result
}
