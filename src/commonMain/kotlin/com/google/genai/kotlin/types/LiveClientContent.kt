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
 * Incremental update of the current conversation delivered from the client.
 *
 * All the content here will unconditionally be appended to the conversation history and used as
 * part of the prompt to the model to generate content.
 *
 * A message here will interrupt any current model generation.
 */
@Serializable
data class LiveClientContent(

  /**
   * The content appended to the current conversation with the model.
   *
   * For single-turn queries, this is a single instance. For multi-turn queries, this is a repeated
   * field that contains conversation history and latest request.
   */
  val turns: List<Content>? = null,

  /**
   * If true, indicates that the server content generation should start with the currently
   * accumulated prompt. Otherwise, the server will await additional messages before starting
   * generation.
   */
  val turnComplete: Boolean? = null,
)
