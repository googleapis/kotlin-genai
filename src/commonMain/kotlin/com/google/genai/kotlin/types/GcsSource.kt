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

/**
 * The Google Cloud Storage location for the input content. This data type is not supported in
 * Gemini API.
 */
@Serializable
data class GcsSource(

  /**
   * Required. Google Cloud Storage URI(-s) to the input file(s). May contain wildcards. For more
   * information on wildcards, see https://cloud.google.com/storage/docs/wildcards.
   */
  val uris: List<String>? = null
)
