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

/**
 * A predicted server-side `ToolCall` returned from the model.
 *
 * This message contains information about a tool that the model wants to invoke. The client is NOT
 * expected to execute this `ToolCall`. Instead, the client should pass this `ToolCall` back to the
 * API in a subsequent turn within a `Content` message, along with the corresponding `ToolResponse`.
 */
@Serializable
data class ToolCall(

  /**
   * Unique identifier of the tool call. The server returns the tool response with the matching
   * `id`.
   */
  val id: String? = null,

  /** The type of tool that was called. */
  val toolType: ToolType? = null,

  /** The tool call arguments. Example: {"arg1": "value1", "arg2": "value2"}. */
  val args: Map<String, JsonElement>? = null,
)
