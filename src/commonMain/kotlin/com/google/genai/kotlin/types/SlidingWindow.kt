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
 * Context window will be truncated by keeping only suffix of it.
 *
 * Context window will always be cut at start of USER role turn. System instructions and
 * `BidiGenerateContentSetup.prefix_turns` will not be subject to the sliding window mechanism, they
 * will always stay at the beginning of context window.
 */
@Serializable
data class SlidingWindow(

  /**
   * Session reduction target -- how many tokens we should keep. Window shortening operation has
   * some latency costs, so we should avoid running it on every turn. Should be < trigger_tokens. If
   * not set, trigger_tokens/2 is assumed.
   */
  val targetTokens: Long? = null
)
