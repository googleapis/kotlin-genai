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

/**
 * Scores autorater responses by using exact string match reward scorer. This data type is not
 * supported in Gemini API.
 */
@Serializable
data class ReinforcementTuningAutoraterScorerExactMatchScorer(

  /** Assigns this reward score if the parsed response string equals the expression. */
  val correctAnswerReward: Double? = null,

  /** Assigns this reward score if the parsed reward value does not equal the expression. */
  val wrongAnswerReward: Double? = null,

  /**
   * The string expression to match against for scoring. This field supports placeholders in the
   * format of {{references.key}} that will be replaced before matching. Regex is not supported for
   * this expression. For example, users can define an ExactMatchScorer as follows: {
   * "correctAnswerReward": 1.0, "wrongAnswerReward": -1.0, "expression":
   * "{{references.concise_answer}}" } When evaluating the reward for each parsed autorater
   * response, if the prompt references in the training/validation dataset has the following
   * fields: ``` { "example": ..., "references": { "concise_ansser": "Yes", "verbose_answer": "The
   * answer is Yes" } } ``` The above ExactMatchScorer will be replaced as follows for scoring: ```
   * { "correctAnswerReward": 1.0, "wrongAnswerReward": -1.0, "expression": "Yes" } ``` If the
   * *parsed* autorater response is equal to the string `"Yes"`, then the reward is `1.0`, otherwise
   * the reward is `-1.0`.
   */
  val expression: String? = null,
)
