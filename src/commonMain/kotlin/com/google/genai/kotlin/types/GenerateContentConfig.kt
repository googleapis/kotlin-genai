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

/**
 * Optional model configuration parameters.
 *
 * For more information, see `Content generation parameters
 * <https://cloud.google.com/vertex-ai/generative-ai/docs/multimodal/content-generation-parameters>`_.
 */
@Serializable
data class GenerateContentConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /** If true, the raw HTTP response will be returned in the 'sdk_http_response' field. */
  val shouldReturnHttpResponse: Boolean? = null,

  /**
   * Instructions for the model to steer it toward better performance. For example, "Answer as
   * concisely as possible" or "Don't use technical terms in your response".
   */
  val systemInstruction: Content? = null,

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

  /** Number of response variations to return. */
  val candidateCount: Int? = null,

  /** Maximum number of tokens that can be generated in the response. */
  val maxOutputTokens: Int? = null,

  /**
   * List of strings that tells the model to stop generating text if one of the strings is
   * encountered in the response.
   */
  val stopSequences: List<String>? = null,

  /**
   * Whether to return the log probabilities of the tokens that were chosen by the model at each
   * step.
   */
  val responseLogprobs: Boolean? = null,

  /** Number of top candidate tokens to return the log probabilities for at each generation step. */
  val logprobs: Int? = null,

  /**
   * Positive values penalize tokens that already appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  val presencePenalty: Double? = null,

  /**
   * Positive values penalize tokens that repeatedly appear in the generated text, increasing the
   * probability of generating more diverse content.
   */
  val frequencyPenalty: Double? = null,

  /**
   * When ``seed`` is fixed to a specific number, the model makes a best effort to provide the same
   * response for repeated requests. By default, a random number is used.
   */
  val seed: Int? = null,

  /**
   * Output response mimetype of the generated candidate text. Supported mimetype:
   * - `text/plain`: (default) Text output.
   * - `application/json`: JSON response in the candidates. The model needs to be prompted to output
   *   the appropriate response type, otherwise the behavior is undefined.
   */
  val responseMimeType: String? = null,

  /**
   * The `Schema` object allows the definition of input and output data types. These types can be
   * objects, but also primitives and arrays. Represents a select subset of an
   * [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema). If set, a compatible
   * response_mime_type must also be set. Compatible mimetypes: `application/json`: Schema for JSON
   * response.
   *
   * If `response_schema` doesn't process your schema correctly, try using `response_json_schema`
   * instead.
   */
  val responseSchema: Schema? = null,

  /**
   * Optional. Output schema of the generated response. This is an alternative to `response_schema`
   * that accepts [JSON Schema](https://json-schema.org/). If set, `response_schema` must be
   * omitted, but `response_mime_type` is required. While the full JSON Schema may be sent, not all
   * features are supported. Specifically, only the following properties are supported: - `$id` -
   * `$defs` - `$ref` - `$anchor`
   * - `type` - `format` - `title` - `description` - `enum` (for strings and numbers) - `items` -
   *   `prefixItems` - `minItems` - `maxItems` - `minimum` - `maximum` - `anyOf` - `oneOf`
   *   (interpreted the same as `anyOf`) - `properties` - `additionalProperties` - `required` The
   *   non-standard `propertyOrdering` property may also be set. Cyclic references are unrolled to a
   *   limited degree and, as such, may only be used within non-required properties. (Nullable
   *   properties are not sufficient.) If `$ref` is set on a sub-schema, no other properties, except
   *   for than those starting as a `$`, may be set.
   */
  val responseJsonSchema: JsonElement? = null,

  /** Configuration for model selection. */
  val modelSelectionConfig: ModelSelectionConfig? = null,

  /** Safety settings in the request to block unsafe content in the response. */
  val safetySettings: List<SafetySetting>? = null,

  /**
   * Code that enables the system to interact with external systems to perform an action outside of
   * the knowledge and scope of the model.
   */
  val tools: List<Tool>? = null,

  /** Associates model output to a specific function call. */
  val toolConfig: ToolConfig? = null,

  /** Resource name of a context cache that can be used in subsequent requests. */
  val cachedContent: String? = null,

  /**
   * The requested modalities of the response. Represents the set of modalities that the model can
   * return.
   */
  val responseModalities: List<String>? = null,

  /** If specified, the media resolution specified will be used. */
  val mediaResolution: MediaResolution? = null,

  /** The speech generation configuration. */
  val speechConfig: SpeechConfig? = null,

  /** If enabled, audio timestamp will be included in the request to the model. */
  val audioTimestamp: Boolean? = null,

  /** The thinking features configuration. */
  val thinkingConfig: ThinkingConfig? = null,

  /** The image generation configuration. */
  val imageConfig: ImageConfig? = null,

  /**
   * Enables enhanced civic answers. It may not be available for all models. This field is not
   * supported in Gemini Enterprise Agent Platform.
   */
  val enableEnhancedCivicAnswers: Boolean? = null,

  /**
   * Settings for prompt and response sanitization using the Model Armor service. If supplied,
   * safety_settings must not be supplied.
   */
  val modelArmorConfig: ModelArmorConfig? = null,

  /** The service tier to use for the request. For example, ServiceTier.FLEX. */
  val serviceTier: ServiceTier? = null,

  /** Configuration for model router requests. */
  val routingConfig: GenerationConfigRoutingConfig? = null,

  /** Labels with user-defined metadata to break down billed charges. */
  val labels: Map<String, String>? = null,

  /** Optional. Configuration for audio transcription (speech recognition). */
  val audioTranscriptionConfig: AudioTranscriptionConfig? = null,
)
