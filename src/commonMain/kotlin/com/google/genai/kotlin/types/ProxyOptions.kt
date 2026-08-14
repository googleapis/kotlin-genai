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

/** Proxy configuration for the client. */
@Serializable
data class ProxyOptions(

  /** Proxy type. Defaults to HTTP if unspecified. */
  val type: ProxyType? = null,

  /** Proxy server hostname or IP address. */
  val host: String? = null,

  /** Proxy server port. */
  val port: Int? = null,

  /** Username for proxy authentication. If provided, `password` must also be specified. */
  val username: String? = null,

  /** Password for proxy authentication. If provided, `username` must also be specified. */
  val password: String? = null,
)
