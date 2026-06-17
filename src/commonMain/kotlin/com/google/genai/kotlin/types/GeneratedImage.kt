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

/** An output image. */
@Serializable
data class GeneratedImage(

  /** The output image data. */
  val image: Image? = null,

  /** Responsible AI filter reason if the image is filtered out of the response. */
  val raiFilteredReason: String? = null,

  /** Safety attributes of the image. Lists of RAI categories and their scores of each content. */
  val safetyAttributes: SafetyAttributes? = null,

  /** The rewritten prompt used for the image generation if the prompt enhancer is enabled. */
  val enhancedPrompt: String? = null,
)
