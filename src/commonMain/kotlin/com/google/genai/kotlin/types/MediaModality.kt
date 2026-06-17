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

/** The modality that this token count applies to. */
@Serializable
@JvmInline
value class MediaModality(val value: String) {
  companion object {

    /** When a modality is not specified, it is treated as `TEXT`. */
    val MODALITY_UNSPECIFIED = MediaModality("MODALITY_UNSPECIFIED")

    /** The `Part` contains plain text. */
    val TEXT = MediaModality("TEXT")

    /** The `Part` contains an image. */
    val IMAGE = MediaModality("IMAGE")

    /** The `Part` contains a video. */
    val VIDEO = MediaModality("VIDEO")

    /** The `Part` contains audio. */
    val AUDIO = MediaModality("AUDIO")

    /** The `Part` contains a document, such as a PDF. */
    val DOCUMENT = MediaModality("DOCUMENT")
  }
}
