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
 * Configuration for upscaling an image.
 *
 * For more information on this configuration, refer to the `Imagen API reference documentation
 * <https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/imagen-api>`_.
 */
@Serializable
data class UpscaleImageConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /** Cloud Storage URI used to store the generated images. */
  val outputGcsUri: String? = null,

  /** Filter level for safety filtering. */
  val safetyFilterLevel: SafetyFilterLevel? = null,

  /** Allows generation of people by the model. */
  val personGeneration: PersonGeneration? = null,

  /** Whether to include a reason for filtered-out images in the response. */
  val includeRaiReason: Boolean? = null,

  /** The image format that the output should be saved as. */
  val outputMimeType: String? = null,

  /** The level of compression. Only applicable if the ``output_mime_type`` is ``image/jpeg``. */
  val outputCompressionQuality: Int? = null,

  /**
   * Whether to add an image enhancing step before upscaling. It is expected to suppress the noise
   * and JPEG compression artifacts from the input image.
   */
  val enhanceInputImage: Boolean? = null,

  /**
   * With a higher image preservation factor, the original image pixels are more respected. With a
   * lower image preservation factor, the output image will have be more different from the input
   * image, but with finer details and less noise.
   */
  val imagePreservationFactor: Double? = null,

  /** User specified labels to track billing usage. */
  val labels: Map<String, String>? = null,
)
