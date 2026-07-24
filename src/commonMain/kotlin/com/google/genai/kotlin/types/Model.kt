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

/** A trained machine learning model. */
@Serializable
data class Model(

  /** Resource name of the model. */
  val name: String? = null,

  /** Display name of the model. */
  val displayName: String? = null,

  /** Description of the model. */
  val description: String? = null,

  /**
   * Version ID of the model. A new version is committed when a new model version is uploaded or
   * trained under an existing model ID. The version ID is an auto-incrementing decimal number in
   * string representation.
   */
  val version: String? = null,

  /**
   * List of deployed models created from this base model. Note that a model could have been
   * deployed to endpoints in different locations.
   */
  val endpoints: List<Endpoint>? = null,

  /** Labels with user-defined metadata to organize your models. */
  val labels: Map<String, String>? = null,

  /** Information about the tuned model from the base model. */
  val tunedModelInfo: TunedModelInfo? = null,

  /** The maximum number of input tokens that the model can handle. */
  val inputTokenLimit: Int? = null,

  /** The maximum number of output tokens that the model can generate. */
  val outputTokenLimit: Int? = null,

  /** List of actions that are supported by the model. */
  val supportedActions: List<String>? = null,

  /** The default checkpoint id of a model version. */
  val defaultCheckpointId: String? = null,

  /** The checkpoints of the model. */
  val checkpoints: List<Checkpoint>? = null,

  /**
   * Temperature value used for sampling set when the dataset was saved. This value is used to tune
   * the degree of randomness.
   */
  val temperature: Double? = null,

  /**
   * The maximum temperature value used for sampling set when the dataset was saved. This value is
   * used to tune the degree of randomness.
   */
  val maxTemperature: Double? = null,

  /**
   * Optional. Specifies the nucleus sampling threshold. The model considers only the smallest set
   * of tokens whose cumulative probability is at least `top_p`. This helps generate more diverse
   * and less repetitive responses. For example, a `top_p` of 0.9 means the model considers tokens
   * until the cumulative probability of the tokens to select from reaches 0.9. It's recommended to
   * adjust either temperature or `top_p`, but not both.
   */
  val topP: Double? = null,

  /**
   * Optional. Specifies the top-k sampling threshold. The model considers only the top k most
   * probable tokens for the next token. This can be useful for generating more coherent and less
   * random text. For example, a `top_k` of 40 means the model will choose the next word from the 40
   * most likely words.
   */
  val topK: Int? = null,

  /**
   * Whether the model supports thinking features. If true, thoughts are returned only if the model
   * supports thought and thoughts are available.
   */
  val thinking: Boolean? = null,
)
