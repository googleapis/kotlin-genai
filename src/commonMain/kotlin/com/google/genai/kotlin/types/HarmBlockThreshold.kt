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
 * The threshold for blocking content. If the harm probability exceeds this threshold, the content
 * will be blocked.
 */
@Serializable
@JvmInline
value class HarmBlockThreshold(val value: String) {
  companion object {

    /** The harm block threshold is unspecified. */
    val HARM_BLOCK_THRESHOLD_UNSPECIFIED = HarmBlockThreshold("HARM_BLOCK_THRESHOLD_UNSPECIFIED")

    /** Block content with a low harm probability or higher. */
    val BLOCK_LOW_AND_ABOVE = HarmBlockThreshold("BLOCK_LOW_AND_ABOVE")

    /** Block content with a medium harm probability or higher. */
    val BLOCK_MEDIUM_AND_ABOVE = HarmBlockThreshold("BLOCK_MEDIUM_AND_ABOVE")

    /** Block content with a high harm probability. */
    val BLOCK_ONLY_HIGH = HarmBlockThreshold("BLOCK_ONLY_HIGH")

    /** Do not block any content, regardless of its harm probability. */
    val BLOCK_NONE = HarmBlockThreshold("BLOCK_NONE")

    /** Turn off the safety filter entirely. */
    val OFF = HarmBlockThreshold("OFF")
  }
}
