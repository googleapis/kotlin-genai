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

/** Output only. The traffic type for this request. This enum is not supported in Gemini API. */
@Serializable
@JvmInline
value class TrafficType(val value: String) {
  companion object {

    /** Unspecified request traffic type. */
    val TRAFFIC_TYPE_UNSPECIFIED = TrafficType("TRAFFIC_TYPE_UNSPECIFIED")

    /** The request was processed using Pay-As-You-Go quota. */
    val ON_DEMAND = TrafficType("ON_DEMAND")

    /** Type for Priority Pay-As-You-Go traffic. */
    val ON_DEMAND_PRIORITY = TrafficType("ON_DEMAND_PRIORITY")

    /** Type for Flex traffic. */
    val ON_DEMAND_FLEX = TrafficType("ON_DEMAND_FLEX")

    /** Type for Provisioned Throughput traffic. */
    val PROVISIONED_THROUGHPUT = TrafficType("PROVISIONED_THROUGHPUT")
  }
}
