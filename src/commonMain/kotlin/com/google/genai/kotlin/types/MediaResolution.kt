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

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/**
 * The token resolution at which input media content is sampled. This is used to control the
 * trade-off between the quality of the response and the number of tokens used to represent the
 * media. A higher resolution allows the model to perceive more detail, which can lead to a more
 * nuanced response, but it will also use more tokens. This does not affect the image dimensions
 * sent to the model.
 */
@Serializable
@JvmInline
value class MediaResolution(val value: String) {
  override fun toString(): String = value

  companion object {

    /** Media resolution has not been set. */
    val MEDIA_RESOLUTION_UNSPECIFIED = MediaResolution("MEDIA_RESOLUTION_UNSPECIFIED")

    /** Media resolution set to low (64 tokens). */
    val MEDIA_RESOLUTION_LOW = MediaResolution("MEDIA_RESOLUTION_LOW")

    /** Media resolution set to medium (256 tokens). */
    val MEDIA_RESOLUTION_MEDIUM = MediaResolution("MEDIA_RESOLUTION_MEDIUM")

    /** Media resolution set to high (zoomed reframing with 256 tokens). */
    val MEDIA_RESOLUTION_HIGH = MediaResolution("MEDIA_RESOLUTION_HIGH")
  }
}
