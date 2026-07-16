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
 * The orientation of the video. Defaults to LANDSCAPE. This enum is not supported in Gemini API.
 */
@Serializable
@JvmInline
value class VideoOrientation(val value: String) {
  companion object {

    /** Unspecified video orientation. Defaults to landscape. */
    val VIDEO_ORIENTATION_UNSPECIFIED = VideoOrientation("VIDEO_ORIENTATION_UNSPECIFIED")

    /** Landscape orientation (e.g. 16:9, 1280x720). */
    val LANDSCAPE = VideoOrientation("LANDSCAPE")

    /** Portrait orientation (e.g. 9:16, 720x1280). */
    val PORTRAIT = VideoOrientation("PORTRAIT")
  }
}
