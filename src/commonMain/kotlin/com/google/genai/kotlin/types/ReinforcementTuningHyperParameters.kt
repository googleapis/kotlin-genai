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

/** Hyperparameters for Reinforcement Tuning. */
@Serializable
data class ReinforcementTuningHyperParameters(

  /** Learning rate multiplier for Reinforcement Learning. */
  val learningRateMultiplier: Double? = null,

  /** Optional. Adapter size for Reinforcement Tuning. */
  val adapterSize: AdapterSize? = null,

  /**
   * Optional. Batch size for the tuning job. How many prompts to process at a train step. If not
   * set, the batch size will be determined automatically.
   */
  val batchSize: Int? = null,

  /**
   * Optional. How often at steps to save checkpoints during training. If not set, one checkpoint
   * per epoch will be set. ```total_steps = epoch_count * samples_per_prompt /
   * total_prompts_in_dataset```
   */
  val checkpointInterval: Int? = null,

  /** Optional. Number of training epoches for the tuning job. */
  val epochCount: Long? = null,

  /**
   * Optional. How often at steps to evaluate the tuning job during training. If not set, evel will
   * be run per epoch. `total_steps = epoch_count * samples_per_prompt / total_prompts_in_dataset`
   */
  val evaluateInterval: Int? = null,

  /** Optional. The maximum number of tokens to generate per prompt. Default to 32768. */
  val maxOutputTokens: Int? = null,

  /** Optional. Number of different responses to generate per prompt during tuning. */
  val samplesPerPrompt: Int? = null,

  /**
   * Optional. The thinking budget for the tuning job to optimize for (Gemini 2.5 only). * -1 means
   * dynamic thinking * 0 means no thinking * > 0 means thinking budget in tokens If not set,
   * default to -1 (dynamic thinking).
   */
  val thinkingBudget: Int? = null,

  /**
   * Indicates the maximum thinking depth during tuning. Starting from Gemini 3.5 models, the old
   * thinking_budget will no longer be supported and will result in a user error if set. Instead,
   * users should use the thinking_level parameter to control the maximum thinking depth.
   */
  val thinkingLevel: ReinforcementTuningThinkingLevel? = null,
)
