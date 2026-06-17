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

/** Configuration for editing an image. */
@Serializable
data class EditImageConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /** Cloud Storage URI used to store the generated images. */
  val outputGcsUri: String? = null,

  /** Description of what to discourage in the generated images. */
  val negativePrompt: String? = null,

  /** Number of images to generate. */
  val numberOfImages: Int? = null,

  /**
   * Aspect ratio of the generated images. Supported values are "1:1", "3:4", "4:3", "9:16", and
   * "16:9".
   */
  val aspectRatio: String? = null,

  /**
   * Controls how much the model adheres to the text prompt. Large values increase output and prompt
   * alignment, but may compromise image quality.
   */
  val guidanceScale: Double? = null,

  /**
   * Random seed for image generation. This is not available when ``add_watermark`` is set to true.
   */
  val seed: Int? = null,

  /** Filter level for safety filtering. */
  val safetyFilterLevel: SafetyFilterLevel? = null,

  /** Allows generation of people by the model. */
  val personGeneration: PersonGeneration? = null,

  /**
   * Whether to report the safety scores of each generated image and the positive prompt in the
   * response.
   */
  val includeSafetyAttributes: Boolean? = null,

  /**
   * Whether to include the Responsible AI filter reason if the image is filtered out of the
   * response.
   */
  val includeRaiReason: Boolean? = null,

  /** Language of the text in the prompt. */
  val language: ImagePromptLanguage? = null,

  /** MIME type of the generated image. */
  val outputMimeType: String? = null,

  /** Compression quality of the generated image (for ``image/jpeg`` only). */
  val outputCompressionQuality: Int? = null,

  /** Whether to add a watermark to the generated images. */
  val addWatermark: Boolean? = null,

  /** User specified labels to track billing usage. */
  val labels: Map<String, String>? = null,

  /** Describes the editing mode for the request. */
  val editMode: EditMode? = null,

  /**
   * The number of sampling steps. A higher value has better image quality, while a lower value has
   * better latency.
   */
  val baseSteps: Int? = null,
)
