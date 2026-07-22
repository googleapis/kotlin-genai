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
 * History configuration. This message is included in the session configuration as
 * `BidiGenerateContentSetup.history_config`. Configures the exchange of history messages. This data
 * type is not supported in Vertex AI.
 */
@Serializable
data class HistoryConfig(

  /**
   * Optional. If true, after sending `setup_complete`, the server will wait and at first process
   * `client_content` messages until `turn_complete` is `true`. This initial history will not
   * trigger a model call and may end with role `MODEL`. After `turn_complete` is `true`, the client
   * can start the realtime conversation via `realtime_input`.
   */
  val initialHistoryInClientContent: Boolean? = null
)
