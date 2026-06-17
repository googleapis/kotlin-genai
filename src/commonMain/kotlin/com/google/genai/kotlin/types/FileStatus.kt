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
import kotlinx.serialization.json.JsonElement

/** Status of a File that uses a common error model. */
@Serializable
data class FileStatus(

  /**
   * A list of messages that carry the error details. There is a common set of message types for
   * APIs to use.
   */
  val details: List<Map<String, JsonElement>>? = null,

  /**
   * A list of messages that carry the error details. There is a common set of message types for
   * APIs to use.
   */
  val message: String? = null,

  /** The status code. 0 for OK, 1 for CANCELLED */
  val code: Int? = null,
)
