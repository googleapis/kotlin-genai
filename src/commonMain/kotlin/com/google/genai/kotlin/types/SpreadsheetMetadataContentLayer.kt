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

/** Content layers to extract from a spreadsheet. */
@Serializable
@JvmInline
value class SpreadsheetMetadataContentLayer(val value: String) {
  override fun toString(): String = value

  companion object {

    /** Default value. */
    val CONTENT_LAYER_UNSPECIFIED = SpreadsheetMetadataContentLayer("CONTENT_LAYER_UNSPECIFIED")

    /** Cell values, merges, and formats. */
    val DATA = SpreadsheetMetadataContentLayer("DATA")

    /** Formula expressions and their computed results. */
    val FORMULAS = SpreadsheetMetadataContentLayer("FORMULAS")

    /** A rendered image of the sheet. */
    val VISUAL = SpreadsheetMetadataContentLayer("VISUAL")
  }
}
