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

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/** Base exception class for all exceptions originating from the GenAI SDK. */
sealed class GenAiException(message: String, cause: Throwable? = null) : Exception(message, cause)

/** Exception thrown when the GenAI API returns an error response. */
open class GenAiApiException(val code: Int, val status: String, message: String) :
  GenAiException("$code $status: $message") {
  companion object {
    /**
     * Parses the response body as JSON to extract error details and throws a corresponding
     * exception.
     *
     * Throws [ClientException] for 4xx errors, [ServerException] for 5xx errors, and
     * [GenAiApiException] otherwise.
     *
     * @param code The HTTP status code.
     * @param body The response body as a string.
     */
    fun throwFromResponse(code: Int, body: String) {
      val jsonElement =
        try {
          Json.parseToJsonElement(body)
        } catch (e: Exception) {
          null
        }

      val errorObject = jsonElement?.jsonObject?.get("error")?.jsonObject
      var message = errorObject?.get("message")?.jsonPrimitive?.content ?: "Unknown error"
      val status = errorObject?.get("status")?.jsonPrimitive?.content ?: "UNKNOWN"

      val detailsText =
        (errorObject?.get("details") as? JsonArray)
          ?.map { item ->
            val detail = (item as? JsonObject)?.get("detail")
            (detail as? JsonPrimitive)?.content ?: item.toString()
          }
          ?.joinToString("\n")

      if (!detailsText.isNullOrEmpty()) {
        message = "$message\nDetails: $detailsText"
      }

      if (code in 400..499) {
        throw ClientException(code, status, message)
      } else if (code in 500..599) {
        throw ServerException(code, status, message)
      } else {
        throw GenAiApiException(code, status, message)
      }
    }
  }
}

/** Exception thrown for 4xx client errors from the GenAI API. */
class ClientException(code: Int, status: String, message: String) :
  GenAiApiException(code, status, message)

/** Exception thrown for 5xx server errors from the GenAI API. */
class ServerException(code: Int, status: String, message: String) :
  GenAiApiException(code, status, message)
