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
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to generate content.
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
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.GenerateContent
 * </pre>
 */
object GenerateContent {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = args.firstOrNull() ?: "gemini-2.5-flash"

      // 3. Instantiate the client (use block automatically closes the client)
      Client().use { client ->
        try {
          // 4. Call the API
          val config =
            GenerateContentConfig(
              systemInstruction =
                Content(parts = listOf(Part(text = "You are a helpful assistant."))),
              maxOutputTokens = 1024,
              temperature = 0.5,
            )
          val response =
            client.models.generateContent(
              model = modelId,
              text = "What is your name?",
              config = config,
            )

          // 5. Print the result
          println("Unary response: ${response.text}")
        } catch (e: Exception) {
          System.err.println("Request failed: ${e.message}")
          e.printStackTrace()
        }
      }

      kotlin.system.exitProcess(0)
    }
}
