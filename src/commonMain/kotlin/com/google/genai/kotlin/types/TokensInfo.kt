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

/** Tokens info with a list of tokens and the corresponding list of token ids. */
@Serializable
data class TokensInfo(

  /** Optional fields for the role from the corresponding Content. */
  val role: String? = null,

  /** A list of token ids from the input. */
  val tokenIds: List<Long>? = null,

  /** A list of tokens from the input. */
  val tokens: List<@Serializable(with = ByteArrayAsBase64Serializer::class) ByteArray>? = null,
)
