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
 * The output from a server-side `ToolCall` execution.
 *
 * This message contains the results of a tool invocation that was initiated by a `ToolCall` from
 * the model. The client should pass this `ToolResponse` back to the API in a subsequent turn within
 * a `Content` message, along with the corresponding `ToolCall`.
 */
@Serializable
data class ToolResponse(

  /** The identifier of the tool call this response is for. */
  val id: String? = null,

  /** The type of tool that was called, matching the tool_type in the corresponding ToolCall. */
  val toolType: ToolType? = null,

  /** The tool response. */
  val response: Map<String, JsonElement>? = null,
)
