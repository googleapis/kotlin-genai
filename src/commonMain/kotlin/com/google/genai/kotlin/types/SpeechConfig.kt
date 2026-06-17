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

/** Config for speech generation and transcription. */
@Serializable
data class SpeechConfig(

  /** The configuration in case of single-voice output. */
  val voiceConfig: VoiceConfig? = null,

  /** Optional. The language code (ISO 639-1) for the speech synthesis. */
  val languageCode: String? = null,

  /**
   * The configuration for a multi-speaker text-to-speech request. This field is mutually exclusive
   * with `voice_config`.
   */
  val multiSpeakerVoiceConfig: MultiSpeakerVoiceConfig? = null,
)
