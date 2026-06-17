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

/** Configuration for recontextualizing an image. */
@Serializable
data class RecontextImageConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /** Number of images to generate. */
  val numberOfImages: Int? = null,

  /**
   * The number of sampling steps. A higher value has better image quality, while a lower value has
   * better latency.
   */
  val baseSteps: Int? = null,

  /** Cloud Storage URI used to store the generated images. */
  val outputGcsUri: String? = null,

  /** Random seed for image generation. */
  val seed: Int? = null,

  /** Filter level for safety filtering. */
  val safetyFilterLevel: SafetyFilterLevel? = null,

  /** Whether allow to generate person images, and restrict to specific ages. */
  val personGeneration: PersonGeneration? = null,

  /** Whether to add a SynthID watermark to the generated images. */
  val addWatermark: Boolean? = null,

  /** MIME type of the generated image. */
  val outputMimeType: String? = null,

  /** Compression quality of the generated image (for ``image/jpeg`` only). */
  val outputCompressionQuality: Int? = null,

  /** Whether to use the prompt rewriting logic. */
  val enhancePrompt: Boolean? = null,

  /** User specified labels to track billing usage. */
  val labels: Map<String, String>? = null,
)
