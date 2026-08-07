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
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to hold a multi-turn chat, streaming each
 * response as it arrives.
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
 * ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.ChatStream
 * </pre>
 */
object ChatStream {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = args.firstOrNull() ?: "gemini-3.6-flash"

      // Instantiate the client (use block automatically closes the client)
      Client().use { client ->
        val chat = client.chats.create(model = modelId)

        // The flow is cold: the message is sent when the flow is collected, not when
        // sendMessageStream returns, and the turn joins the history once the flow completes. Let
        // one turn finish before starting the next, or the second message reaches the model first.
        print("Model: ")
        chat.sendMessageStream("Tell me a two sentence story about a robot.").collect { chunk ->
          chunk.text?.let { print(it) }
        }
        println()

        print("Model: ")
        chat.sendMessageStream("Give the robot a name.").collect { chunk ->
          chunk.text?.let { print(it) }
        }
        println()

        // Streaming keeps every chunk, so a streamed answer adds one entry per chunk rather
        // than one for the whole response.
        println("\nHistory entries: ${chat.getHistory().size}")
      }

      kotlin.system.exitProcess(0)
    }
}
