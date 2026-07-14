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

/** Statistics of the input text associated with the result of content embedding. */
@Serializable
data class ContentEmbeddingStatistics(

  /**
   * Gemini Enterprise Agent Platform only. If the input text was truncated due to having a length
   * longer than the allowed maximum input.
   */
  val truncated: Boolean? = null,

  /** Gemini Enterprise Agent Platform only. Number of tokens of the input text. */
  val tokenCount: Double? = null,

  /**
   * Gemini Enterprise Agent Platform only. List of modalities and their token count for the input
   * content.
   */
  val tokensDetails: List<ModalityTokenCount>? = null,
)
