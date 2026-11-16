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
 * Metadata describes the input document content.
 *
 * Set this in `Part.document_metadata` or `FunctionResponsePart.document_metadata` when
 * `inline_data` or `file_data` contains a document to configure static document preprocessing.
 */
@Serializable
data class DocumentMetadata(

  /** 1-indexed inclusive start page. */
  val startPage: Int? = null,

  /** 1-indexed inclusive end page. */
  val endPage: Int? = null,

  /**
   * Content layers to return. If empty, defaults to [PAGE_IMAGE] when DocumentMetadata is set. When
   * DocumentMetadata is not set, default document processing is used (for example, page images for
   * PDFs).
   */
  val contentLayers: List<DocumentMetadataContentLayer>? = null,
)
