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

/** TunedModelCheckpoint for the Tuned Model of a Tuning Job. */
@Serializable
data class TunedModelCheckpoint(

  /** The ID of the checkpoint. */
  val checkpointId: String? = null,

  /** The epoch of the checkpoint. */
  val epoch: Long? = null,

  /** The step of the checkpoint. */
  val step: Long? = null,

  /**
   * The Endpoint resource name that the checkpoint is deployed to. Format:
   * `projects/{project}/locations/{location}/endpoints/{endpoint}`.
   */
  val endpoint: String? = null,
)
