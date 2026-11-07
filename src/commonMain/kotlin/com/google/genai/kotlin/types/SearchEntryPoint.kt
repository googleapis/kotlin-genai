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
 * An entry point for displaying Google Search results. A `SearchEntryPoint` is populated when the
 * grounding source for a model's response is Google Search. It provides information that you can
 * use to display the search results in your application.
 */
@Serializable
data class SearchEntryPoint(

  /**
   * Optional. An HTML snippet that can be embedded in a web page or an application's webview. This
   * snippet displays a search result, including the title, URL, and a brief description of the
   * search result.
   */
  val renderedContent: String? = null,

  /**
   * Optional. A base64-encoded JSON object that contains a list of search queries and their
   * corresponding search URLs. This information can be used to build a custom search UI.
   */
  @Serializable(with = ByteArrayAsBase64Serializer::class) val sdkBlob: ByteArray? = null,
)
