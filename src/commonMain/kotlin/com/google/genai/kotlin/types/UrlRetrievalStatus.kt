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

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/** The status of the URL retrieval. */
@Serializable
@JvmInline
value class UrlRetrievalStatus(val value: String) {
  companion object {

    /** Default value. This value is unused. */
    val URL_RETRIEVAL_STATUS_UNSPECIFIED = UrlRetrievalStatus("URL_RETRIEVAL_STATUS_UNSPECIFIED")

    /** The URL was retrieved successfully. */
    val URL_RETRIEVAL_STATUS_SUCCESS = UrlRetrievalStatus("URL_RETRIEVAL_STATUS_SUCCESS")

    /** The URL retrieval failed. */
    val URL_RETRIEVAL_STATUS_ERROR = UrlRetrievalStatus("URL_RETRIEVAL_STATUS_ERROR")

    /**
     * Url retrieval is failed because the content is behind paywall. This enum value is not
     * supported in Vertex AI.
     */
    val URL_RETRIEVAL_STATUS_PAYWALL = UrlRetrievalStatus("URL_RETRIEVAL_STATUS_PAYWALL")

    /**
     * Url retrieval is failed because the content is unsafe. This enum value is not supported in
     * Vertex AI.
     */
    val URL_RETRIEVAL_STATUS_UNSAFE = UrlRetrievalStatus("URL_RETRIEVAL_STATUS_UNSAFE")
  }
}
