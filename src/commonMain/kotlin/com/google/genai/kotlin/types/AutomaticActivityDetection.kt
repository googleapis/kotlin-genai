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

/** Configures automatic detection of activity. */
@Serializable
data class AutomaticActivityDetection(

  /**
   * If enabled, detected voice and text input count as activity. If disabled, the client must send
   * activity signals.
   */
  val disabled: Boolean? = null,

  /** Determines how likely speech is to be detected. */
  val startOfSpeechSensitivity: StartSensitivity? = null,

  /** Determines how likely detected speech is ended. */
  val endOfSpeechSensitivity: EndSensitivity? = null,

  /**
   * The required duration of detected speech before start-of-speech is committed. The lower this
   * value the more sensitive the start-of-speech detection is and the shorter speech can be
   * recognized. However, this also increases the probability of false positives.
   */
  val prefixPaddingMs: Int? = null,

  /**
   * The required duration of detected non-speech (e.g. silence) before end-of-speech is committed.
   * The larger this value, the longer speech gaps can be without interrupting the user's activity
   * but this will increase the model's latency.
   */
  val silenceDurationMs: Int? = null,
)
