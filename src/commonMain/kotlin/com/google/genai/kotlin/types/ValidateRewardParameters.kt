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
 * Parameters for the validate_reward method.
 *
 * Validates a reinforcement tuning reward configuration against a sample response and example
 * before creating a reinforcement tuning job.
 */
@Serializable
data class ValidateRewardParameters(

  /**
   * Required. The resource name of the Location to validate the reward in, e.g.
   * `projects/{project}/locations/{location}`.
   */
  val parent: String? = null,

  /** Required. The sample response for validating the reward configuration. */
  val sampleResponse: Content? = null,

  /** Required. The example to validate the reward configuration. */
  val example: ReinforcementTuningExample? = null,

  /**
   * Single reward function configuration for reinforcement tuning. Mutually exclusive with
   * composite_reward_config.
   */
  val singleRewardConfig: SingleReinforcementTuningRewardConfig? = null,

  /**
   * Composite reward function configuration for reinforcement tuning. Mutually exclusive with
   * single_reward_config.
   */
  val compositeRewardConfig: CompositeReinforcementTuningRewardConfig? = null,

  /** Optional parameters for the request. */
  val config: ValidateRewardConfig? = null,
)
