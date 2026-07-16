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

/** Fine-tuning job creation request - optional fields. */
@Serializable
data class CreateTuningJobConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /**
   * The method to use for tuning (SUPERVISED_FINE_TUNING or PREFERENCE_TUNING or DISTILLATION or
   * REINFORCEMENT_TUNING). If not set, the default method (SFT) will be used.
   */
  val method: TuningMethod? = null,

  /** Validation dataset for tuning. The dataset must be formatted as a JSONL file. */
  val validationDataset: TuningValidationDataset? = null,

  /**
   * The display name of the tuned Model. The name can be up to 128 characters long and can consist
   * of any UTF-8 characters.
   */
  val tunedModelDisplayName: String? = null,

  /** The description of the TuningJob */
  val description: String? = null,

  /** Number of complete passes the model makes over the entire training dataset during training. */
  val epochCount: Int? = null,

  /**
   * Multiplier for adjusting the default learning rate. 1P models only. Mutually exclusive with
   * learning_rate.
   */
  val learningRateMultiplier: Double? = null,

  /**
   * If set to true, disable intermediate checkpoints and only the last checkpoint will be exported.
   * Otherwise, enable intermediate checkpoints.
   */
  val exportLastCheckpointOnly: Boolean? = null,

  /** The optional checkpoint id of the pre-tuned model to use for tuning, if applicable. */
  val preTunedModelCheckpointId: String? = null,

  /** Adapter size for tuning. */
  val adapterSize: AdapterSize? = null,

  /** Tuning mode for tuning. */
  val tuningMode: TuningMode? = null,

  /**
   * Custom base model for tuning. This is only supported for OSS models in Gemini Enterprise Agent
   * Platform.
   */
  val customBaseModel: String? = null,

  /**
   * The batch size hyperparameter for tuning. This is only supported for OSS models in Gemini
   * Enterprise Agent Platform.
   */
  val batchSize: Int? = null,

  /**
   * The learning rate for tuning. OSS models only. Mutually exclusive with
   * learning_rate_multiplier.
   */
  val learningRate: Double? = null,

  /**
   * Optional. The labels with user-defined metadata to organize TuningJob and generated resources
   * such as Model and Endpoint. Label keys and values can be no longer than 64 characters (Unicode
   * codepoints), can only contain lowercase letters, numeric characters, underscores and dashes.
   * International characters are allowed. See https://goo.gl/xmQnxf for more information and
   * examples of labels.
   */
  val labels: Map<String, String>? = null,

  /** Weight for KL Divergence regularization, Preference Optimization tuning only. */
  val beta: Double? = null,

  /** The base teacher model that is being distilled. Distillation only. */
  val baseTeacherModel: String? = null,

  /** The resource name of the Tuned teacher model. Distillation only. */
  val tunedTeacherModelSource: String? = null,

  /** Multiplier for adjusting the weight of the SFT loss. Distillation only. */
  val sftLossWeightMultiplier: Double? = null,

  /** The Google Cloud Storage location where the tuning job outputs are written. */
  val outputUri: String? = null,

  /** Reward function configuration for reinforcement tuning. Reinforcement tuning only. */
  val rewardConfig: SingleReinforcementTuningRewardConfig? = null,

  /**
   * Composite reward function configuration for reinforcement tuning. Reinforcement tuning only.
   */
  val compositeRewardConfig: CompositeReinforcementTuningRewardConfig? = null,

  /**
   * Number of different responses to generate per prompt during tuning. Reinforcement tuning only.
   */
  val samplesPerPrompt: Int? = null,

  /** How often at steps to evaluate the tuning job during training. Reinforcement tuning only. */
  val evaluateInterval: Int? = null,

  /** How often at steps to save checkpoints during training. Reinforcement tuning only. */
  val checkpointInterval: Int? = null,

  /** The maximum number of tokens to generate per prompt. Reinforcement tuning only. */
  val maxOutputTokens: Int? = null,

  /**
   * Indicates the maximum thinking depth. Use with earlier models shall result in error.
   * Reinforcement tuning only.
   */
  val thinkingLevel: ReinforcementTuningThinkingLevel? = null,

  /**
   * Cloud Storage path to file containing validation dataset for tuning. The dataset must be
   * formatted as a JSONL file. If no validation dataset is provided, by default the API splits 25%
   * of the training dataset or 50 examples, whichever is larger, as the validation dataset.
   * Reinforcement tuning only.
   */
  val validationDatasetUri: String? = null,

  /**
   * The encryption spec of the tuning job. Customer-managed encryption key options for a TuningJob.
   * If this is set, then all resources created by the TuningJob will be encrypted with provided
   * encryption key.
   */
  val encryptionSpec: EncryptionSpec? = null,
)
