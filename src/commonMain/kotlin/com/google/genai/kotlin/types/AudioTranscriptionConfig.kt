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

/** The audio transcription configuration in Setup. */
@Serializable
data class AudioTranscriptionConfig(

  /** Deprecated: use LanguageAuto or LanguageHints instead. */
  val languageCodes: List<String>? = null,

  /** The model will detect the language automatically. Do not use together with LanguageHints. */
  val languageAuto: LanguageAuto? = null,

  /** Specifies one or more languages in the audio. Do not use together with LanguageAuto. */
  val languageHints: LanguageHints? = null,

  /**
   * A list of phrases used for speech adaptation, which biases the ASR model to improve recognition
   * of these specific terms.
   */
  val adaptationPhrases: List<String>? = null,

  /** Configures word-level timestamp generation. */
  val wordTimestamp: Boolean? = null,

  /** Configures speaker diarization. */
  val diarization: Boolean? = null,
)
