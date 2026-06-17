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

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/** Aggregation metric. This enum is not supported in Gemini API. */
@Serializable
@JvmInline
value class AggregationMetric(val value: String) {
  companion object {

    /** Unspecified aggregation metric. */
    val AGGREGATION_METRIC_UNSPECIFIED = AggregationMetric("AGGREGATION_METRIC_UNSPECIFIED")

    /** Average aggregation metric. Not supported for Pairwise metric. */
    val AVERAGE = AggregationMetric("AVERAGE")

    /** Mode aggregation metric. */
    val MODE = AggregationMetric("MODE")

    /** Standard deviation aggregation metric. Not supported for pairwise metric. */
    val STANDARD_DEVIATION = AggregationMetric("STANDARD_DEVIATION")

    /** Variance aggregation metric. Not supported for pairwise metric. */
    val VARIANCE = AggregationMetric("VARIANCE")

    /** Minimum aggregation metric. Not supported for pairwise metric. */
    val MINIMUM = AggregationMetric("MINIMUM")

    /** Maximum aggregation metric. Not supported for pairwise metric. */
    val MAXIMUM = AggregationMetric("MAXIMUM")

    /** Median aggregation metric. Not supported for pairwise metric. */
    val MEDIAN = AggregationMetric("MEDIAN")

    /** 90th percentile aggregation metric. Not supported for pairwise metric. */
    val PERCENTILE_P90 = AggregationMetric("PERCENTILE_P90")

    /** 95th percentile aggregation metric. Not supported for pairwise metric. */
    val PERCENTILE_P95 = AggregationMetric("PERCENTILE_P95")

    /** 99th percentile aggregation metric. Not supported for pairwise metric. */
    val PERCENTILE_P99 = AggregationMetric("PERCENTILE_P99")
  }
}
