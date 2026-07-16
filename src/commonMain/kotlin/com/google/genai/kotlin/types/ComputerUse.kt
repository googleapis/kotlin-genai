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

/** Tool to support computer use. */
@Serializable
data class ComputerUse(

  /** Optional. Enables the prompt injection detection check on computer-use request. */
  val enablePromptInjectionDetection: Boolean? = null,

  /** Required. The environment being operated. */
  val environment: Environment? = null,

  /**
   * Optional. By default,
   * [predefined functions](https://cloud.google.com/vertex-ai/generative-ai/docs/computer-use#supported-actions)
   * are included in the final model call. Some of them can be explicitly excluded from being
   * automatically included. This can serve two purposes: 1. Using a more restricted / different
   * action space. 2. Improving the definitions / instructions of predefined functions.
   */
  val excludedPredefinedFunctions: List<String>? = null,

  /**
   * Optional. Disabled safety policies for computer use. This field is not supported in Vertex AI.
   */
  val disabledSafetyPolicies: List<SafetyPolicy>? = null,
)
