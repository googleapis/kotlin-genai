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

/** The number of thoughts tokens that the model should generate. */
@Serializable
@JvmInline
value class ThinkingLevel(val value: String) {
  companion object {

    /** Unspecified thinking level. */
    val THINKING_LEVEL_UNSPECIFIED = ThinkingLevel("THINKING_LEVEL_UNSPECIFIED")

    /** MINIMAL thinking level. */
    val MINIMAL = ThinkingLevel("MINIMAL")

    /** Low thinking level. */
    val LOW = ThinkingLevel("LOW")

    /** Medium thinking level. */
    val MEDIUM = ThinkingLevel("MEDIUM")

    /** High thinking level. */
    val HIGH = ThinkingLevel("HIGH")
  }
}
