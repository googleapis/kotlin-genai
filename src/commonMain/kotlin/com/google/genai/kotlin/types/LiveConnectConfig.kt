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

/** Session config for the API connection. */
@Serializable
data class LiveConnectConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /**
   * The requested modalities of the response. Represents the set of modalities that the model can
   * return. Defaults to AUDIO if not specified.
   */
  val responseModalities: List<Modality>? = null,

  /**
   * Value that controls the degree of randomness in token selection. Lower temperatures are good
   * for prompts that require a less open-ended or creative response, while higher temperatures can
   * lead to more diverse or creative results.
   */
  val temperature: Double? = null,

  /**
   * Tokens are selected from the most to least probable until the sum of their probabilities equals
   * this value. Use a lower value for less random responses and a higher value for more random
   * responses.
   */
  val topP: Double? = null,

  /**
   * For each token selection step, the ``top_k`` tokens with the highest probabilities are sampled.
   * Then tokens are further filtered based on ``top_p`` with the final token selected using
   * temperature sampling. Use a lower number for less random responses and a higher number for more
   * random responses.
   */
  val topK: Double? = null,

  /** Maximum number of tokens that can be generated in the response. */
  val maxOutputTokens: Int? = null,

  /** If specified, the media resolution specified will be used. */
  val mediaResolution: MediaResolution? = null,

  /**
   * When ``seed`` is fixed to a specific number, the model makes a best effort to provide the same
   * response for repeated requests. By default, a random number is used.
   */
  val seed: Int? = null,

  /** The speech generation configuration. */
  val speechConfig: SpeechConfig? = null,

  /**
   * Config for thinking features. An error will be returned if this field is set for models that
   * don't support thinking.
   */
  val thinkingConfig: ThinkingConfig? = null,

  /** If enabled, the model will detect emotions and adapt its responses accordingly. */
  val enableAffectiveDialog: Boolean? = null,

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
   * If included the server will send SessionResumptionUpdate messages.
   */
  val sessionResumption: SessionResumptionConfig? = null,

  /** The transcription of the input aligns with the input audio language. */
  val inputAudioTranscription: AudioTranscriptionConfig? = null,

  /**
   * The transcription of the output aligns with the language code specified for the output audio.
   */
  val outputAudioTranscription: AudioTranscriptionConfig? = null,

  /** Configures the realtime input behavior in BidiGenerateContent. */
  val realtimeInputConfig: RealtimeInputConfig? = null,

  /**
   * Configures context window compression mechanism.
   *
   * If included, server will compress context window to fit into given length.
   */
  val contextWindowCompression: ContextWindowCompressionConfig? = null,

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

  /** Config for translation. */
  val translationConfig: TranslationConfig? = null,
)
