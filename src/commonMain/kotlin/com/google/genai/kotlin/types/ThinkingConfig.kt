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

/** The thinking features configuration. */
@Serializable
data class ThinkingConfig(

  /**
   * Indicates whether to include thoughts in the response. If true, thoughts are returned only if
   * the model supports thought and thoughts are available.
   */
  val includeThoughts: Boolean? = null,

  /**
   * Indicates the thinking budget in tokens. 0 is DISABLED. -1 is AUTOMATIC. The default values and
   * allowed ranges are model dependent.
   */
  val thinkingBudget: Int? = null,

  /** Optional. The number of thoughts tokens that the model should generate. */
  val thinkingLevel: ThinkingLevel? = null,
)
