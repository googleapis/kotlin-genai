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

/** Metadata on the usage of the cached content. */
@Serializable
data class CachedContentUsageMetadata(

  /** Duration of audio in seconds. This field is not supported in Gemini API. */
  val audioDurationSeconds: Int? = null,

  /** Number of images. This field is not supported in Gemini API. */
  val imageCount: Int? = null,

  /** Number of text characters. This field is not supported in Gemini API. */
  val textCount: Int? = null,

  /** Total number of tokens that the cached content consumes. */
  val totalTokenCount: Int? = null,

  /** Duration of video in seconds. This field is not supported in Gemini API. */
  val videoDurationSeconds: Int? = null,
)
