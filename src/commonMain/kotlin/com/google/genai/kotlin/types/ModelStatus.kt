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

/**
 * The status of the underlying model. This is used to indicate the stage of the underlying model
 * and the retirement time if applicable. This data type is not supported in Vertex AI.
 */
@Serializable
data class ModelStatus(

  /** A message explaining the model status. */
  val message: String? = null,

  /** The stage of the underlying model. */
  val modelStage: ModelStage? = null,

  /** The time at which the model will be retired. */
  @Serializable(with = InstantSerializer::class) val retirementTime: Instant? = null,
)
