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
 * URI-based data. A FileData message contains a URI pointing to data of a specific media type. It
 * is used to represent images, audio, and video stored in Google Cloud Storage.
 */
@Serializable
data class FileData(

  /**
   * Optional. The display name of the file. Used to provide a label or filename to distinguish
   * files. This field is only returned in `PromptMessage` for prompt management. It is used in the
   * Gemini calls only when server side tools (`code_execution`, `google_search`, and `url_context`)
   * are enabled. This field is not supported in Gemini API.
   */
  val displayName: String? = null,

  /** Required. The URI of the file in Google Cloud Storage. */
  val fileUri: String? = null,

  /** Required. The IANA standard MIME type of the source data. */
  val mimeType: String? = null,
)
