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

/** Function calling config. */
@Serializable
data class FunctionCallingConfig(

  /**
   * Optional. Function names to call. Only set when the Mode is ANY. Function names should match
   * FunctionDeclaration.name. With mode set to ANY, model will predict a function call from the set
   * of function names provided.
   */
  val allowedFunctionNames: List<String>? = null,

  /** Optional. Function calling mode. */
  val mode: FunctionCallingConfigMode? = null,

  /**
   * Optional. When set to true, arguments of a single function call will be streamed out in
   * multiple parts/contents/responses. Partial parameter results will be returned in the
   * `FunctionCall.partial_args` field. This field is not supported in Gemini API.
   */
  val streamFunctionCallArguments: Boolean? = null,
)
