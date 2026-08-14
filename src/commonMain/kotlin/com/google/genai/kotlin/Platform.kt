/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.genai.kotlin

/**
 * Returns the platform the SDK is running on for the `x-goog-api-client` header, such as
 * `genai-kotlin/17.0.1` or `genai-android/34`.
 *
 * Each target supplies its own actual rather than sharing one in `jvmAndroidMain`, since the label
 * exists to tell the platforms apart.
 */
internal expect fun platformLabel(): String
