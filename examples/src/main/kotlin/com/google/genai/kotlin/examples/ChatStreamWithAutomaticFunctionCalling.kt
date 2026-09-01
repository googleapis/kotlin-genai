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

import com.google.genai.kotlin.AutomaticFunctionCalling
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.callableFunction
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to let the model call your functions during a
 * chat, streaming the response as it arrives.
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
 * ./gradlew :examples:runExample \
 *   -PmainClass=com.google.genai.kotlin.examples.ChatStreamWithAutomaticFunctionCalling
 * </pre>
 */
object ChatStreamWithAutomaticFunctionCalling {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = args.firstOrNull() ?: GEMINI_MODEL_NAME

      // A mocked function to keep the example short. Use the real one you want here.
      val getWeather =
        callableFunction(
          "get_weather",
          description = "Looks up the current weather for a city.",
          paramName = "city",
        ) { city: String ->
          println("\n[ran get_weather(city=$city)]")
          "18 degrees and sunny in $city"
        }

      // Instantiate the client (use block automatically closes the client)
      Client().use { client ->
        val chat =
          client.chats.create(
            model = modelId,
            automaticFunctionCalling = AutomaticFunctionCalling(getWeather),
          )

        // The flow spans the whole exchange, however many requests it takes. Everything the model
        // sends is emitted, function calls included; the results this side sends back are not,
        // since they are you answering the model rather than the model speaking.
        print("Model: ")
        chat.sendMessageStream("What is the weather in Zurich?").collect { chunk ->
          chunk.functionCalls?.forEach { call -> println("\n[model asked for ${call.name}]") }
          chunk.text?.let { print(it) }
        }
        println()

        val answered =
          chat.getHistory().flatMap { it.parts.orEmpty() }.count { it.functionResponse != null }
        println("\nFunction results sent back for you: $answered")
      }

      kotlin.system.exitProcess(0)
    }
}
