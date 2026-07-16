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

/** Preference optimization tuning spec for tuning. */
@Serializable
data class PreferenceOptimizationSpec(

  /**
   * Optional. If set to true, disable intermediate checkpoints for Preference Optimization and only
   * the last checkpoint will be exported. Otherwise, enable intermediate checkpoints for Preference
   * Optimization. Default is false.
   */
  val exportLastCheckpointOnly: Boolean? = null,

  /** Optional. Hyperparameters for Preference Optimization. */
  val hyperParameters: PreferenceOptimizationHyperParameters? = null,

  /**
   * Required. Cloud Storage path to file containing training dataset for preference optimization
   * tuning. The dataset must be formatted as a JSONL file.
   */
  val trainingDatasetUri: String? = null,

  /**
   * Optional. Cloud Storage path to file containing validation dataset for preference optimization
   * tuning. The dataset must be formatted as a JSONL file.
   */
  val validationDatasetUri: String? = null,
)
