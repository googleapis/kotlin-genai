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
 * Dataset bucket used to create a histogram for the distribution given a population of values. This
 * data type is not supported in Gemini API.
 */
@Serializable
data class DatasetDistributionDistributionBucket(

  /** Output only. Number of values in the bucket. */
  val count: Long? = null,

  /** Output only. Left bound of the bucket. */
  val left: Double? = null,

  /** Output only. Right bound of the bucket. */
  val right: Double? = null,
)
