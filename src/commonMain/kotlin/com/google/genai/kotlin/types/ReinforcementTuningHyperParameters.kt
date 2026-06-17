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

  /** Number of training epochs for the tuning job. */
  val epochCount: Long? = null,

  /** Learning rate multiplier for Reinforcement Learning. */
  val learningRateMultiplier: Double? = null,

  /** Adapter size for Reinforcement Tuning. */
  val adapterSize: AdapterSize? = null,

  /** Number of different responses to generate per prompt during tuning. */
  val samplesPerPrompt: Int? = null,

  /**
   * Batch size for the tuning job. How many prompts to process at a train step. If not set, the
   * batch size will be determined automatically.
   */
  val batchSize: Int? = null,

  /**
   * How often (in steps) to evaluate the tuning job during training. If not set, evaluation will
   * run per epoch.
   */
  val evaluateInterval: Int? = null,

  /**
   * How often (in steps) to save checkpoints during training. If not set, one checkpoint per epoch
   * will be saved.
   */
  val checkpointInterval: Int? = null,

  /** The maximum number of tokens to generate per prompt. If not set, defaults to 32768. */
  val maxOutputTokens: Int? = null,

  /** Indicates the maximum thinking depth. Use with earlier models shall result in error. */
  val thinkingLevel: ReinforcementTuningThinkingLevel? = null,
)
