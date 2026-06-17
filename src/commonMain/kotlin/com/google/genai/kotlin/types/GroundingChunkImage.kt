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
 * An `Image` chunk is a piece of evidence that comes from an image search result. It contains the
 * URI of the image search result and the URI of the image. This is used to provide the user with a
 * link to the source of the information.
 */
@Serializable
data class GroundingChunkImage(

  /** The URI of the image search result page. */
  val sourceUri: String? = null,

  /** The URI of the image. */
  val imageUri: String? = null,

  /** The title of the image search result page. */
  val title: String? = null,

  /** The domain of the image search result page. */
  val domain: String? = null,
)
