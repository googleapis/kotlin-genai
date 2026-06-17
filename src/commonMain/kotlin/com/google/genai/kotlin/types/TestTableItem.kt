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
import kotlinx.serialization.json.JsonElement

/**  */
@Serializable
data class TestTableItem(

  /** The name of the test. This is used to derive the replay id. */
  val name: String? = null,

  /** The parameters to the test. Use pydantic models. */
  val parameters: Map<String, JsonElement>? = null,

  /** Expects an exception for MLDev matching the string. */
  val exceptionIfMldev: String? = null,

  /** Expects an exception for Vertex matching the string. */
  val exceptionIfVertex: String? = null,

  /** Use if you don't want to use the default replay id which is derived from the test name. */
  val overrideReplayId: String? = null,

  /**
   * True if the parameters contain an unsupported union type. This test will be skipped for
   * languages that do not support the union type.
   */
  val hasUnion: Boolean? = null,

  /**
   * When set to a reason string, this test will be skipped in the API mode. Use this flag for tests
   * that can not be reproduced with the real API. E.g. a test that deletes a resource.
   */
  val skipInApiMode: String? = null,

  /**
   * Keys to ignore when comparing the request and response. This is useful for tests that are not
   * deterministic.
   */
  val ignoreKeys: List<String>? = null,
)
