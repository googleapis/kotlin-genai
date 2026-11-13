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

/**
 * Metadata describes the input spreadsheet content.
 *
 * Set this in `Part.spreadsheet_metadata` or `FunctionResponsePart.spreadsheet_metadata` when
 * `inline_data` or `file_data` contains a spreadsheet to configure static spreadsheet
 * preprocessing.
 */
@Serializable
data class SpreadsheetMetadata(

  /**
   * The range of cells to extract, in A1 notation (e.g. "A1:D10"). If not set, the full occupied
   * area of the sheet is used.
   */
  val visibleCells: String? = null,

  /**
   * The 1-indexed index of the sheet to extract, in workbook order. If not set, the first sheet is
   * used.
   */
  val sheetIndex: Int? = null,

  /**
   * Content layers to return. If empty, defaults to [DATA, FORMULAS] when SpreadsheetMetadata is
   * set.
   */
  val contentLayers: List<SpreadsheetMetadataContentLayer>? = null,
)
