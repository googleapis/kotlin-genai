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

/** Reinforcement tuning autorater scorer. */
@Serializable
data class ReinforcementTuningAutoraterScorer(

  /** Autorater config for evaluation. */
  val autoraterConfig: AutoraterConfig? = null,

  /**
   * Allows substituting `prompt`, `response`, `system_instruction` and `references.reference` (each
   * wrapped in double curly braces) into the autorater prompt.
   */
  val autoraterPrompt: String? = null,

  /** Parses autorater returned response. */
  val autoraterResponseParseConfig: ReinforcementTuningParseResponseConfig? = null,

  /**
   * Scores autorater responses by directly converting parsed autorater response to float reward.
   */
  val parsedResponseConversionScorer:
    ReinforcementTuningAutoraterScorerParsedResponseConversionScorer? =
    null,

  /** Scores autorater responses by using exact string match reward scorer. */
  val exactMatchScorer: ReinforcementTuningAutoraterScorerExactMatchScorer? = null,
)
