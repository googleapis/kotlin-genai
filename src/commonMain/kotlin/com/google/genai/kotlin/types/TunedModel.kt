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

/** TunedModel for the Tuned Model of a Tuning Job. */
@Serializable
data class TunedModel(

  /**
   * Output only. The resource name of the TunedModel. Format:
   * `projects/{project}/locations/{location}/models/{model}@{version_id}` When tuning from a base
   * model, the version_id will be 1. For continuous tuning, the version id will be incremented by 1
   * from the last version id in the parent model. E.g.,
   * `projects/{project}/locations/{location}/models/{model}@{last_version_id + 1}`
   */
  val model: String? = null,

  /**
   * Output only. A resource name of an Endpoint. Format:
   * `projects/{project}/locations/{location}/endpoints/{endpoint}`.
   */
  val endpoint: String? = null,

  /**
   * The checkpoints associated with this TunedModel. This field is only populated for tuning jobs
   * that enable intermediate checkpoints.
   */
  val checkpoints: List<TunedModelCheckpoint>? = null,
)
