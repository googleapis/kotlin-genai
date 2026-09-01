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
import com.google.genai.kotlin.Describe
import com.google.genai.kotlin.callableFunction
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

/** Parameters for `plan_trip`. The fields are the parameters, so they need no separate names. */
@Serializable
data class PlanTripArgs(
  @Describe("City the trip starts from.") val origin: String,
  @Describe("City the trip ends in.") val destination: String,
  @Describe("Number of nights to stay.") val nights: Int = 2,
)

/**
 * An example of using the Google Gen AI Kotlin SDK to let the model call your functions during a
 * chat.
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
 *   -PmainClass=com.google.genai.kotlin.examples.ChatWithAutomaticFunctionCalling
 * </pre>
 */
object ChatWithAutomaticFunctionCalling {
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
          println("[ran get_weather(city=$city)]")
          "18 degrees and sunny in $city"
        }

      // A function taking a @Serializable class instead. Its fields are the parameters, and
      // @Describe documents them for the model.
      val planTrip =
        callableFunction("plan_trip", description = "Plans a trip between two cities.") {
          trip: PlanTripArgs ->
          println("[ran plan_trip($trip)]")
          "Take the train from ${trip.origin} to ${trip.destination} and stay ${trip.nights} nights"
        }

      // Instantiate the client (use block automatically closes the client)
      Client().use { client ->
        // Passing automaticFunctionCalling is what turns this on. Leave it out and the model's
        // function calls come back to you to run and answer yourself.
        val chat =
          client.chats.create(
            model = modelId,
            automaticFunctionCalling = AutomaticFunctionCalling(getWeather, planTrip),
          )

        // One call, several requests: the model asks for a function, the SDK runs it and sends the
        // result back, and this returns once the model has an answer.
        val response =
          chat.sendMessage("What is the weather in Zurich, and how do I get there from Munich?")
        println("\nModel: ${response.text}")

        // The whole exchange is one turn: your message, the model's call, the result the SDK sent
        // back on your behalf, and the model's answer.
        println("\nHistory:")
        chat.getHistory().forEach { content ->
          val parts =
            content.parts.orEmpty().joinToString(", ") { part ->
              when {
                part.functionCall != null -> "calls ${part.functionCall?.name}"
                part.functionResponse != null -> "answers ${part.functionResponse?.name}"
                else -> "text"
              }
            }
          println("  ${content.role}: $parts")
        }
      }

      kotlin.system.exitProcess(0)
    }
}
