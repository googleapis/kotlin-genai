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

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

/** A wrapper class for handling HTTP responses from the Google GenAI SDK. */
internal class ApiResponse(private val rawResponse: HttpResponse) {

  /** The HTTP status code of the response. */
  val statusCode: Int
    get() = rawResponse.status.value

  /**
   * Returns the response body as a string.
   *
   * Throws [GenAiApiException] (or its subclasses) if the HTTP status code indicates an error (>=
   * 300).
   *
   * @return The response body text.
   */
  suspend fun body(): String {
    val responseText = rawResponse.bodyAsText()
    if (statusCode >= 300) {
      GenAiApiException.throwFromResponse(statusCode, responseText)
    }
    return responseText
  }

  /** Deserializes the response body into an object of type [T]. */
  suspend inline fun <reified T> deserialize(): T {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString(serializer<T>(), body())
  }

  /** Returns all response headers. */
  val headers: Headers
    get() = rawResponse.headers
}
