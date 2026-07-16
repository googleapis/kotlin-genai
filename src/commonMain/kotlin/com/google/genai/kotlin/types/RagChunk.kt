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
 * A RagChunk includes the content of a chunk of a RagFile, and associated metadata. This data type
 * is not supported in Gemini API.
 */
@Serializable
data class RagChunk(

  /** The ID of the chunk. */
  val chunkId: String? = null,

  /** The ID of the file that the chunk belongs to. */
  val fileId: String? = null,

  /** If populated, represents where the chunk starts and ends in the document. */
  val pageSpan: RagChunkPageSpan? = null,

  /** The content of the chunk. */
  val text: String? = null,
)
