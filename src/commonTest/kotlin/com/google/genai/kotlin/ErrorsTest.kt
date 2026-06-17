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

import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.Test

class ErrorsTest {

  @Test
  fun testThrowFromResponse_parsesValidClientError() {
    val json =
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

    val exception =
      assertFailsWith<ClientException> { GenAiApiException.throwFromResponse(400, json) }

    assertEquals(400, exception.code)
    assertEquals("INVALID_ARGUMENT", exception.status)
    assertEquals("Invalid argument", exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_parsesErrorWithDetails() {
    val json =
      """
      {
        "error": {
          "code": 400,
          "message": "Request contains an invalid argument.",
          "status": "INVALID_ARGUMENT",
          "details": [
            {
              "@type": "type.googleapis.com/google.rpc.DebugInfo",
              "detail": "[ORIGINAL ERROR] generic::invalid_argument: Referencing Google Cloud Storage files directly is not supported. Register them using FileService.RegisterFile first."
            }
          ]
        }
      }
      """
        .trimIndent()

    val exception =
      assertFailsWith<ClientException> { GenAiApiException.throwFromResponse(400, json) }

    assertEquals(400, exception.code)
    assertEquals("INVALID_ARGUMENT", exception.status)
    val expectedMessage =
      "Request contains an invalid argument.\nDetails: [ORIGINAL ERROR] generic::invalid_argument: Referencing Google Cloud Storage files directly is not supported. Register them using FileService.RegisterFile first."
    assertEquals(expectedMessage, exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_parsesErrorWithDetailsNotArray() {
    val json =
      """
      {
        "error": {
          "code": 400,
          "message": "Invalid argument",
          "status": "INVALID_ARGUMENT",
          "details": "some string"
        }
      }
      """
        .trimIndent()

    val exception =
      assertFailsWith<ClientException> { GenAiApiException.throwFromResponse(400, json) }

    assertEquals("Invalid argument", exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_parsesErrorWithDetailsNonObjectItem() {
    val json =
      """
      {
        "error": {
          "code": 400,
          "message": "Invalid argument",
          "status": "INVALID_ARGUMENT",
          "details": [
            "string detail",
            123
          ]
        }
      }
      """
        .trimIndent()

    val exception =
      assertFailsWith<ClientException> { GenAiApiException.throwFromResponse(400, json) }

    val expectedMessage = "Invalid argument\nDetails: \"string detail\"\n123"
    assertEquals(expectedMessage, exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_parsesErrorWithDetailsNonStringDetail() {
    val json =
      """
      {
        "error": {
          "code": 400,
          "message": "Invalid argument",
          "status": "INVALID_ARGUMENT",
          "details": [
            {
              "detail": { "sub": "error" }
            }
          ]
        }
      }
      """
        .trimIndent()

    val exception =
      assertFailsWith<ClientException> { GenAiApiException.throwFromResponse(400, json) }

    val expectedMessage = "Invalid argument\nDetails: {\"detail\":{\"sub\":\"error\"}}"
    assertEquals(expectedMessage, exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_parsesValidServerError() {
    val json =
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

    val exception =
      assertFailsWith<ServerException> { GenAiApiException.throwFromResponse(500, json) }

    assertEquals(500, exception.code)
    assertEquals("INTERNAL", exception.status)
    assertEquals("Internal error", exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_handlesMalformedJson() {
    val json = "Invalid JSON"

    // Should default to ClientException for 400 code
    val exception =
      assertFailsWith<ClientException> { GenAiApiException.throwFromResponse(400, json) }

    assertEquals(400, exception.code)
    assertEquals("UNKNOWN", exception.status)
    assertEquals("Unknown error", exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_handlesMissingErrorField() {
    val json =
      """
      {
        "something_else": "value"
      }
      """
        .trimIndent()

    val exception =
      assertFailsWith<ClientException> { GenAiApiException.throwFromResponse(400, json) }

    assertEquals(400, exception.code)
    assertEquals("UNKNOWN", exception.status)
    assertEquals("Unknown error", exception.message?.substringAfter(": ")?.trim())
  }

  @Test
  fun testThrowFromResponse_handlesOtherStatusCode() {
    val json =
      """
      {
        "error": {
          "code": 300,
          "message": "Redirect",
          "status": "REDIRECT"
        }
      }
      """
        .trimIndent()

    val exception =
      assertFailsWith<GenAiApiException> { GenAiApiException.throwFromResponse(300, json) }

    assertEquals(300, exception.code)
    assertEquals("REDIRECT", exception.status)
    assertEquals("Redirect", exception.message?.substringAfter(": ")?.trim())
  }
}
