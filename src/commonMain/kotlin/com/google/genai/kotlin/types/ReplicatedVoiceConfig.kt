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

/** The configuration for the replicated voice to use. */
@Serializable
data class ReplicatedVoiceConfig(

  /**
   * The mimetype of the voice sample. The only currently supported value is `audio/wav`. This
   * represents 16-bit signed little-endian wav data, with a 24kHz sampling rate.
   */
  val mimeType: String? = null,

  /** The sample of the custom voice. */
  @Serializable(with = ByteArrayAsBase64Serializer::class) val voiceSampleAudio: ByteArray? = null,
)
