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
   * The prompt for an autorater to scorer the parsed sample response. This field supports the
   * following placeholders that will be replaced before scoring: - {{prompt}} - {{response}} -
   * {{system_instruction}} - {{references.key}}
   */
  val autoraterPrompt: String? = null,

  /**
   * Parses autorater returned response for scoring. For example, if the autorater response has
   * reward stored in the `2.0` block, defining a parsing response config using regex `".*(.*?)"`
   * will return a score `"2.0"`.
   */
  val autoraterResponseParseConfig: ReinforcementTuningParseResponseConfig? = null,

  /**
   * Scores autorater responses by directly converting parsed autorater response to a float reward.
   * Note: Reward is clipped to be within `[-1, 1]`, i.e., `reward = max(min(reward, 1.0), -1.0)`.
   */
  val parsedResponseConversionScorer:
    ReinforcementTuningAutoraterScorerParsedResponseConversionScorer? =
    null,

  /** Scores autorater responses by using string match reward scorer. */
  val exactMatchScorer: ReinforcementTuningAutoraterScorerExactMatchScorer? = null,
)
