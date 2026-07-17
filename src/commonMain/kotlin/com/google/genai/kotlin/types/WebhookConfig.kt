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

/**
 * Configuration for webhook notifications.
 *
 * Used to configure webhook endpoints that will receive notifications when long-running operations
 * (e.g., batch jobs, video generation) complete.
 */
@Serializable
data class WebhookConfig(

  /**
   * The webhook URIs to receive notifications. If set, these webhook URIs will be used instead of
   * the registered webhooks.
   */
  val uris: List<String>? = null,

  /**
   * User metadata that will be included in each webhook event notification. Use this to attach
   * custom key-value data to correlate webhook events with your internal systems.
   */
  val userMetadata: Map<String, JsonElement>? = null,
)
