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

import kotlinx.serialization.Serializable

/** Partial argument value of the function call. This data type is not supported in Gemini API. */
@Serializable
data class PartialArg(

  /** Optional. Represents a boolean value. */
  val boolValue: Boolean? = null,

  /**
   * Required. A JSON Path (RFC 9535) to the argument being streamed.
   * https://datatracker.ietf.org/doc/html/rfc9535. e.g. "$.foo.bar[0].data".
   */
  val jsonPath: String? = null,

  /** Optional. Represents a null value. */
  val nullValue: NullValue? = null,

  /** Optional. Represents a double value. */
  val numberValue: Double? = null,

  /** Optional. Represents a string value. */
  val stringValue: String? = null,

  /**
   * Optional. Whether this is not the last part of the same json_path. If true, another PartialArg
   * message for the current json_path is expected to follow.
   */
  val willContinue: Boolean? = null,
)
