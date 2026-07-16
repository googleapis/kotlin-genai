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

/** User-facing format for Gemini Reinforcement Tuning examples on Vertex. */
@Serializable
data class ReinforcementTuningExample(

  /**
   * References for the given prompt. The key is the name of the reference, and the value is the
   * reference itself.
   */
  val references: Map<String, String>? = null,

  /** Multi-turn contents that represents the Prompt. */
  val contents: List<Content>? = null,

  /** Corresponds to system_instruction in user-facing GenerateContentRequest. */
  val systemInstruction: Content? = null,
)
