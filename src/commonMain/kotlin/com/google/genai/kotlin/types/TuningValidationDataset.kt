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

/**  */
@Serializable
data class TuningValidationDataset(

  /** GCS URI of the file containing validation dataset in JSONL format. */
  val gcsUri: String? = null,

  /**
   * The resource name of the Gemini Enterprise Agent Platform (previously known as Vertex AI)
   * Multimodal Dataset that is used as validation dataset. Example:
   * 'projects/my-project-id-or-number/locations/my-location/datasets/my-dataset-id'.
   */
  val vertexDatasetResource: String? = null,
)
