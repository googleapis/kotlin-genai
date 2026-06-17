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

/**
 * Client generated response to a `ToolCall` received from the server.
 *
 * Individual `FunctionResponse` objects are matched to the respective `FunctionCall` objects by the
 * `id` field.
 *
 * Note that in the unary and server-streaming GenerateContent APIs function calling happens by
 * exchanging the `Content` parts, while in the bidi GenerateContent APIs function calling happens
 * over this dedicated set of messages.
 */
@Serializable
data class LiveClientToolResponse(

  /** The response to the function calls. */
  val functionResponses: List<FunctionResponse>? = null
)
