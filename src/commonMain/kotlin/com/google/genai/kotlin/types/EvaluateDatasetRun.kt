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

/** Evaluate Dataset Run Result for Tuning Job. This data type is not supported in Gemini API. */
@Serializable
data class EvaluateDatasetRun(

  /**
   * Output only. The checkpoint id used in the evaluation run. Only populated when evaluating
   * checkpoints.
   */
  val checkpointId: String? = null,

  /** Output only. The error of the evaluation run if any. */
  val error: GoogleRpcStatus? = null,

  /** Output only. Results for EvaluationService. */
  val evaluateDatasetResponse: EvaluateDatasetResponse? = null,

  /**
   * Output only. The resource name of the evaluation run. Format:
   * `projects/{project}/locations/{location}/evaluationRuns/{evaluation_run_id}`.
   */
  val evaluationRun: String? = null,

  /** Output only. Deprecated: The updated architecture uses evaluation_run instead. */
  val operationName: String? = null,
)
