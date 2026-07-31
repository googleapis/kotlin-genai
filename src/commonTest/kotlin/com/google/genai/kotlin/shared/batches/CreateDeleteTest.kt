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

package com.google.genai.kotlin.shared.batches

import com.google.genai.kotlin.shared.SHARED_MODEL
import com.google.genai.kotlin.shared.SharedTestBase
import com.google.genai.kotlin.types.BatchJobSource
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.InlinedRequest
import com.google.genai.kotlin.types.Part
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 120.seconds, testBody = testBody)

class CreateDeleteTest : SharedTestBase() {
  private fun inlinedSource() =
    BatchJobSource(
      inlinedRequests =
        listOf(
          InlinedRequest(
            contents =
              listOf(Content(role = "user", parts = listOf(Part(text = "Why is the sky blue?"))))
          )
        )
    )

  /** Canonical batches/create_delete: test_create_delete_mldev. */
  @Test
  fun testCreateDelete() = runTest {
    listOf(false, true).forEach { enterprise ->
      if (skipBackend(enterprise)) return@forEach
      val suffix = if (enterprise) "vertex" else "mldev"
      val testName = "shared.batches.CreateDeleteTest.testCreateDelete.$suffix"
      val client = createClient(enterprise, testName)

      if (enterprise) {
        // Agent Platform rejects inlined requests client-side.
        val e =
          kotlin.runCatching { client.batches.create(model = SHARED_MODEL, src = inlinedSource()) }
            .exceptionOrNull()
        assertNotNull(e, "Expected the Agent Platform backend to reject inlinedRequests")
        assertTrue(
          e.message!!.contains("inlinedRequests is only supported in Gemini Developer API mode"),
          "Unexpected message: ${e.message}",
        )
        return@forEach
      }

      runLive {
        val job = client.batches.create(model = SHARED_MODEL, src = inlinedSource())
        assertNotNull(job.name)
        assertTrue(job.name!!.contains("batches/"))
        // Delete unconditionally so a failed assertion cannot leak a live batch job.
        tryCleanup { client.batches.delete(name = job.name!!) }
      }
    }
  }
}
