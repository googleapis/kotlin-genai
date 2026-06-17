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

/** Used to override the default configuration. */
@Serializable
data class UploadFileConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /**
   * The name of the file in the destination (e.g., 'files/sample-image'. If not provided one will
   * be generated.
   */
  val name: String? = null,

  /**
   * mime_type: The MIME type of the file. If not provided, it will be inferred from the file
   * extension.
   */
  val mimeType: String? = null,

  /** Optional display name of the file. */
  val displayName: String? = null,
)
