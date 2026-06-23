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

/** Configuration for image-specific output formatting. */
@Serializable
data class ImageResponseFormat(

  /** Optional. The aspect ratio for the image output. */
  val aspectRatio: AspectRatio? = null,

  /** Optional. Delivery mode for the generated content. */
  val delivery: Delivery? = null,

  /** Optional. The size of the image output. */
  val imageSize: ImageSize? = null,

  /** Optional. The MIME type of the image output. */
  val mimeType: String? = null,
)
