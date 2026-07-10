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
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CreateCachedContentConfig
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.UpdateCachedContentConfig
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to manage cached content.
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
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.CachedContentOperations
 * </pre>
 */
object CachedContentOperations {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = if (args.isNotEmpty()) args[0] else "gemini-3.5-flash"

      // Instantiate the client. The client by default uses the Gemini Developer API.
      Client().use { client ->
        // This is a dummy data, use your own bytes data or file URI Instead.
        val bytesData =
          Part(
            inlineData =
              Blob(
                mimeType = "text/plain",
                data = "Hello Gemini ".repeat(5_000).encodeToByteArray(),
              )
          )

        val config =
          CreateCachedContentConfig(
            systemInstruction = Content(parts = listOf(Part(text = "You are an expert."))),
            ttl = 60.minutes,
            contents = listOf(Content(role = "user", parts = listOf(bytesData))),
          )

        val cachedContent1 = client.caches.create(model = modelId, config = config)
        println("Created cached content: $cachedContent1")

        val cachedContent2 = client.caches.get(name = cachedContent1.name!!)
        println("Get cached content: $cachedContent2")

        val cachedContent3 =
          client.caches.update(
            name = cachedContent1.name!!,
            config = UpdateCachedContentConfig(ttl = 10.minutes),
          )
        println("Update cached content: $cachedContent3")

        println("List cached contents is coming soon.")

        val response =
          client.models.generateContent(
            model = modelId,
            text = "Summarize the cached data.",
            config =
              GenerateContentConfig(cachedContent = cachedContent3.name!!, maxOutputTokens = 1024),
          )
        println("Generate content with the cached content. Response: ${response.text}")

        client.caches.delete(cachedContent1.name!!)
        println("Deleted cached content: ${cachedContent1.name}")
      }

      kotlin.system.exitProcess(0)
    }
}
