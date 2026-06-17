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

/** Defines how to parse sample response. */
@Serializable
@JvmInline
value class ResponseParseType(val value: String) {
  companion object {

    /** Default value. This value is unused. */
    val RESPONSE_PARSE_TYPE_UNSPECIFIED = ResponseParseType("RESPONSE_PARSE_TYPE_UNSPECIFIED")

    /** Use the sample response as is. */
    val IDENTITY = ResponseParseType("IDENTITY")

    /** Use regex to extract the important part of sample response. */
    val REGEX_EXTRACT = ResponseParseType("REGEX_EXTRACT")
  }
}
