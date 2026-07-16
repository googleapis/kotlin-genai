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

/** Distribution computed over a tuning dataset. This data type is not supported in Gemini API. */
@Serializable
data class DatasetDistribution(

  /** Output only. Defines the histogram bucket. */
  val buckets: List<DatasetDistributionDistributionBucket>? = null,

  /** Output only. The maximum of the population values. */
  val max: Double? = null,

  /** Output only. The arithmetic mean of the values in the population. */
  val mean: Double? = null,

  /** Output only. The median of the values in the population. */
  val median: Double? = null,

  /** Output only. The minimum of the population values. */
  val min: Double? = null,

  /** Output only. The 5th percentile of the values in the population. */
  val p5: Double? = null,

  /** Output only. The 95th percentile of the values in the population. */
  val p95: Double? = null,

  /** Output only. Sum of a given population of values. */
  val sum: Double? = null,
)
