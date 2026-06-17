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

/** URI based data for function response. This data type is not supported in Gemini API. */
@Serializable
data class FunctionResponseFileData(

  /** Required. URI. */
  val fileUri: String? = null,

  /** Required. The IANA standard MIME type of the source data. */
  val mimeType: String? = null,

  /**
   * Optional. Display name of the file data. Used to provide a label or filename to distinguish
   * file datas. This field is only returned in PromptMessage for prompt management. It is currently
   * used in the Gemini GenerateContent calls only when server side tools (code_execution,
   * google_search, and url_context) are enabled.
   */
  val displayName: String? = null,
)
