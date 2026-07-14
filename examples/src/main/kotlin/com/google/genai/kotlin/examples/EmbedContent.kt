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

package com.google.genai.kotlin.examples

import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.EmbedContentConfig
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.FileData
import com.google.genai.kotlin.types.Part
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to calculate embeddings.
 *
 * This example demonstrates basic and advanced features of the `embedContent` API,
 * focusing on `gemini-embedding-2` capabilities under Gemini Enterprise Agent Platform.
 *
 * Usage:
 *
 * 1a. If you are using Gemini Enterprise Agent Platform, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * Then set Project, Location, and GOOGLE_GENAI_USE_ENTERPRISE flag as environment variables:
 *
 * export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 * export GOOGLE_CLOUD_LOCATION=global
 * export GOOGLE_GENAI_USE_ENTERPRISE=true
 *
 * 1b. If you are using Gemini Developer API, set an API key environment variable:
 *
 * export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * 2. Run the example:
 * <pre>
 * ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.EmbedContent
 * </pre>
 */
object EmbedContent {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      // Use the standard embedding model (gemini-embedding-2 supports multimodal and advanced features)
      val modelId = args.firstOrNull() ?: "gemini-embedding-2"

      println("Initializing client...")
      Client().use { client ->
        try {
          println("\n--- 1. Basic Text Embedding ---")
          val textToEmbed = "What is the capital of France?"
          println("Embedding text: \"$textToEmbed\" using model: $modelId")

          val response = client.models.embedContent(
            model = modelId,
            text = textToEmbed
          )

          printEmbeddingResponse(response)

          println("\n--- 2. Embedding with Custom Config (Task Type & Dimensionality) ---")
          println("Requesting embedding with outputDimensionality = 16 and taskType = RETRIEVAL_DOCUMENT")

          val config = EmbedContentConfig(
            outputDimensionality = 16,
            taskType = "RETRIEVAL_DOCUMENT",
            title = "Capital Inquiry"
          )
          val responseWithConfig = client.models.embedContent(
            model = modelId,
            text = textToEmbed,
            config = config
          )

          printEmbeddingResponse(responseWithConfig)

          println("\n--- 3. Multimodal Embedding ---")
          val multimodalResponse = if (client.enterprise) {
            println("Running Gemini Enterprise Agent Platform mode: Using GCS image URI...")
            val gcsUri = "gs://cloud-samples-data/generative-ai/image/a-man-and-a-dog.png"

            client.models.embedContent(
              model = modelId,
              contents = listOf(
                Content(
                  parts = listOf(
                    Part(text = "Similar things to the following image:"),
                    Part(fileData = FileData(fileUri = gcsUri, mimeType = "image/png"))
                  )
                )
              ),
              config = EmbedContentConfig(outputDimensionality = 10)
            )
          } else {
            println("Running Gemini Developer API mode: Using inline image bytes from resources...")
            val imageStream = EmbedContent::class.java.classLoader.getResourceAsStream("google.png")
              ?: throw IllegalStateException("Required resource 'google.png' not found in classpath.")
            val imageBytes = imageStream.use { it.readBytes() }

            client.models.embedContent(
              model = modelId,
              contents = listOf(
                Content(
                  parts = listOf(
                    Part(text = "Similar things to the following image:"),
                    Part(inlineData = Blob(mimeType = "image/png", data = imageBytes))
                  )
                )
              ),
              config = EmbedContentConfig(outputDimensionality = 10)
            )
          }

          printEmbeddingResponse(multimodalResponse)

        } catch (e: Exception) {
          System.err.println("Request failed: ${e.message}")
          e.printStackTrace()
        }
      }
      kotlin.system.exitProcess(0)
    }

  private fun printEmbeddingResponse(response: com.google.genai.kotlin.types.EmbedContentResponse) {
    val embeddings = response.embeddings
    if (embeddings != null && embeddings.isNotEmpty()) {
      val embedding = embeddings[0]
      val vector = embedding.values
      println("Successfully generated embedding!")
      println("  Vector size: ${vector?.size}")
      println("  First 5 values: ${vector?.take(5)}")

      val stats = embedding.statistics
      if (stats != null) {
        println("  Statistics:")
        println("    Token Count: ${stats.tokenCount}")
        val tokensDetails = stats.tokensDetails
        if (tokensDetails != null) {
          println("    Tokens Details:")
          tokensDetails.forEach { detail ->
            println("      Modality: ${detail.modality?.value}, Token Count: ${detail.tokenCount}")
          }
        }
      }
    } else {
      println("  No embeddings returned.")
    }
  }
}
