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

/** Config for `inlined_responses` parameter. */
@Serializable
data class InlinedResponse(

  /** The response to the request. */
  val response: GenerateContentResponse? = null,

  /** The metadata to be associated with the request. */
  val metadata: Map<String, String>? = null,

  /** The error encountered while processing the request. */
  val error: JobError? = null,
)
