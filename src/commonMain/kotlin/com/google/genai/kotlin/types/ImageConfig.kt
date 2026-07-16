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

/** The image generation configuration to be used in GenerateContentConfig. */
@Serializable
data class ImageConfig(

  /**
   * Aspect ratio of the generated images. Supported values are "1:1", "2:3", "3:2", "3:4", "4:3",
   * "9:16", "16:9", and "21:9".
   */
  val aspectRatio: String? = null,

  /**
   * Optional. Specifies the size of generated images. Supported values are `1K`, `2K`, `4K`. If not
   * specified, the model will use default value `1K`.
   */
  val imageSize: String? = null,

  /**
   * Controls the generation of people. Supported values are: ALLOW_ALL, ALLOW_ADULT, ALLOW_NONE.
   */
  val personGeneration: String? = null,

  /** MIME type of the generated image. This field is not supported in Gemini API. */
  val outputMimeType: String? = null,

  /**
   * Compression quality of the generated image (for ``image/jpeg`` only). This field is not
   * supported in Gemini API.
   */
  val outputCompressionQuality: Int? = null,

  /**
   * Optional. The image output format for generated images. This field is not supported in Gemini
   * API.
   */
  val imageOutputOptions: ImageConfigImageOutputOptions? = null,

  /**
   * Optional. Controls whether prominent people (celebrities) generation is allowed. If used with
   * personGeneration, personGeneration enum would take precedence. For instance, if ALLOW_NONE is
   * set, all person generation would be blocked. If this field is unspecified, the default behavior
   * is to allow prominent people. This field is not supported in Gemini API.
   */
  val prominentPeople: ProminentPeople? = null,
)
