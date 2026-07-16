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

/** Generation config. */
@Serializable
data class GenerationConfig(

  /** Optional. Config for model selection. */
  val modelSelectionConfig: ModelSelectionConfig? = null,

  /**
   * Output schema of the generated response. This is an alternative to `response_schema` that
   * accepts [JSON Schema](https://json-schema.org/).
   */
  val responseJsonSchema: JsonElement? = null,

  /** Optional. Configuration for audio transcription (speech recognition). */
  val audioTranscriptionConfig: AudioTranscriptionConfig? = null,

  /**
   * Optional. If enabled, audio timestamps will be included in the request to the model. This can
   * be useful for synchronizing audio with other modalities in the response. This field is not
   * supported in Gemini API.
   */
  val audioTimestamp: Boolean? = null,

  /**
   * Optional. The number of candidate responses to generate. A higher `candidate_count` can provide
   * more options to choose from, but it also consumes more resources. This can be useful for
   * generating a variety of responses and selecting the best one.
   */
  val candidateCount: Int? = null,

  /**
   * Optional. If enabled, the model will detect emotions and adapt its responses accordingly. For
   * example, if the model detects that the user is frustrated, it may provide a more empathetic
   * response. This field is not supported in Gemini API.
   */
  val enableAffectiveDialog: Boolean? = null,

  /**
   * Optional. Penalizes tokens based on their frequency in the generated text. A positive value
   * helps to reduce the repetition of words and phrases. Valid values can range from [-2.0, 2.0].
   */
  val frequencyPenalty: Double? = null,

  /**
   * Optional. The number of top log probabilities to return for each token. This can be used to see
   * which other tokens were considered likely candidates for a given position. A higher value will
   * return more options, but it will also increase the size of the response.
   */
  val logprobs: Int? = null,

  /**
   * Optional. The maximum number of tokens to generate in the response. A token is approximately
   * four characters. The default value varies by model. This parameter can be used to control the
   * length of the generated text and prevent overly long responses.
   */
  val maxOutputTokens: Int? = null,

  /**
   * Optional. The token resolution at which input media content is sampled. This is used to control
   * the trade-off between the quality of the response and the number of tokens used to represent
   * the media. A higher resolution allows the model to perceive more detail, which can lead to a
   * more nuanced response, but it will also use more tokens. This does not affect the image
   * dimensions sent to the model.
   */
  val mediaResolution: MediaResolution? = null,

  /**
   * Optional. Penalizes tokens that have already appeared in the generated text. A positive value
   * encourages the model to generate more diverse and less repetitive text. Valid values can range
   * from [-2.0, 2.0].
   */
  val presencePenalty: Double? = null,

  /**
   * Optional. New response format field for the model to configure output formatting and delivery.
   */
  val responseFormat: List<ResponseFormat>? = null,

  /**
   * Optional. If set to true, the log probabilities of the output tokens are returned. Log
   * probabilities are the logarithm of the probability of a token appearing in the output. A higher
   * log probability means the token is more likely to be generated. This can be useful for
   * analyzing the model's confidence in its own output and for debugging.
   */
  val responseLogprobs: Boolean? = null,

  /**
   * Optional. The IANA standard MIME type of the response. The model will generate output that
   * conforms to this MIME type. Supported values include 'text/plain' (default) and
   * 'application/json'. The model needs to be prompted to output the appropriate response type,
   * otherwise the behavior is undefined. Deprecated: Use `response_format` instead.
   */
  val responseMimeType: String? = null,

  /**
   * Optional. The modalities of the response. The model will generate a response that includes all
   * the specified modalities. For example, if this is set to `[TEXT, IMAGE]`, the response will
   * include both text and an image.
   */
  val responseModalities: List<Modality>? = null,

  /**
   * Optional. Lets you to specify a schema for the model's response, ensuring that the output
   * conforms to a particular structure. This is useful for generating structured data such as JSON.
   * The schema is a subset of the
   * [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema) object. When this
   * field is set, you must also set the `response_mime_type` to `application/json`. Deprecated: Use
   * `response_format` instead.
   */
  val responseSchema: Schema? = null,

  /** Optional. Routing configuration. This field is not supported in Gemini API. */
  val routingConfig: GenerationConfigRoutingConfig? = null,

  /**
   * Optional. A seed for the random number generator. By setting a seed, you can make the model's
   * output mostly deterministic. For a given prompt and parameters (like temperature, top_p, etc.),
   * the model will produce the same response every time. However, it's not a guaranteed absolute
   * deterministic behavior. This is different from parameters like `temperature`, which control the
   * *level* of randomness. `seed` ensures that the "random" choices the model makes are the same on
   * every run, making it essential for testing and ensuring reproducible results.
   */
  val seed: Int? = null,

  /** Optional. The speech generation config. */
  val speechConfig: SpeechConfig? = null,

  /**
   * Optional. A list of character sequences that will stop the model from generating further
   * tokens. If a stop sequence is generated, the output will end at that point. This is useful for
   * controlling the length and structure of the output. For example, you can use ["\n", "###"] to
   * stop generation at a new line or a specific marker.
   */
  val stopSequences: List<String>? = null,

  /**
   * Optional. Controls the randomness of the output. A higher temperature results in more creative
   * and diverse responses, while a lower temperature makes the output more predictable and focused.
   * The valid range is (0.0, 2.0].
   */
  val temperature: Double? = null,

  /**
   * Optional. Configuration for thinking features. An error will be returned if this field is set
   * for models that don't support thinking.
   */
  val thinkingConfig: ThinkingConfig? = null,

  /**
   * Optional. Specifies the top-k sampling threshold. The model considers only the top k most
   * probable tokens for the next token. This can be useful for generating more coherent and less
   * random text. For example, a `top_k` of 40 means the model will choose the next word from the 40
   * most likely words.
   */
  val topK: Double? = null,

  /**
   * Optional. Specifies the nucleus sampling threshold. The model considers only the smallest set
   * of tokens whose cumulative probability is at least `top_p`. This helps generate more diverse
   * and less repetitive responses. For example, a `top_p` of 0.9 means the model considers tokens
   * until the cumulative probability of the tokens to select from reaches 0.9. It's recommended to
   * adjust either temperature or `top_p`, but not both.
   */
  val topP: Double? = null,

  /**
   * Optional. Enables enhanced civic answers. It may not be available for all models. This field is
   * not supported in Vertex AI.
   */
  val enableEnhancedCivicAnswers: Boolean? = null,

  /** Optional. Config for translation. This field is not supported in Vertex AI. */
  val translationConfig: TranslationConfig? = null,
)
