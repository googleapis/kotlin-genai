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

/** Configures the customized avatar to be used in the session. */
@Serializable
data class CustomizedAvatar(

  /** The mime type of the reference image, e.g., "image/jpeg". */
  val imageMimeType: String? = null,

  /**
   * The data of the reference image. The dimensions of the reference image should be 9:16
   * (portrait) with a minimum resolution of 704x1280.
   */
  @Serializable(with = ByteArrayAsBase64Serializer::class) val imageData: ByteArray? = null,
)
