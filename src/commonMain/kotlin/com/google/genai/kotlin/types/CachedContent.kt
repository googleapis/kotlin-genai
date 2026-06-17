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

/** A resource used in LLM queries for users to explicitly specify what to cache. */
@Serializable
data class CachedContent(

  /** The server-generated resource name of the cached content. */
  val name: String? = null,

  /** The user-generated meaningful display name of the cached content. */
  val displayName: String? = null,

  /** The name of the publisher model to use for cached content. */
  val model: String? = null,

  /** Creation time of the cache entry. */
  @Serializable(with = InstantSerializer::class) val createTime: Instant? = null,

  /** When the cache entry was last updated in UTC time. */
  @Serializable(with = InstantSerializer::class) val updateTime: Instant? = null,

  /** Expiration time of the cached content. */
  @Serializable(with = InstantSerializer::class) val expireTime: Instant? = null,

  /** Metadata on the usage of the cached content. */
  val usageMetadata: CachedContentUsageMetadata? = null,
)
