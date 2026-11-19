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

/** Text normalization mode for speech synthesis. */
@Serializable
@JvmInline
value class TextNormalization(val value: String) {
  override fun toString(): String = value

  companion object {

    /** Unspecified text normalization mode. Defaults to `AUTO`. */
    val TEXT_NORMALIZATION_UNSPECIFIED = TextNormalization("TEXT_NORMALIZATION_UNSPECIFIED")

    /**
     * Automatically determines whether to apply text normalization based on the model and speech
     * mode.
     */
    val AUTO = TextNormalization("AUTO")

    /** Enables text normalization for numbers, currencies, dates, and units. */
    val ENABLED = TextNormalization("ENABLED")

    /** Disables text normalization for pre-normalized or phonetic inputs. */
    val DISABLED = TextNormalization("DISABLED")
  }
}
