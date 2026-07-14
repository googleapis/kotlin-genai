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

/** Config for the count_tokens method. */
@Serializable
data class CountTokensConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /** Instructions for the model to steer it toward better performance. */
  val systemInstruction: Content? = null,

  /**
   * Code that enables the system to interact with external systems to perform an action outside of
   * the knowledge and scope of the model.
   */
  val tools: List<Tool>? = null,

  /**
   * Configuration that the model uses to generate the response. Not supported by the Gemini
   * Developer API.
   */
  val generationConfig: GenerationConfig? = null,
)
