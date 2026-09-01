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
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to hold a multi-turn chat.
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
 * ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.Chat
 * </pre>
 */
object Chat {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = args.firstOrNull() ?: GEMINI_MODEL_NAME

      // Instantiate the client (use block automatically closes the client)
      Client().use { client ->
        val config =
          GenerateContentConfig(
            systemInstruction = Content.fromText("You are a concise assistant.")
          )

        // The session applies this config to every turn and remembers the conversation, so earlier
        // turns are sent along with each new message.
        val chat = client.chats.create(model = modelId, config = config)

        val first = chat.sendMessage("My favourite colour is blue.")
        println("Model: ${first.text}")

        // Answering this needs the first turn, which the session sends automatically.
        val second = chat.sendMessage("What is my favourite colour?")
        println("Model: ${second.text}")

        // Two entries per exchange: the message and the response.
        println("\nHistory entries: ${chat.getHistory().size}")
      }

      kotlin.system.exitProcess(0)
    }
}
