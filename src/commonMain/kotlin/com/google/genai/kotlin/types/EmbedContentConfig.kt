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

/** Optional parameters for the embed_content method. */
@Serializable
data class EmbedContentConfig(

  /** Type of task for which the embedding will be used. */
  val taskType: String? = null,

  /** Title for the text. Only applicable when TaskType is `RETRIEVAL_DOCUMENT`. */
  val title: String? = null,

  /**
   * Reduced dimension for the output embedding. If set, excessive values in the output embedding
   * are truncated from the end. Supported by newer models since 2024 only. You cannot set this
   * value if using the earlier model (`models/embedding-001`).
   */
  val outputDimensionality: Int? = null,

  /** Gemini Enterprise Agent Platform only. The MIME type of the input. */
  val mimeType: String? = null,

  /**
   * Gemini Enterprise Agent Platform only. Whether to silently truncate inputs longer than the max
   * sequence length. If this option is set to false, oversized inputs will lead to an
   * INVALID_ARGUMENT error, similar to other text APIs.
   */
  val autoTruncate: Boolean? = null,

  /**
   * Gemini Enterprise Agent Platform only. Whether to enable OCR for document content. Only
   * applicable to Gemini Embedding 2 models.
   */
  val documentOcr: Boolean? = null,

  /**
   * Gemini Enterprise Agent Platform only. Whether to extract audio from video content. Only
   * applicable to Gemini Embedding 2 models.
   */
  val audioTrackExtraction: Boolean? = null,

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,
)
