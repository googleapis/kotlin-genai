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

/**
 * Model names shared by the `test-server` tests.
 *
 * A recording pins the model name it was made with, so changing a constant here means re-recording
 * every test that uses it. Each module's names move into this file as that module is re-recorded; a
 * module still holding its own private constant has not been refreshed yet.
 */

/** The latest Gemini flash model, used by most tests. */
const val FLASH_MODEL_NAME = "gemini-flash-latest"

/** The text-to-speech model. No generally available version exists yet. */
const val TTS_MODEL_NAME = "gemini-3.1-flash-tts-preview"

/** The Nano Banana image-generation model. */
const val IMAGE_MODEL_NAME = "gemini-3.1-flash-image"

/** The embedding model. */
const val EMBEDDING_MODEL_NAME = "gemini-embedding-2"

/** The previous embedding model, kept so the older response shape stays covered. */
const val LEGACY_EMBEDDING_MODEL_NAME = "gemini-embedding-001"

/** A third-party Model-as-a-Service embedding model, served only from `us-central1`. */
const val MAAS_EMBEDDING_MODEL_NAME =
  "publishers/intfloat/models/multilingual-e5-large-instruct-maas"
