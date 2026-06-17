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
 * Represents a breakdown of token usage by modality. This message is used in CountTokensResponse
 * and GenerateContentResponse.UsageMetadata to provide a detailed view of how many tokens are used
 * by each modality (e.g., text, image, video) in a request. This is particularly useful for
 * multimodal models, allowing you to track and manage token consumption for billing and quota
 * purposes.
 */
@Serializable
data class ModalityTokenCount(

  /** The modality that this token count applies to. */
  val modality: MediaModality? = null,

  /** The number of tokens counted for this modality. */
  val tokenCount: Int? = null,
)
