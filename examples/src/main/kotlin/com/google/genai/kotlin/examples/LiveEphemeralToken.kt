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
import com.google.genai.kotlin.ExperimentalGenAiApi
import com.google.genai.kotlin.types.CreateAuthTokenConfig
import com.google.genai.kotlin.types.HttpOptions
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.LiveConnectConstraints
import com.google.genai.kotlin.types.Modality
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to create an ephemeral auth token and use it to
 * connect to the Live API.
 *
 * Usage:
 *
 * 1. Set your Gemini API key: export GOOGLE_API_KEY=YOUR_API_KEY
 *
 * 2. Run the example:
 * <pre>
 * ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.LiveEphemeralToken
 * </pre>
 */
object LiveEphemeralToken {
  @OptIn(ExperimentalGenAiApi::class)
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val model = "gemini-3.1-flash-live-preview"

      // 1. Create an ephemeral auth token using standard credentials (e.g. backend server).
      val token =
        Client().use { client ->
          println("Creating ephemeral auth token for Live API...")
          val tokenConfig =
            CreateAuthTokenConfig(
              uses = 1,
              liveConnectConstraints =
                LiveConnectConstraints(
                  model = model,
                  config =
                    LiveConnectConfig(
                      responseModalities = listOf(Modality.AUDIO),
                      temperature = 0.7,
                    ),
                ),
              httpOptions = HttpOptions(apiVersion = "v1alpha"),
            )
          client.authTokens.create(tokenConfig)
        }

      val tokenName = token.name ?: error("Token creation failed, name is null.")
      println("Created ephemeral token: $tokenName")

      // 2. Initialize a new client with the ephemeral token (e.g. client app).
      Client(apiKey = tokenName, httpOptions = HttpOptions(apiVersion = "v1alpha")).use { client ->
        println("\nConnecting to Live API using ephemeral token...")

        client.live.connect(model).use { session ->
          println("Connected! Sending a message...")
          session.sendRealtimeInput(text = "Hello from an ephemeral token session!")

          session
            .receive()
            .catch { e -> println("\nSession closed or error: ${e.message}") }
            .collect { serverMessage ->
              serverMessage.setupComplete?.let { println("[Server Setup Complete: $it]") }
              serverMessage.serverContent?.run {
                serverMessage.data?.let { println("\n[Model Audio Data: ${it.size} bytes]") }
                modelTurn?.parts?.forEach { part ->
                  part.text?.let { println("\n[Model Text: $it]") }
                }
                if (turnComplete == true || interrupted == true) {
                  println("\n[Server: Turn Complete]")
                  session.closeSession()
                }
              }
            }
        }
      }
      kotlin.system.exitProcess(0)
    }
}
