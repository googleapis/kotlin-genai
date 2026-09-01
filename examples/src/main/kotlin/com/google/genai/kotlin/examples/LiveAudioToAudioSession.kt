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
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.LiveConnectConfig
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to interact with Live API using Audio to Audio.
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
 * ./gradlew :examples:runExample -PmainClass=com.google.genai.kotlin.examples.LiveAudioToAudioSession
 * </pre>
 */
object LiveAudioToAudioSession {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      Client().use { client ->
        println(
          "Connecting to Live Session from ${if (client.enterprise) "GEAP" else "Gemini"} API with model: $LIVE_MODEL_NAME..."
        )

        // Optional. Enable input/output transcription.
        val config =
          LiveConnectConfig(
            inputAudioTranscription = AudioTranscriptionConfig(),
            outputAudioTranscription = AudioTranscriptionConfig(),
          )

        client.live.connect(LIVE_MODEL_NAME, config).use { session ->
          println("\nConnected! Sending audio message...")

          // Send an actual PCM audio buffer (16kHz, 16-bit, mono) from resources
          // In a real application, you would capture this from a microphone
          val audioBytes =
            LiveAudioToAudioSession::class
              .java
              .getResourceAsStream("/hello_are_you_there.pcm")
              ?.readBytes()
              ?: throw IllegalStateException("Resource /hello_are_you_there.pcm not found")

          session.sendRealtimeInput(
            audio = Blob(data = audioBytes, mimeType = "audio/pcm;rate=16000")
          )
          session.sendRealtimeInput(audioStreamEnd = true)

          // Directly read the stream on the main thread until the turn completes
          session
            .receive()
            .catch { e -> println("\nSession closed or error: ${e.message}") }
            .collect { serverMessage ->
              serverMessage.setupComplete?.let { println("[Server Setup Complete: $it]") }
              serverMessage.serverContent?.run {
                // Receive audio bytes if any
                serverMessage.data?.let {
                  println("\n[Model Turn Audio Data: ${it.size} bytes]")
                  // Do something to process the audio data
                }
                // Print out text in inputTranscription if any
                inputTranscription?.text?.let { println("\n[Input Transcription: $it]") }
                // Print out text in outputTranscription if any
                outputTranscription?.text?.let { println("\n[Output Transcription: $it]") }

                if (turnComplete == true || interrupted == true) {
                  println(
                    "\n[Server: ${if (turnComplete == true) "Turn Complete" else "Interrupted"}]"
                  )
                  // Gracefully exits the WebSocket and ends the collect loop
                  session.closeSession()
                }
              }
            }
        }
      }
      kotlin.system.exitProcess(0)
    }
}
