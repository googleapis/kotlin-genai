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

/** Match operation to use for evaluation. */
@Serializable
@JvmInline
value class MatchOperation(val value: String) {
  companion object {

    /** Default value. This value is unused. */
    val MATCH_OPERATION_UNSPECIFIED = MatchOperation("MATCH_OPERATION_UNSPECIFIED")

    /** Equivalent to GoogleSQL `REGEX_CONTAINS(target, expression)`. */
    val REGEX_CONTAINS = MatchOperation("REGEX_CONTAINS")

    /** `expression` is a substring of target. */
    val PARTIAL_MATCH = MatchOperation("PARTIAL_MATCH")

    /** `expression` is an exact match of target. */
    val EXACT_MATCH = MatchOperation("EXACT_MATCH")
  }
}
