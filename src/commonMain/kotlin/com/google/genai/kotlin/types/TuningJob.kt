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

/** A tuning job. */
@Serializable
data class TuningJob(

  /** Used to retain the full HTTP response. */
  val sdkHttpResponse: HttpResponse? = null,

  /**
   * Output only. Identifier. Resource name of a TuningJob. Format:
   * `projects/{project}/locations/{location}/tuningJobs/{tuning_job}`
   */
  val name: String? = null,

  /** Output only. The detailed state of the job. */
  val state: JobState? = null,

  /** Output only. Time when the TuningJob was created. */
  @Serializable(with = InstantSerializer::class) val createTime: Instant? = null,

  /**
   * Output only. Time when the TuningJob for the first time entered the `JOB_STATE_RUNNING` state.
   */
  @Serializable(with = InstantSerializer::class) val startTime: Instant? = null,

  /**
   * Output only. Time when the TuningJob entered any of the following JobStates:
   * `JOB_STATE_SUCCEEDED`, `JOB_STATE_FAILED`, `JOB_STATE_CANCELLED`, `JOB_STATE_EXPIRED`.
   */
  @Serializable(with = InstantSerializer::class) val endTime: Instant? = null,

  /** Output only. Time when the TuningJob was most recently updated. */
  @Serializable(with = InstantSerializer::class) val updateTime: Instant? = null,

  /**
   * Output only. Only populated when job's state is `JOB_STATE_FAILED` or `JOB_STATE_CANCELLED`.
   */
  val error: GoogleRpcStatus? = null,

  /** Optional. The description of the TuningJob. */
  val description: String? = null,

  /**
   * The base model that is being tuned. See
   * [Supported models](https://cloud.google.com/vertex-ai/generative-ai/docs/model-reference/tuning#supported_models).
   */
  val baseModel: String? = null,

  /** Output only. The tuned model resources associated with this TuningJob. */
  val tunedModel: TunedModel? = null,

  /** The pre-tuned model for continuous tuning. */
  val preTunedModel: PreTunedModel? = null,

  /** Tuning Spec for Supervised Fine Tuning. */
  val supervisedTuningSpec: SupervisedTuningSpec? = null,

  /** Tuning Spec for Preference Optimization. */
  val preferenceOptimizationSpec: PreferenceOptimizationSpec? = null,

  /** Tuning Spec for Distillation. */
  val distillationSpec: DistillationSpec? = null,

  /** Tuning Spec for Reinforcement Tuning. */
  val reinforcementTuningSpec: ReinforcementTuningSpec? = null,

  /** Output only. The tuning data statistics associated with this TuningJob. */
  val tuningDataStats: TuningDataStats? = null,

  /**
   * Customer-managed encryption key options for a TuningJob. If this is set, then all resources
   * created by the TuningJob will be encrypted with the provided encryption key.
   */
  val encryptionSpec: EncryptionSpec? = null,

  /** Tuning Spec for open sourced and third party Partner models. */
  val partnerModelTuningSpec: PartnerModelTuningSpec? = null,

  /**
   * Optional. The user-provided path to custom model weights. Set this field to tune a custom
   * model. The path must be a Cloud Storage directory that contains the model weights in
   * .safetensors format along with associated model metadata files. If this field is set, the
   * base_model field must still be set to indicate which base model the custom model is derived
   * from. This feature is only available for open source models.
   */
  val customBaseModel: String? = null,

  /** Output only. Evaluation runs for the Tuning Job. */
  val evaluateDatasetRuns: List<EvaluateDatasetRun>? = null,

  /** Output only. The Experiment associated with this TuningJob. */
  val experiment: String? = null,

  /** Tuning Spec for Full Fine Tuning. */
  val fullFineTuningSpec: FullFineTuningSpec? = null,

  /**
   * Optional. The labels with user-defined metadata to organize TuningJob and generated resources
   * such as Model and Endpoint. Label keys and values can be no longer than 64 characters (Unicode
   * codepoints), can only contain lowercase letters, numeric characters, underscores and dashes.
   * International characters are allowed. See https://goo.gl/xmQnxf for more information and
   * examples of labels.
   */
  val labels: Map<String, String>? = null,

  /**
   * Optional. Cloud Storage path to the directory where tuning job outputs are written to. This
   * field is only available and required for open source models.
   */
  val outputUri: String? = null,

  /**
   * Output only. The resource name of the PipelineJob associated with the TuningJob. Format:
   * `projects/{project}/locations/{location}/pipelineJobs/{pipeline_job}`.
   */
  val pipelineJob: String? = null,

  /**
   * The service account that the tuningJob workload runs as. If not specified, the Vertex AI Secure
   * Fine-Tuned Service Agent in the project will be used. See
   * https://cloud.google.com/iam/docs/service-agents#vertex-ai-secure-fine-tuning-service-agent
   * Users starting the pipeline must have the `iam.serviceAccounts.actAs` permission on this
   * service account.
   */
  val serviceAccount: String? = null,

  /**
   * Optional. The display name of the TunedModel. The name can be up to 128 characters long and can
   * consist of any UTF-8 characters. For continuous tuning, tuned_model_display_name will by
   * default use the same display name as the pre-tuned model. If a new display name is provided,
   * the tuning job will create a new model instead of a new version.
   */
  val tunedModelDisplayName: String? = null,

  /** Output only. The detail state of the tuning job (while the overall `JobState` is running). */
  val tuningJobState: TuningJobState? = null,

  /** Tuning Spec for Veo Tuning. */
  val veoTuningSpec: VeoTuningSpec? = null,

  /** Output only. Tuning Job metadata. */
  val tuningJobMetadata: TuningJobMetadata? = null,

  /** Tuning Spec for Veo LoRA Tuning. */
  val veoLoraTuningSpec: VeoLoraTuningSpec? = null,

  /**  */
  val distillationSamplingSpec: DistillationSamplingSpec? = null,
)
