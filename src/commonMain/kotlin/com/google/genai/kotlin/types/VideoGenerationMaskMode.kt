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

/** Enum for the mask mode of a video generation mask. */
@Serializable
@JvmInline
value class VideoGenerationMaskMode(val value: String) {
  companion object {

    /**
     * The image mask contains a masked rectangular region which is applied on the first frame of
     * the input video. The object described in the prompt is inserted into this region and will
     * appear in subsequent frames.
     */
    val INSERT = VideoGenerationMaskMode("INSERT")

    /**
     * The image mask is used to determine an object in the first video frame to track. This object
     * is removed from the video.
     */
    val REMOVE = VideoGenerationMaskMode("REMOVE")

    /**
     * The image mask is used to determine a region in the video. Objects in this region will be
     * removed.
     */
    val REMOVE_STATIC = VideoGenerationMaskMode("REMOVE_STATIC")

    /**
     * The image mask contains a masked rectangular region where the input video will go. The
     * remaining area will be generated. Video masks are not supported.
     */
    val OUTPAINT = VideoGenerationMaskMode("OUTPAINT")
  }
}
