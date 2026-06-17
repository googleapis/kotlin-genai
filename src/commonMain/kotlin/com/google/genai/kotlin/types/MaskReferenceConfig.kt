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

/** Configuration for a Mask reference image. */
@Serializable
data class MaskReferenceConfig(

  /**
   * Prompts the model to generate a mask instead of you needing to provide one (unless
   * MASK_MODE_USER_PROVIDED is used).
   */
  val maskMode: MaskReferenceMode? = null,

  /**
   * A list of up to 5 class ids to use for semantic segmentation. Automatically creates an image
   * mask based on specific objects.
   */
  val segmentationClasses: List<Int>? = null,

  /** Dilation percentage of the mask provided. Float between 0 and 1. */
  val maskDilation: Double? = null,
)
