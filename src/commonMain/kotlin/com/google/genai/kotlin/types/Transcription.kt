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

/** Audio transcription in Server Content. */
@Serializable
data class Transcription(

  /** Optional. Transcription text. */
  val text: String? = null,

  /** Optional. The bool indicates the end of the transcription. */
  val finished: Boolean? = null,

  /** The BCP-47 language code of the transcription. */
  val languageCode: String? = null,

  /** A label identifying the speaker of this audio segment (e.g. "spk_1", "spk_2"). */
  val speakerLabel: String? = null,

  /** Detailed word-level transcriptions and timing details. */
  val words: List<WordInfo>? = null,
)
