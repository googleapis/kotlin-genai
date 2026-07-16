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

/** Hyperparameters for SFT. This data type is not supported in Gemini API. */
@Serializable
data class SupervisedHyperParameters(

  /** Optional. Adapter size for tuning. */
  val adapterSize: AdapterSize? = null,

  /** Optional. Batch size for tuning. This feature is only available for open source models. */
  val batchSize: Long? = null,

  /**
   * Optional. Number of complete passes the model makes over the entire training dataset during
   * training.
   */
  val epochCount: Long? = null,

  /**
   * Optional. Learning rate for tuning. Mutually exclusive with `learning_rate_multiplier`. This
   * feature is only available for open source models.
   */
  val learningRate: Double? = null,

  /**
   * Optional. Multiplier for adjusting the default learning rate. Mutually exclusive with
   * `learning_rate`. This feature is only available for 1P models.
   */
  val learningRateMultiplier: Double? = null,
)
