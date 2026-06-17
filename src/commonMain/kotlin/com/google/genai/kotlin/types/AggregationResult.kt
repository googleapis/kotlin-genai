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

/** The aggregation result for a single metric. This data type is not supported in Gemini API. */
@Serializable
data class AggregationResult(

  /** Aggregation metric. */
  val aggregationMetric: AggregationMetric? = null,

  /** Results for bleu metric. */
  val bleuMetricValue: BleuMetricValue? = null,

  /** Result for code execution metric. */
  val customCodeExecutionResult: CustomCodeExecutionResult? = null,

  /** Results for exact match metric. */
  val exactMatchMetricValue: ExactMatchMetricValue? = null,

  /** Result for pairwise metric. */
  val pairwiseMetricResult: PairwiseMetricResult? = null,

  /** Result for pointwise metric. */
  val pointwiseMetricResult: PointwiseMetricResult? = null,

  /** Results for rouge metric. */
  val rougeMetricValue: RougeMetricValue? = null,
)
