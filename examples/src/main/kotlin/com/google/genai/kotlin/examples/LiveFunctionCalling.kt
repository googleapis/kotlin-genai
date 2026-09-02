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
import com.google.genai.kotlin.types.AudioTranscriptionConfig
import com.google.genai.kotlin.types.FunctionDeclaration
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.Schema
import com.google.genai.kotlin.types.Tool
import com.google.genai.kotlin.types.Type
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonPrimitive

/**
 * An example of using the Google Gen AI Kotlin SDK to interact with Live API using function
 * calling.
 *
 * Usage:
 *
 * 1a. If you are using Gemini Enterprise Agent Platform, setup ADC to get credentials:
 * https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * Then set Project, Location, and GOOGLE_GENAI_USE_ENTERPRISE flag as environment variables. The
 * Live model is not served in the `global` location, so choose a region:
 *
 * export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * export GOOGLE_CLOUD_LOCATION=us-central1
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
 * ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.LiveFunctionCalling
 * </pre>
 */
object LiveFunctionCalling {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      Client().use { client ->
        val model = liveModelName(client)
        println(
          "Connecting to Live Session from ${if (client.enterprise) "GEAP" else "Gemini"} API with model: $model..."
        )

        val getWeatherDeclaration =
          FunctionDeclaration(
            name = "GetWeather",
            description = "return the real time weather of the location",
            parameters =
              Schema(
                type = Type.OBJECT,
                properties = mapOf("location" to Schema(type = Type.STRING)),
                required = listOf("location"),
              ),
          )

        val config =
          LiveConnectConfig(
            tools = listOf(Tool(functionDeclarations = listOf(getWeatherDeclaration))),
            outputAudioTranscription = AudioTranscriptionConfig(),
          )

        client.live.connect(model, config).use { session ->
          println("\nConnected! Asking for the weather...")

          session.sendRealtimeInput(text = "What is the weather in Seattle?")

          // Close the session automatically after 15 seconds
          launch {
            delay(15_000)
            println("\n[15 seconds elapsed, closing session...]")
            session.closeSession()
          }

          // Directly read the stream on the main thread
          session
            .receive()
            .catch { e -> println("\nSession closed or error: ${e.message}") }
            .collect { serverMessage ->
              serverMessage.setupComplete?.let { println("[Server Setup Complete]") }

              serverMessage.toolCall?.let { toolCall ->
                println("\n[Server requests tool call: ${toolCall}]")
                val functionResponses =
                  toolCall.functionCalls?.map { call ->
                    FunctionResponse(
                      id = call.id,
                      name = call.name,
                      response = mapOf("temperature" to JsonPrimitive("72F")),
                    )
                  }
                if (functionResponses != null) {
                  println("  -> Sending tool responses: $functionResponses")
                  session.sendToolResponse(functionResponses)
                }
              }

              serverMessage.serverContent?.run {
                // Receive audio bytes if any
                serverMessage.data?.let {
                  println("\n[Model Turn Audio Data: ${it.size} bytes]")
                  // Do something to process the audio data
                }
                // Print out text in outputTranscription if any
                outputTranscription?.text?.let { println("\n[Output Transcription: $it]") }
              }
            }
        }
      }
      kotlin.system.exitProcess(0)
    }
}
