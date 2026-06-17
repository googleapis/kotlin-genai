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
 * A mask reference image.
 *
 * This encapsulates either a mask image provided by the user and configs for the user provided
 * mask, or only config parameters for the model to generate a mask.
 *
 * A mask image is an image whose non-zero values indicate where to edit the base image. If the user
 * provides a mask image, the mask must be in the same dimensions as the raw image.
 */
@Serializable
data class MaskReferenceImage(

  /** The reference image for the editing operation. */
  val referenceImage: Image? = null,

  /** The id of the reference image. */
  val referenceId: Int? = null,

  /** The type of the reference image. Only set by the SDK. */
  val referenceType: String? = null,

  /** Configuration for the mask reference image. */
  val config: MaskReferenceConfig? = null,
)
