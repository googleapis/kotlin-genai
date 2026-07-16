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

/** Distillation tuning spec for tuning. */
@Serializable
data class DistillationSpec(

  /**
   * The base teacher model that is being distilled. See
   * [Supported models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
   */
  val baseTeacherModel: String? = null,

  /** Optional. Hyperparameters for Distillation. */
  val hyperParameters: DistillationHyperParameters? = null,

  /**
   * Deprecated. A path in a Cloud Storage bucket, which will be treated as the root output
   * directory of the distillation pipeline. It is used by the system to generate the paths of
   * output artifacts.
   */
  val pipelineRootDirectory: String? = null,

  /**
   * Optional. Cloud Storage path to file containing prompt dataset for distillation. The dataset
   * must be formatted as a JSONL file.
   */
  val promptDatasetUri: String? = null,

  /**
   * The student model that is being tuned, e.g., "google/gemma-2b-1.1-it". Deprecated. Use
   * base_model instead.
   */
  val studentModel: String? = null,

  /**
   * Deprecated. Cloud Storage path to file containing training dataset for tuning. The dataset must
   * be formatted as a JSONL file.
   */
  val trainingDatasetUri: String? = null,

  /**
   * The resource name of the Tuned teacher model. Format:
   * `projects/{project}/locations/{location}/models/{model}`.
   */
  val tunedTeacherModelSource: String? = null,

  /**
   * Optional. Specifies the tuning mode for distillation (sft part). This feature is only available
   * for open source models.
   */
  val tuningMode: TuningMode? = null,

  /**
   * Optional. Cloud Storage path to file containing validation dataset for tuning. The dataset must
   * be formatted as a JSONL file.
   */
  val validationDatasetUri: String? = null,
)
