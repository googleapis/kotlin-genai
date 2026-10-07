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
 * A wrapper around the list of tools.
 *
 * This wrapper exists because a bare `repeated Tool` field cannot tell apart "not sending a tools
 * update" from "clearing all tools": an unset repeated field and an empty repeated field look
 * identical on the wire. Wrapping the list in a message adds a presence bit, so the two cases
 * become: - `tools` field unset: no update; keep the previously provided tools. - `tools` field set
 * (even with an empty list): replace the current tools with the provided list, which may be empty
 * to clear all tools.
 */
@Serializable
data class LiveClientContextUpdateTools(

  /** The list of tools the model may use to generate the next response. */
  val tools: List<Tool>? = null
)
