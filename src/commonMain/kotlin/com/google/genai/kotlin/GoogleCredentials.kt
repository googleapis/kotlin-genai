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

package com.google.genai.kotlin

import io.ktor.client.request.HttpRequestBuilder

/**
 * Represents the credentials used to authenticate requests to the Google GenAI API.
 *
 * On JVM and Android platforms, this class is represented as a typealias to Google Auth's
 * `com.google.auth.oauth2.GoogleCredentials`.
 */
expect class GoogleCredentials

internal expect fun getDefaultCredentials(): GoogleCredentials?

internal expect fun GoogleCredentials.applyToRequest(builder: HttpRequestBuilder)
