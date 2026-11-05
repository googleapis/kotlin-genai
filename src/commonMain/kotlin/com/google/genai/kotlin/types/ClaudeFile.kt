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
 * A file that can be referenced by ID from subsequent Claude model requests. The JSON
 * representation of this message matches the Claude Files API File object field-for-field, so that
 * code written against that API parses these responses unchanged. This data type is not supported
 * in Gemini API.
 */
@Serializable
data class ClaudeFile(

  /** Output only. The time at which the file was created. */
  @Serializable(with = InstantSerializer::class) val createdAt: Instant? = null,

  /**
   * Output only. Whether the contents of this file can be retrieved with `DownloadFile`. False for
   * user-uploaded files, true for tool-generated ones.
   */
  val downloadable: Boolean? = null,

  /**
   * Output only. The time at which the file expires and is deleted, set from the
   * `expires_in_seconds` upload form field. Unset for files that do not expire, which is serialised
   * as `null`.
   */
  @Serializable(with = InstantSerializer::class) val expiresAt: Instant? = null,

  /**
   * Output only. The original name of the uploaded file, taken from the `Content-Disposition`
   * header on upload. Between 1 and 255 characters. Control characters and the characters
   * `<>:"|?*\/` are not accepted.
   */
  val filename: String? = null,

  /**
   * Output only. The server-assigned identifier of the file, for example `file_abc123`. This is the
   * ID to pass in subsequent model requests.
   */
  val id: String? = null,

  /**
   * Output only. The media type of the file, taken from the `Content-Type` header of the file part
   * on upload.
   */
  val mimeType: String? = null,

  /** Output only. The size of the file in bytes. */
  val sizeBytes: Int? = null,

  /** Output only. The object type. Always `file`. */
  val type: String? = null,
)
