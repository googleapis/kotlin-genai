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

/** A tuned machine learning model. */
@Serializable
data class TunedModelInfo(

  /** ID of the base model that you want to tune. */
  val baseModel: String? = null,

  /** Date and time when the base model was created. */
  @Serializable(with = InstantSerializer::class) val createTime: Instant? = null,

  /** Date and time when the base model was last updated. */
  @Serializable(with = InstantSerializer::class) val updateTime: Instant? = null,
)
