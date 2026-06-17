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

// Auto-generated code. Do not edit.

package com.google.genai.kotlin.types

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/** The location of the API key. This enum is not supported in Gemini API. */
@Serializable
@JvmInline
value class HttpElementLocation(val value: String) {
  companion object {

    /**  */
    val HTTP_IN_UNSPECIFIED = HttpElementLocation("HTTP_IN_UNSPECIFIED")

    /** Element is in the HTTP request query. */
    val HTTP_IN_QUERY = HttpElementLocation("HTTP_IN_QUERY")

    /** Element is in the HTTP request header. */
    val HTTP_IN_HEADER = HttpElementLocation("HTTP_IN_HEADER")

    /** Element is in the HTTP request path. */
    val HTTP_IN_PATH = HttpElementLocation("HTTP_IN_PATH")

    /** Element is in the HTTP request body. */
    val HTTP_IN_BODY = HttpElementLocation("HTTP_IN_BODY")

    /** Element is in the HTTP request cookie. */
    val HTTP_IN_COOKIE = HttpElementLocation("HTTP_IN_COOKIE")
  }
}
