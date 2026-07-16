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
 * ReinforcementTuningStringMatchRewardScorer is used to score parsed responses for string matching
 * use cases. For example, for math problems, users can use string match scorer to check if the
 * correct exact answer is generated. Note: Reward returned by the string match reward function is
 * clipped to be within `[-1, 1]` if wrongAnswerReward or correctAnswerReward are beyond the range,
 * i.e., `reward = max(min(reward, 1.0), -1.0)`. This data type is not supported in Gemini API.
 */
@Serializable
data class ReinforcementTuningStringMatchRewardScorer(

  /**
   * Correct answer rewawrd is returned if the parsed response is evaluated as `true`. All correct
   * answers get the same reward.
   */
  val correctAnswerReward: Double? = null,

  /** Uses json match expression to evaluate parsed response. */
  val jsonMatchExpression: ReinforcementTuningStringMatchRewardScorerJsonMatchExpression? = null,

  /** Uses string match expression to evaluate parsed response. */
  val stringMatchExpression: ReinforcementTuningStringMatchRewardScorerStringMatchExpression? =
    null,

  /**
   * Wrong answer reward is returned if the parsed response is evaluated as `false`. All wrong
   * answers get the same reward.
   */
  val wrongAnswerReward: Double? = null,
)
