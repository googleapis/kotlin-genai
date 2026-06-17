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

/** Segment of the content this support belongs to. */
@Serializable
data class Segment(

  /**
   * Output only. Start index in the given Part, measured in bytes.
   *
   * Offset from the start of the Part, inclusive, starting at zero.
   */
  val startIndex: Int? = null,

  /**
   * Output only. End index in the given Part, measured in bytes.
   *
   * Offset from the start of the Part, exclusive, starting at zero.
   */
  val endIndex: Int? = null,

  /** Output only. The index of a Part object within its parent Content object. */
  val partIndex: Int? = null,

  /** Output only. The text corresponding to the segment from the response. */
  val text: String? = null,
)
