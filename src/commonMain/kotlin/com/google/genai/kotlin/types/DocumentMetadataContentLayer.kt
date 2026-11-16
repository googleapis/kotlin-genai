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

/** Content layers to extract from a document. */
@Serializable
@JvmInline
value class DocumentMetadataContentLayer(val value: String) {
  override fun toString(): String = value

  companion object {

    /** Default value. */
    val CONTENT_LAYER_UNSPECIFIED = DocumentMetadataContentLayer("CONTENT_LAYER_UNSPECIFIED")

    /** Rendered page images. */
    val PAGE_IMAGE = DocumentMetadataContentLayer("PAGE_IMAGE")

    /** Extracted page text. */
    val PAGE_TEXT = DocumentMetadataContentLayer("PAGE_TEXT")
  }
}
