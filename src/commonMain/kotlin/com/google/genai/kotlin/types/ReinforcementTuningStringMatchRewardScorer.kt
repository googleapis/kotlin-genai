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

/** Scores parsed responses for string matching use cases. */
@Serializable
data class ReinforcementTuningStringMatchRewardScorer(

  /**
   * Wrong answer reward is returned if evaluator evaluates to `false`. All wrong answers get the
   * same reward.
   */
  val wrongAnswerReward: Double? = null,

  /**
   * Correct answer reward is returned if evaluator evaluates to `true`. All correct answers get the
   * same reward.
   */
  val correctAnswerReward: Double? = null,

  /** Uses string match expression to evaluate parsed response. */
  val stringMatchExpression: ReinforcementTuningStringMatchRewardScorerStringMatchExpression? =
    null,

  /** Uses json match expression to evaluate parsed response. */
  val jsonMatchExpression: ReinforcementTuningStringMatchRewardScorerJsonMatchExpression? = null,
)
