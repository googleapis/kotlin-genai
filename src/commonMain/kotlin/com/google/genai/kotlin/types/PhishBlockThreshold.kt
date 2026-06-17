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

/**
 * Sites with confidence level chosen & above this value will be blocked from the search results.
 * This enum is not supported in Gemini API.
 */
@Serializable
@JvmInline
value class PhishBlockThreshold(val value: String) {
  companion object {

    /** Defaults to unspecified. */
    val PHISH_BLOCK_THRESHOLD_UNSPECIFIED = PhishBlockThreshold("PHISH_BLOCK_THRESHOLD_UNSPECIFIED")

    /** Blocks Low and above confidence URL that is risky. */
    val BLOCK_LOW_AND_ABOVE = PhishBlockThreshold("BLOCK_LOW_AND_ABOVE")

    /** Blocks Medium and above confidence URL that is risky. */
    val BLOCK_MEDIUM_AND_ABOVE = PhishBlockThreshold("BLOCK_MEDIUM_AND_ABOVE")

    /** Blocks High and above confidence URL that is risky. */
    val BLOCK_HIGH_AND_ABOVE = PhishBlockThreshold("BLOCK_HIGH_AND_ABOVE")

    /** Blocks Higher and above confidence URL that is risky. */
    val BLOCK_HIGHER_AND_ABOVE = PhishBlockThreshold("BLOCK_HIGHER_AND_ABOVE")

    /** Blocks Very high and above confidence URL that is risky. */
    val BLOCK_VERY_HIGH_AND_ABOVE = PhishBlockThreshold("BLOCK_VERY_HIGH_AND_ABOVE")

    /** Blocks Extremely high confidence URL that is risky. */
    val BLOCK_ONLY_EXTREMELY_HIGH = PhishBlockThreshold("BLOCK_ONLY_EXTREMELY_HIGH")
  }
}
