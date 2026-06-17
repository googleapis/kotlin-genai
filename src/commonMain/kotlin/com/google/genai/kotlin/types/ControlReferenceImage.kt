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
 * A control reference image.
 *
 * The image of the control reference image is either a control image provided by the user, or a
 * regular image which the backend will use to generate a control image of. In the case of the
 * latter, the enable_control_image_computation field in the config should be set to True.
 *
 * A control image is an image that represents a sketch image of areas for the model to fill in
 * based on the prompt.
 */
@Serializable
data class ControlReferenceImage(

  /** The reference image for the editing operation. */
  val referenceImage: Image? = null,

  /** The id of the reference image. */
  val referenceId: Int? = null,

  /** The type of the reference image. Only set by the SDK. */
  val referenceType: String? = null,

  /** Configuration for the control reference image. */
  val config: ControlReferenceConfig? = null,
)
