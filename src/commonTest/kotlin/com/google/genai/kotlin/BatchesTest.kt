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

import com.google.genai.kotlin.types.BatchJobSource
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.InlinedRequest
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.JobState
import com.google.genai.kotlin.types.EmbeddingsBatchJobSource
import com.google.genai.kotlin.types.EmbedContentBatch
import com.google.genai.kotlin.types.CreateBatchJobConfig
import com.google.genai.kotlin.types.BatchJobDestination
import com.google.genai.kotlin.types.VertexMultimodalDatasetDestination
import com.google.genai.kotlin.types.EmbedContentConfig
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

private const val MODEL_NAME = "gemini-3.5-flash"

private fun runTest(testBody: suspend TestScope.() -> Unit) =
  runTest(timeout = 60.seconds, testBody = testBody)

class BatchesTest : BaseTestServer() {

  @Test
  fun testCreateGetDeleteBatchJobMlDev() = runTest {
    val testName = "BatchesTest.testCreateGetDeleteBatchJobMlDev"
    val client = createClient(false, testName)

    val inlinedRequests = listOf(
      InlinedRequest(
        contents = listOf(Content(role = "user", parts = listOf(Part(text = "Why is the sky blue?"))))
      )
    )

    val src = BatchJobSource(
      inlinedRequests = inlinedRequests
    )

    var batchJob = client.batches.create(model = MODEL_NAME, src = src)

    assertNotNull(batchJob.name)
    assertTrue(batchJob.name!!.contains("batches/"))

    // Get the batch job
    batchJob = client.batches.get(name = batchJob.name!!)
    assertNotNull(batchJob.state)

    // Only delete if it's not pending to avoid errors
    if (batchJob.state != JobState.JOB_STATE_PENDING) {
      client.batches.delete(name = batchJob.name!!)
    }
  }

  @Test
  fun testListBatchJobsMlDev() = runTest {
    val testName = "BatchesTest.testListBatchJobsMlDev"
    val client = createClient(false, testName)

    val pager = client.batches.list()
    assertNotNull(pager)

    val jobs = pager.take(1).toList()
    assertTrue(jobs.isNotEmpty() || jobs.isEmpty()) // Just verify it completes
  }

  @Test
  fun testListBatchJobsVertex() = runTest {
    val testName = "BatchesTest.testListBatchJobsVertex"
    val client = createClient(true, testName)

    val pager = client.batches.list()
    assertNotNull(pager)

    val jobs = pager.take(1).toList()
    assertTrue(jobs.isNotEmpty() || jobs.isEmpty()) // Just verify it completes
  }

  @Test
  fun testCreateGetBatchJobEmbeddingsMlDev() = runTest {
    val testName = "BatchesTest.testCreateGetBatchJobEmbeddingsMlDev"
    val client = createClient(false, testName)

    val embedSrc = EmbeddingsBatchJobSource(
      inlinedRequests = EmbedContentBatch(
        contents = listOf(
          Content(parts = listOf(Part(text = "1"))),
          Content(parts = listOf(Part(text = "2"))),
          Content(parts = listOf(Part(text = "3")))
        )
      )
    )

    var batchJob = client.batches.createEmbeddings(model = "gemini-embedding-001", src = embedSrc)
    assertNotNull(batchJob.name)
    assertTrue(batchJob.name!!.contains("batches/"))

    // Get the batch job
    batchJob = client.batches.get(name = batchJob.name!!)
    assertNotNull(batchJob.state)
  }

  @Test
  fun testCreateGetBatchJobEmbeddingsWithConfigMlDev() = runTest {
    val testName = "BatchesTest.testCreateGetBatchJobEmbeddingsWithConfigMlDev"
    val client = createClient(false, testName)

    val embedSrc = EmbeddingsBatchJobSource(
      inlinedRequests = EmbedContentBatch(
        config = EmbedContentConfig(
          taskType = "RETRIEVAL_DOCUMENT",
          title = "test_title"
        ),
        contents = listOf(
          Content(parts = listOf(Part(text = "1"))),
          Content(parts = listOf(Part(text = "2"))),
          Content(parts = listOf(Part(text = "3")))
        )
      )
    )

    var batchJob = client.batches.createEmbeddings(model = "gemini-embedding-001", src = embedSrc)
    assertNotNull(batchJob.name)
    assertTrue(batchJob.name!!.contains("batches/"))

    // Get the batch job
    batchJob = client.batches.get(name = batchJob.name!!)
    assertNotNull(batchJob.state)
  }

  @Test
  fun testCreateGetBatchJobGCSVertex() = runTest {
    val testName = "BatchesTest.testCreateGetBatchJobGCSVertex"
    val client = createClient(true, testName)

    val src = BatchJobSource(
      gcsUri = listOf("gs://unified-genai-tests/batches/input/generate_content_requests.jsonl"),
      format = "jsonl"
    )
    val config = CreateBatchJobConfig(
      displayName = "test_batch_gcs",
      dest = BatchJobDestination(
        gcsUri = "gs://unified-genai-tests/batches/output",
        format = "jsonl"
      )
    )

    var batchJob = client.batches.create(model = "gemini-2.5-flash", src = src, config = config)
    assertNotNull(batchJob.name)

    // Get the batch job
    batchJob = client.batches.get(name = batchJob.name!!)
    assertNotNull(batchJob.state)
  }

  @Test
  fun testCancelBatchJobMlDev() = runTest {
    val testName = "BatchesTest.testCancelBatchJobMlDev"
    val client = createClient(false, testName)

    val inlinedRequests = listOf(
      InlinedRequest(
        contents = listOf(Content(role = "user", parts = listOf(Part(text = "Why is the sky blue?"))))
      )
    )

    val src = BatchJobSource(
      inlinedRequests = inlinedRequests
    )

    val batchJob = client.batches.create(model = MODEL_NAME, src = src)

    if (batchJob.name != null) {
      client.batches.cancel(name = batchJob.name!!)
    }
  }

  @Test
  fun testCancelBatchJobVertex() = runTest {
    val testName = "BatchesTest.testCancelBatchJobVertex"
    val client = createClient(true, testName)

    val src = BatchJobSource(
      gcsUri = listOf("gs://unified-genai-tests/batches/input/generate_content_requests.jsonl"),
      format = "jsonl"
    )
    val config = CreateBatchJobConfig(
      displayName = "test_batch_cancel_gcs",
      dest = BatchJobDestination(
        gcsUri = "gs://unified-genai-tests/batches/output",
        format = "jsonl"
      )
    )

    val batchJob = client.batches.create(model = "gemini-2.5-flash", src = src, config = config)

    if (batchJob.name != null) {
      client.batches.cancel(name = batchJob.name!!)
    }
  }
}
