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

/** Message contains configuration that will apply for the duration of the streaming session. */
@Serializable
data class LiveClientSetup(

  /** The fully qualified name of the publisher model or tuned model endpoint to use. */
  val model: String? = null,

  /** The generation configuration for the session. Note: only a subset of fields are supported. */
  val generationConfig: GenerationConfig? = null,

  /**
   * The user provided system instructions for the model. Note: only text should be used in parts
   * and content in each part will be in a separate paragraph.
   */
  val systemInstruction: Content? = null,

  /**
   * A list of `Tools` the model may use to generate the next response.
   *
   * A `Tool` is a piece of code that enables the system to interact with external systems to
   * perform an action, or set of actions, outside of knowledge and scope of the model.
   */
  val tools: List<Tool>? = null,

  /**
   * Configures session resumption mechanism.
   *
   * If included server will send SessionResumptionUpdate messages.
   */
  val sessionResumption: SessionResumptionConfig? = null,

  /**
   * Configures context window compression mechanism.
   *
   * If included, server will compress context window to fit into given length.
   */
  val contextWindowCompression: ContextWindowCompressionConfig? = null,

  /** The transcription of the input aligns with the input audio language. */
  val inputAudioTranscription: AudioTranscriptionConfig? = null,

  /**
   * The transcription of the output aligns with the language code specified for the output audio.
   */
  val outputAudioTranscription: AudioTranscriptionConfig? = null,

  /**
   * Configures the proactivity of the model. This allows the model to respond proactively to the
   * input and to ignore irrelevant input.
   */
  val proactivity: ProactivityConfig? = null,

  /**
   * Configures the explicit VAD signal. If enabled, the client will send vad_signal to indicate the
   * start and end of speech. This allows the server to process the audio more efficiently.
   */
  val explicitVadSignal: Boolean? = null,

  /** Configures the avatar model behavior. */
  val avatarConfig: AvatarConfig? = null,

  /** Safety settings in the request to block unsafe content in the response. */
  val safetySettings: List<SafetySetting>? = null,

  /** Configures the exchange of history between the client and the server. */
  val historyConfig: HistoryConfig? = null,
)
