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

import kotlin.time.Duration
import kotlinx.serialization.Serializable

/** Optional configuration for cached content creation. */
@Serializable
data class CreateCachedContentConfig(

  /** Used to override HTTP request options. */
  val httpOptions: HttpOptions? = null,

  /**
   * The TTL for this resource. The expiration time is computed: now + TTL. It is a duration string,
   * with up to nine fractional digits, terminated by 's'. Example: "3.5s".
   */
  val ttl: Duration? = null,

  /**
   * Timestamp of when this resource is considered expired. Uses RFC 3339 format, Example:
   * 2014-10-02T15:01:23Z.
   */
  @Serializable(with = InstantSerializer::class) val expireTime: Instant? = null,

  /** The user-generated meaningful display name of the cached content. */
  val displayName: String? = null,

  /** The content to cache. */
  val contents: List<Content>? = null,

  /** Developer set system instruction. */
  val systemInstruction: Content? = null,

  /** A list of `Tools` the model may use to generate the next response. */
  val tools: List<Tool>? = null,

  /** Configuration for the tools to use. This config is shared for all tools. */
  val toolConfig: ToolConfig? = null,

  /**
   * The Cloud KMS resource identifier of the customer managed encryption key used to protect a
   * resource. The key needs to be in the same region as where the compute resource is created. See
   * https://cloud.google.com/vertex-ai/docs/general/cmek for more details. If this is set, then all
   * created CachedContent objects will be encrypted with the provided encryption key. Allowed
   * formats: projects/{project}/locations/{location}/keyRings/{key_ring}/cryptoKeys/{crypto_key}
   */
  val kmsKeyName: String? = null,
)
