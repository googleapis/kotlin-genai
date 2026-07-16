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

/** A pre-tuned model for continuous tuning. This data type is not supported in Gemini API. */
@Serializable
data class PreTunedModel(

  /** Output only. The name of the base model this PreTunedModel was tuned from. */
  val baseModel: String? = null,

  /** Optional. The source checkpoint id. If not specified, the default checkpoint will be used. */
  val checkpointId: String? = null,

  /**
   * The resource name of the Model. E.g., a model resource name with a specified version id or
   * alias: `projects/{project}/locations/{location}/models/{model}@{version_id}`
   * `projects/{project}/locations/{location}/models/{model}@{alias}` Or, omit the version id to use
   * the default version: `projects/{project}/locations/{location}/models/{model}`
   */
  val tunedModelName: String? = null,
)
