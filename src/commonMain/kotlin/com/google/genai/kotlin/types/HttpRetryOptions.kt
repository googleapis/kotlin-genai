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

/** HTTP retry options to be used in each of the requests. */
@Serializable
data class HttpRetryOptions(

  /**
   * Maximum number of attempts, including the original request. If 0 or 1, it means no retries. If
   * not specified, default to 5.
   */
  val attempts: Int? = null,

  /**
   * Initial delay before the first retry, in fractions of a second. If not specified, default to
   * 1.0 second.
   */
  val initialDelay: Double? = null,

  /**
   * Maximum delay between retries, in fractions of a second. If not specified, default to 60.0
   * seconds.
   */
  val maxDelay: Double? = null,

  /**
   * Multiplier by which the delay increases after each attempt. If not specified, default to 2.0.
   */
  val expBase: Double? = null,

  /** Randomness factor for the delay. If not specified, default to 1.0. */
  val jitter: Double? = null,

  /**
   * List of HTTP status codes that should trigger a retry. If not specified, a default set of
   * retryable codes (408, 429, and 5xx) may be used.
   */
  val httpStatusCodes: List<Int>? = null,
)
