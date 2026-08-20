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
import com.google.genai.kotlin.types.ClientOptions
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.runBlocking

/**
 * An example of configuring a custom HTTP client engine in the Google Gen AI client.
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
 * 2. Run the example: ./gradlew :examples:runExample
 *    -PmainClass=com.google.genai.kotlin.examples.CustomHttpClient
 */
object CustomHttpClient {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = args.firstOrNull() ?: "gemini-3.5-flash"

      val customEngine = OkHttp.create {
        config {
          connectTimeout(15, TimeUnit.SECONDS)
          readTimeout(30, TimeUnit.SECONDS)
          writeTimeout(30, TimeUnit.SECONDS)
          // Additional OkHttpClient customizations (interceptors, connection pool, SSL, etc.) can
          // be added here
        }
      }

      val clientOptions = ClientOptions(customHttpClient = customEngine)

      Client(clientOptions = clientOptions).use { client ->
        try {
          println("Sending request using custom HTTP client engine...")
          val response =
            client.models.generateContent(
              model = modelId,
              text = "Tell me a fun fact about networking.",
            )
          println("Response:\n${response.text}")
        } catch (e: Exception) {
          System.err.println("Request failed: ${e.message}")
          e.printStackTrace()
        }
      }

      kotlin.system.exitProcess(0)
    }
}
