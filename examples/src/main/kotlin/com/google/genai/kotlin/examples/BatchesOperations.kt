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

package com.google.genai.kotlin.examples

import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.BatchJobDestination
import com.google.genai.kotlin.types.BatchJobSource
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CreateBatchJobConfig
import com.google.genai.kotlin.types.EmbedContentBatch
import com.google.genai.kotlin.types.EmbeddingsBatchJobSource
import com.google.genai.kotlin.types.InlinedRequest
import com.google.genai.kotlin.types.Part
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to manage batch jobs.
 *
 * Usage:
 *
 * 1a. If you are using Gemini Enterprise Agent Platform, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * Then set Project, Location, and GOOGLE_GENAI_USE_ENTERPRISE flag as environment variables:
 *
 * export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * export GOOGLE_GENAI_USE_ENTERPRISE=true
 *
 * 1b. If you are using Gemini Developer API, set an API key environment variable. You can find a
 * list of available API keys here: https://aistudio.google.com/app/apikey
 *
 * export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * 2. Run the example:
 * <pre>
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.BatchesOperations
 * </pre>
 */
object BatchesOperations {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = if (args.isNotEmpty()) args[0] else "gemini-3.5-flash"
      val isEnterprise = System.getenv("GOOGLE_GENAI_USE_ENTERPRISE")?.toBoolean() ?: false

      // Instantiate the client. The client by default uses the Gemini Developer API.
      Client().use { client ->
        val src =
          if (isEnterprise) {
            BatchJobSource(
              gcsUri =
                listOf("gs://unified-genai-tests/batches/input/generate_content_requests.jsonl"),
              format = "jsonl",
            )
          } else {
            val inlinedRequests =
              listOf(
                InlinedRequest(
                  contents =
                    listOf(
                      Content(role = "user", parts = listOf(Part(text = "Why is the sky blue?")))
                    )
                )
              )
            BatchJobSource(inlinedRequests = inlinedRequests)
          }

        val config =
          if (isEnterprise) {
            CreateBatchJobConfig(
              displayName = "test_batch_gcs",
              dest =
                BatchJobDestination(
                  gcsUri = "gs://unified-genai-tests/batches/output",
                  format = "jsonl",
                ),
            )
          } else {
            CreateBatchJobConfig()
          }

        println("Creating batch job...")
        val batchJob = client.batches.create(model = modelId, src = src, config = config)
        println("Created batch job: ${batchJob.name}")

        val jobId = batchJob.name ?: return@runBlocking

        println("Getting batch job...")
        val getJob = client.batches.get(name = jobId)
        println("Got batch job state: ${getJob.state}")

        println("Listing batch jobs...")
        val listJobs = client.batches.list()
        val jobs = listJobs.take(5).toList()
        println("Iterated over ${jobs.size} batch jobs")

        println("Deleting batch job...")
        client.batches.delete(name = jobId)
        println("Deleted batch job: $jobId")
        
        if (!isEnterprise) {
            println("Creating batch embeddings job...")
            val embedSrc = EmbeddingsBatchJobSource(
                inlinedRequests = EmbedContentBatch(
                    contents = listOf(
                        Content(parts = listOf(Part(text = "Hello world"))),
                        Content(parts = listOf(Part(text = "Batch embedding example")))
                    )
                )
            )
            val embedBatchJob = client.batches.createEmbeddings(model = "gemini-embedding-2", src = embedSrc)
            println("Created batch embeddings job: ${embedBatchJob.name}")
            val embedJobId = embedBatchJob.name
            if (embedJobId != null) {
                client.batches.delete(name = embedJobId)
                println("Deleted batch embeddings job: $embedJobId")
            }
        }
      }
      kotlin.system.exitProcess(0)
    }
}
