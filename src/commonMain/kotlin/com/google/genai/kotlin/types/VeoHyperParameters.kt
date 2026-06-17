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

/** Hyperparameters for Veo. This data type is not supported in Gemini API. */
@Serializable
data class VeoHyperParameters(

  /**
   * Optional. Number of complete passes the model makes over the entire training dataset during
   * training.
   */
  val epochCount: Long? = null,

  /** Optional. Multiplier for adjusting the default learning rate. */
  val learningRateMultiplier: Double? = null,

  /** The tuning task for Veo. */
  val tuningTask: TuningTask? = null,

  /**
   * Optional. The ratio of Google internal dataset to use in the training mixture, in range of `[0,
   * 1)`. If `0.2`, it means 20% of Google internal dataset and 80% of user dataset will be used for
   * training. If not set, the default value is 0.1.
   */
  val veoDataMixtureRatio: Double? = null,

  /** Optional. The adapter size for LoRA tuning. */
  val adapterSize: AdapterSize? = null,

  /** The speed of the tuning job. Only supported for Veo 3.0 models. */
  val tuningSpeed: TuningSpeed? = null,
)
