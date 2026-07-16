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
 * A predicted FunctionCall returned from the model that contains a string representing the
 * FunctionDeclaration.name and a structured JSON object containing the parameters and their values.
 */
@Serializable
data class FunctionCall(

  /**
   * Optional. The function parameters and values in JSON object format. See
   * FunctionDeclaration.parameters for parameter details.
   */
  val args: Map<String, JsonElement>? = null,

  /**
   * Optional. The unique id of the function call. If populated, the client to execute the
   * `function_call` and return the response with the matching `id`.
   */
  val id: String? = null,

  /** Optional. The name of the function to call. Matches FunctionDeclaration.name. */
  val name: String? = null,

  /**
   * Optional. The partial argument value of the function call. If provided, represents the
   * arguments/fields that are streamed incrementally. This field is not supported in Gemini API.
   */
  val partialArgs: List<PartialArg>? = null,

  /**
   * Optional. Whether this is the last part of the FunctionCall. If true, another partial message
   * for the current FunctionCall is expected to follow. This field is not supported in Gemini API.
   */
  val willContinue: Boolean? = null,
)
