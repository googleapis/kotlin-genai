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

/** Reinforcement tuning spec for tuning. */
@Serializable
data class ReinforcementTuningSpec(

  /** Composite reward function configuration for reinforcement tuning. */
  val compositeRewardConfig: CompositeReinforcementTuningRewardConfig? = null,

  /**
   * Cloud Storage path to the file containing training dataset for tuning. The dataset must be
   * formatted as a JSONL file.
   */
  val trainingDatasetUri: String? = null,

  /**
   * Cloud Storage path to the file containing validation dataset for tuning. The dataset must be
   * formatted as a JSONL file.
   */
  val validationDatasetUri: String? = null,

  /** Additional hyper-parameters to use during tuning. */
  val hyperParameters: ReinforcementTuningHyperParameters? = null,

  /** Single reward function configuration for reinforcement tuning. */
  val singleRewardConfig: SingleReinforcementTuningRewardConfig? = null,
)
