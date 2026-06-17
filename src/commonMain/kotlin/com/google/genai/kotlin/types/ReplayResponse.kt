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

/** Represents a single response in a replay. */
@Serializable
data class ReplayResponse(

  /**  */
  val statusCode: Int? = null,

  /**  */
  val headers: Map<String, String>? = null,

  /**  */
  val bodySegments: List<Map<String, JsonElement>>? = null,

  /**  */
  val sdkResponseSegments: List<Map<String, JsonElement>>? = null,
)
