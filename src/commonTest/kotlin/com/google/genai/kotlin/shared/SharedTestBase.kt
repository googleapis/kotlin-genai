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

package com.google.genai.kotlin.shared

import com.google.genai.kotlin.BaseTestServer
import com.google.genai.kotlin.GenAiApiException
import com.google.genai.kotlin.types.HttpRetryOptions

/** Flash is cheaper and faster than the corpus GEMINI_MODEL for API-mode runs. */
const val SHARED_MODEL = "gemini-3.6-flash"

/**
 * Base class for the curated "shared" integration tests, the cross-SDK critical pathways the
 * nightly API-mode jobs run against the live backends. See go/genai-sdk:integration-testing.
 */
open class SharedTestBase : BaseTestServer() {

  /**
   * Retries transient failures so that a 5xx or 429 does not fail the nightly. Keep aligned with
   * conftest.py in the Python SDK tests.
   */
  override val testRetryOptions =
    HttpRetryOptions(
      attempts = 3,
      initialDelay = 1.0,
      maxDelay = 10.0,
      expBase = 2.0,
      httpStatusCodes = listOf(408, 429, 500, 502, 503, 504),
    )

  /**
   * True when the currently running job has selected the other backend. `kotlin.test` has no
   * runtime "skipped" status, so callers return early instead.
   */
  protected fun skipBackend(enterprise: Boolean): Boolean {
    val vertexOnly = !System.getenv("GOOGLE_GENAI_RUN_VERTEX_ONLY_IN_API_MODE").isNullOrEmpty()
    val geminiOnly = !System.getenv("GOOGLE_GENAI_RUN_GEMINI_ONLY_IN_API_MODE").isNullOrEmpty()

    if (enterprise && geminiOnly) {
      println("   === Skipping Vertex AI test (GEMINI ONLY config enabled)")
      return true
    }
    if (!enterprise && vertexOnly) {
      println("   === Skipping Gemini API test (VERTEX ONLY config enabled)")
      return true
    }
    return false
  }

  /** True when the throwable is a 429 RESOURCE_EXHAUSTED quota error. */
  protected fun isQuotaError(e: Throwable): Boolean =
    e is GenAiApiException && (e.code == 429 || e.status == "RESOURCE_EXHAUSTED")

  /**
   * Runs a live call, swallowing a 429 rather than failing the build. A quota response still
   * proves the SDK reached the live endpoint, so it is not a regression. See
   * go/genai-sdk:integration-testing section 4.4.
   */
  protected suspend fun runLive(block: suspend () -> Unit) {
    try {
      block()
    } catch (e: Throwable) {
      if (isQuotaError(e)) {
        println("   === Resource exhausted (429), not failing the build: ${e.message}")
        return
      }
      throw e
    }
  }

  /** Best-effort cleanup that never masks the real test result. */
  protected suspend fun tryCleanup(block: suspend () -> Unit) {
    try {
      block()
    } catch (e: Throwable) {
      println("   [cleanup] ignored failure: ${e.message}")
    }
  }
}
