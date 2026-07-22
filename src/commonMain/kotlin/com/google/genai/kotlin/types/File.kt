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
import kotlinx.serialization.json.JsonElement

/** A file uploaded to the API. */
@Serializable
data class File(

  /**
   * The `File` resource name. The ID (name excluding the "files/" prefix) can contain up to 40
   * characters that are lowercase alphanumeric or dashes (-). The ID cannot start or end with a
   * dash. If the name is empty on create, a unique name will be generated. Example: `files/123-456`
   */
  val name: String? = null,

  /**
   * Optional. The human-readable display name for the `File`. The display name must be no more than
   * 512 characters in length, including spaces. Example: 'Welcome Image'
   */
  val displayName: String? = null,

  /** Output only. MIME type of the file. */
  val mimeType: String? = null,

  /** Output only. Size of the file in bytes. */
  val sizeBytes: Long? = null,

  /** Output only. The timestamp of when the `File` was created. */
  @Serializable(with = InstantSerializer::class) val createTime: Instant? = null,

  /**
   * Output only. The timestamp of when the `File` will be deleted. Only set if the `File` is
   * scheduled to expire.
   */
  @Serializable(with = InstantSerializer::class) val expirationTime: Instant? = null,

  /** Output only. The timestamp of when the `File` was last updated. */
  @Serializable(with = InstantSerializer::class) val updateTime: Instant? = null,

  /**
   * Output only. SHA-256 hash of the uploaded bytes. The hash value is encoded in base64 format.
   */
  val sha256Hash: String? = null,

  /** Output only. The URI of the `File`. */
  val uri: String? = null,

  /** Output only. The URI of the `File`, only set for downloadable (generated) files. */
  val downloadUri: String? = null,

  /** Output only. Processing state of the File. */
  val state: FileState? = null,

  /** Output only. The source of the `File`. */
  val source: FileSource? = null,

  /** Output only. Metadata for a video. */
  val videoMetadata: Map<String, JsonElement>? = null,

  /** Output only. Error status if File processing failed. */
  val error: FileStatus? = null,
)
