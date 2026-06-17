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

/** Result of executing the ExecutableCode. Generated only when the `CodeExecution` tool is used. */
@Serializable
data class CodeExecutionResult(

  /** Required. Outcome of the code execution. */
  val outcome: Outcome? = null,

  /**
   * Optional. Contains stdout when code execution is successful, stderr or other description
   * otherwise.
   */
  val output: String? = null,

  /**
   * Optional. The identifier of the `ExecutableCode` part this result is for. Only populated if the
   * corresponding `ExecutableCode` has an id. This field is not supported in Vertex AI.
   */
  val id: String? = null,
)
