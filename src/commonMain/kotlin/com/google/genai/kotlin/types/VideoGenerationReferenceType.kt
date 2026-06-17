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

/** Enum for the reference type of a video generation reference image. */
@Serializable
@JvmInline
value class VideoGenerationReferenceType(val value: String) {
  companion object {

    /**
     * A reference image that provides assets to the generated video, such as the scene, an object,
     * a character, etc.
     */
    val ASSET = VideoGenerationReferenceType("ASSET")

    /**
     * A reference image that provides aesthetics including colors, lighting, texture, etc., to be
     * used as the style of the generated video, such as 'anime', 'photography', 'origami', etc.
     */
    val STYLE = VideoGenerationReferenceType("STYLE")
  }
}
