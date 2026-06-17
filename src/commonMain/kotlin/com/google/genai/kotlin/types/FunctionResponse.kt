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
 * The result output from a FunctionCall that contains a string representing the
 * FunctionDeclaration.name and a structured JSON object containing any output from the function is
 * used as context to the model. This should contain the result of a `FunctionCall` made based on
 * model prediction.
 */
@Serializable
data class FunctionResponse(

  /**
   * Optional. Signals that function call continues, and more responses will be returned, turning
   * the function call into a generator. Is only applicable to NON_BLOCKING function calls, is
   * ignored otherwise. If set to false, future responses will not be considered. It is allowed to
   * return empty `response` with `will_continue=False` to signal that the function call is
   * finished. This may still trigger the model generation. To avoid triggering the generation and
   * finish the function call, additionally set `scheduling` to `SILENT`. This field is not
   * supported in Vertex AI.
   */
  val willContinue: Boolean? = null,

  /**
   * Optional. Specifies how the response should be scheduled in the conversation. Only applicable
   * to NON_BLOCKING function calls, is ignored otherwise. Defaults to WHEN_IDLE.
   */
  val scheduling: FunctionResponseScheduling? = null,

  /**
   * Optional. Ordered `Parts` that constitute a function response. Parts may have different IANA
   * MIME types.
   */
  val parts: List<FunctionResponsePart>? = null,

  /**
   * Optional. The id of the function call this response is for. Populated by the client to match
   * the corresponding function call `id`.
   */
  val id: String? = null,

  /**
   * Required. The name of the function to call. Matches FunctionDeclaration.name and
   * FunctionCall.name.
   */
  val name: String? = null,

  /**
   * Required. The function response in JSON object format. Use "output" key to specify function
   * output and "error" key to specify error details (if any). If "output" and "error" keys are not
   * specified, then whole "response" is treated as function output.
   */
  val response: Map<String, JsonElement>? = null,
)
