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
import com.google.genai.kotlin.types.UpdateModelConfig
import kotlinx.coroutines.runBlocking

/**
 * An example of using the Google Gen AI Kotlin SDK to update a tuned model.
 *
 * Note: update is only supported by the Gemini Enterprise Agent Platform API.
 *
 * Usage:
 *
 * 1. Setup ADC to get credentials:
 *    https://cloud.google.com/docs/authentication/provide-credentials-adc#google-idp
 *
 * 2. Set Project, Location, and GOOGLE_GENAI_USE_ENTERPRISE flag as environment variables:
 *
 * export GOOGLE_CLOUD_PROJECT=YOUR_PROJECT
 *
 * export GOOGLE_CLOUD_LOCATION=YOUR_LOCATION
 *
 * export GOOGLE_GENAI_USE_ENTERPRISE=true
 *
 * Note: This method is not supported by the Gemini Developer API.
 *
 * 3. Run the example:
 * <pre>
 * ./gradlew :example:runExample -PmainClass=com.google.genai.kotlin.examples.UpdateTunedModel --args="projects/your-project-id/locations/us-central1/models/your-model-id"
 * </pre>
 */
object UpdateTunedModel {
  @JvmStatic
  fun main(args: Array<String>) =
    runBlocking<Unit> {
      val modelId = args.firstOrNull()

      if (modelId == null) {
        System.err.println("Please provide a tuned model ID as the first argument.")
        System.err.println(
          "Example: projects/your-project-id/locations/us-central1/models/your-model-id"
        )
        kotlin.system.exitProcess(1)
      }

      Client().use { client ->
        if (!client.enterprise) {
          System.err.println(
            "Error: update is only supported when using the Gemini Enterprise Agent Platform API."
          )
          System.err.println(
            "Please set GOOGLE_GENAI_USE_ENTERPRISE=true along with your project and location environment variables."
          )
          kotlin.system.exitProcess(1)
        }

        val config =
          UpdateModelConfig(
            displayName = "New Display Name",
            description = "Updated description for my tuned model",
            updateMask = "display_name,description",
          )

        println("Updating tuned model: $modelId")
        val updatedModel = client.models.update(model = modelId, config = config)
        println("Successfully updated model: ${updatedModel.name}")
      }

      kotlin.system.exitProcess(0)
    }
}
