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

/** Tuning Spec for Veo LoRA Model Tuning. This data type is not supported in Gemini API. */
@Serializable
data class VeoLoraTuningSpec(

  /** Optional. Hyperparameters for Veo LoRA. */
  val hyperParameters: VeoHyperParameters? = null,

  /**
   * Required. Training dataset used for tuning. The dataset can be specified as either a Cloud
   * Storage path to a JSONL file or as the resource name of a Vertex Multimodal Dataset.
   */
  val trainingDatasetUri: String? = null,

  /**
   * Optional. Validation dataset used for tuning. The dataset can be specified as either a Cloud
   * Storage path to a JSONL file or as the resource name of a Vertex Multimodal Dataset.
   */
  val validationDatasetUri: String? = null,

  /** Optional. The orientation of the video. Defaults to LANDSCAPE. */
  val videoOrientation: VideoOrientation? = null,
)
