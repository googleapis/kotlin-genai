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

/** Defines how to parse sample response for reinforcement tuning. */
@Serializable
data class ReinforcementTuningParseResponseConfig(

  /** Defines how to parse sample response. */
  val parseType: ResponseParseType? = null,

  /**
   * Defines the regex to extract the important part of sample response. This field is only used
   * when `parse_type` is `REGEX_EXTRACT`.
   */
  val regexExtractExpression: String? = null,
)
