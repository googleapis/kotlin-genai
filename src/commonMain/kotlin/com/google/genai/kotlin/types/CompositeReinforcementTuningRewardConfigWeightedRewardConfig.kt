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

/** Composite reinforcement tuning reward config weighted reward config. */
@Serializable
data class CompositeReinforcementTuningRewardConfigWeightedRewardConfig(

  /** Single reward configuration. */
  val rewardConfig: SingleReinforcementTuningRewardConfig? = null,

  /**
   * How much this single reward contributes to the total overall reward. Total reward is a linear
   * combination of single rewards with their corresponding weights, i.e., ``` total_reward = (
   * weight_a * reward_a + weight_b * reward_b + ... ) / (weight_a + weight_b + ...) ```
   */
  val weight: Double? = null,
)
