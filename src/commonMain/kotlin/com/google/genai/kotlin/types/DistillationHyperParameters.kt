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

/** Distillation hyperparameters for tuning. */
@Serializable
data class DistillationHyperParameters(

  /** Number of complete passes the model makes over the entire training dataset during training. */
  val epochCount: Long? = null,

  /** Multiplier for adjusting the default learning rate. */
  val learningRateMultiplier: Double? = null,

  /** The size of the adapter. Can be 'small', 'medium', or 'large'. */
  val adapterSize: AdapterSize? = null,

  /** Batch size for tuning. This feature is only available for open source models. */
  val batchSize: Int? = null,

  /** The learning rate for distillation tuning. */
  val learningRate: Double? = null,

  /**
   * Generation config for Distillation teacher model sampling. Only the following fields are
   * supported for distillation teacher samplings:
   * - temperature
   * - top_p
   * - top_k
   * - candidate_count
   * - thinking_config
   */
  val generationConfig: GenerationConfig? = null,
)
