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

// Auto-generated code. Do not edit.

@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER", "UNUSED_ANONYMOUS_PARAMETER")

package com.google.genai.kotlin

import com.google.genai.kotlin.types.CancelTuningJobConfig
import com.google.genai.kotlin.types.CancelTuningJobParameters
import com.google.genai.kotlin.types.CancelTuningJobResponse
import com.google.genai.kotlin.types.CompositeReinforcementTuningRewardConfig
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CreateTuningJobConfig
import com.google.genai.kotlin.types.CreateTuningJobParametersPrivate
import com.google.genai.kotlin.types.GetTuningJobConfig
import com.google.genai.kotlin.types.GetTuningJobParameters
import com.google.genai.kotlin.types.HttpResponse
import com.google.genai.kotlin.types.JobState
import com.google.genai.kotlin.types.ListTuningJobsConfig
import com.google.genai.kotlin.types.ListTuningJobsParameters
import com.google.genai.kotlin.types.ListTuningJobsResponse
import com.google.genai.kotlin.types.PreTunedModel
import com.google.genai.kotlin.types.ReinforcementTuningExample
import com.google.genai.kotlin.types.SingleReinforcementTuningRewardConfig
import com.google.genai.kotlin.types.TuningDataset
import com.google.genai.kotlin.types.TuningJob
import com.google.genai.kotlin.types.TuningOperation
import com.google.genai.kotlin.types.ValidateRewardConfig
import com.google.genai.kotlin.types.ValidateRewardParameters
import com.google.genai.kotlin.types.ValidateRewardResponse
import io.ktor.http.encodeURLQueryComponent

class Tunings internal constructor(internal val apiClient: ApiClient) {

  internal fun cancelTuningJobParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    return toObject
  }

  internal fun cancelTuningJobParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    return toObject
  }

  internal fun cancelTuningJobResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    return toObject
  }

  internal fun cancelTuningJobResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    return toObject
  }

  internal fun codeExecutionResultToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("outcome"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("outcome"),
        Common.getValueByPath(fromObject, arrayOf("outcome")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("output"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("output"),
        Common.getValueByPath(fromObject, arrayOf("output")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("id")))) {
      throw IllegalArgumentException(
        "id parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun contentToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("parts"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(partToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("parts"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("role"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("role"),
        Common.getValueByPath(fromObject, arrayOf("role")),
      )
    }

    return toObject
  }

  internal fun createTuningJobConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("validationDataset")))) {
      throw IllegalArgumentException("validationDataset parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("tunedModelDisplayName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("tunedModelDisplayName")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("description")))) {
      throw IllegalArgumentException("description parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("epochCount"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("tuningTask", "hyperparameters", "epochCount"),
        Common.getValueByPath(fromObject, arrayOf("epochCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tuningTask", "hyperparameters", "learningRateMultiplier"),
        Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly")))) {
      throw IllegalArgumentException(
        "exportLastCheckpointOnly parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("preTunedModelCheckpointId")))) {
      throw IllegalArgumentException(
        "preTunedModelCheckpointId parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("adapterSize")))) {
      throw IllegalArgumentException("adapterSize parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("tuningMode")))) {
      throw IllegalArgumentException("tuningMode parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("customBaseModel")))) {
      throw IllegalArgumentException("customBaseModel parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("batchSize"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("tuningTask", "hyperparameters", "batchSize"),
        Common.getValueByPath(fromObject, arrayOf("batchSize")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("learningRate"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("tuningTask", "hyperparameters", "learningRate"),
        Common.getValueByPath(fromObject, arrayOf("learningRate")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("labels")))) {
      throw IllegalArgumentException("labels parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("beta")))) {
      throw IllegalArgumentException("beta parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("baseTeacherModel")))) {
      throw IllegalArgumentException("baseTeacherModel parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("tunedTeacherModelSource")))) {
      throw IllegalArgumentException(
        "tunedTeacherModelSource parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("sftLossWeightMultiplier")))) {
      throw IllegalArgumentException(
        "sftLossWeightMultiplier parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("outputUri")))) {
      throw IllegalArgumentException("outputUri parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("rewardConfig")))) {
      throw IllegalArgumentException("rewardConfig parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("compositeRewardConfig")))) {
      throw IllegalArgumentException(
        "compositeRewardConfig parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("samplesPerPrompt")))) {
      throw IllegalArgumentException("samplesPerPrompt parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("evaluateInterval")))) {
      throw IllegalArgumentException("evaluateInterval parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("checkpointInterval")))) {
      throw IllegalArgumentException("checkpointInterval parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")))) {
      throw IllegalArgumentException("maxOutputTokens parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("thinkingLevel")))) {
      throw IllegalArgumentException("thinkingLevel parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("validationDatasetUri")))) {
      throw IllegalArgumentException(
        "validationDatasetUri parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("encryptionSpec")))) {
      throw IllegalArgumentException("encryptionSpec parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun createTuningJobConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    val discriminatorValueValidationDataset =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("validationDataset"))?.let { validationDataset ->
      when (discriminatorValueValidationDataset) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("validationDataset"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec"),
              tuningValidationDatasetToVertex(
                Common.getValueByPath(fromObject, arrayOf("validationDataset"))
                  as Map<String, Any?>,
                toObject,
                rootObject,
              ),
            )
          }
        }
        "PREFERENCE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("validationDataset"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("preferenceOptimizationSpec"),
              tuningValidationDatasetToVertex(
                Common.getValueByPath(fromObject, arrayOf("validationDataset"))
                  as Map<String, Any?>,
                toObject,
                rootObject,
              ),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("validationDataset"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec"),
              tuningValidationDatasetToVertex(
                Common.getValueByPath(fromObject, arrayOf("validationDataset"))
                  as Map<String, Any?>,
                toObject,
                rootObject,
              ),
            )
          }
        }
        "REINFORCEMENT_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("validationDataset"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("reinforcementTuningSpec"),
              tuningValidationDatasetToVertex(
                Common.getValueByPath(fromObject, arrayOf("validationDataset"))
                  as Map<String, Any?>,
                toObject,
                rootObject,
              ),
            )
          }
        }
        else -> {}
      }
    }

    Common.getValueByPath(fromObject, arrayOf("tunedModelDisplayName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("tunedModelDisplayName"),
        Common.getValueByPath(fromObject, arrayOf("tunedModelDisplayName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("description"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("description"),
        Common.getValueByPath(fromObject, arrayOf("description")),
      )
    }

    val discriminatorValueEpochCount =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("epochCount"))?.let { epochCount ->
      when (discriminatorValueEpochCount) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("epochCount"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "hyperParameters", "epochCount"),
              Common.getValueByPath(fromObject, arrayOf("epochCount")),
            )
          }
        }
        "PREFERENCE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("epochCount"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("preferenceOptimizationSpec", "hyperParameters", "epochCount"),
              Common.getValueByPath(fromObject, arrayOf("epochCount")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("epochCount"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "hyperParameters", "epochCount"),
              Common.getValueByPath(fromObject, arrayOf("epochCount")),
            )
          }
        }
        "REINFORCEMENT_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("epochCount"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("reinforcementTuningSpec", "hyperParameters", "epochCount"),
              Common.getValueByPath(fromObject, arrayOf("epochCount")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueLearningRateMultiplier =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier"))?.let {
      learningRateMultiplier ->
      when (discriminatorValueLearningRateMultiplier) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "hyperParameters", "learningRateMultiplier"),
              Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier")),
            )
          }
        }
        "PREFERENCE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("preferenceOptimizationSpec", "hyperParameters", "learningRateMultiplier"),
              Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "hyperParameters", "learningRateMultiplier"),
              Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier")),
            )
          }
        }
        "REINFORCEMENT_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("reinforcementTuningSpec", "hyperParameters", "learningRateMultiplier"),
              Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueExportLastCheckpointOnly =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly"))?.let {
      exportLastCheckpointOnly ->
      when (discriminatorValueExportLastCheckpointOnly) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "exportLastCheckpointOnly"),
              Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly")),
            )
          }
        }
        "PREFERENCE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("preferenceOptimizationSpec", "exportLastCheckpointOnly"),
              Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "exportLastCheckpointOnly"),
              Common.getValueByPath(fromObject, arrayOf("exportLastCheckpointOnly")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueAdapterSize =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("adapterSize"))?.let { adapterSize ->
      when (discriminatorValueAdapterSize) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("adapterSize"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "hyperParameters", "adapterSize"),
              Common.getValueByPath(fromObject, arrayOf("adapterSize")),
            )
          }
        }
        "PREFERENCE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("adapterSize"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("preferenceOptimizationSpec", "hyperParameters", "adapterSize"),
              Common.getValueByPath(fromObject, arrayOf("adapterSize")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("adapterSize"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "hyperParameters", "adapterSize"),
              Common.getValueByPath(fromObject, arrayOf("adapterSize")),
            )
          }
        }
        "REINFORCEMENT_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("adapterSize"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("reinforcementTuningSpec", "hyperParameters", "adapterSize"),
              Common.getValueByPath(fromObject, arrayOf("adapterSize")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueTuningMode =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("tuningMode"))?.let { tuningMode ->
      when (discriminatorValueTuningMode) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("tuningMode"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "tuningMode"),
              Common.getValueByPath(fromObject, arrayOf("tuningMode")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("tuningMode"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "tuningMode"),
              Common.getValueByPath(fromObject, arrayOf("tuningMode")),
            )
          }
        }
        else -> {}
      }
    }

    Common.getValueByPath(fromObject, arrayOf("customBaseModel"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("customBaseModel"),
        Common.getValueByPath(fromObject, arrayOf("customBaseModel")),
      )
    }

    val discriminatorValueBatchSize =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("batchSize"))?.let { batchSize ->
      when (discriminatorValueBatchSize) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("batchSize"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "hyperParameters", "batchSize"),
              Common.getValueByPath(fromObject, arrayOf("batchSize")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("batchSize"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "hyperParameters", "batchSize"),
              Common.getValueByPath(fromObject, arrayOf("batchSize")),
            )
          }
        }
        "REINFORCEMENT_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("batchSize"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("reinforcementTuningSpec", "hyperParameters", "batchSize"),
              Common.getValueByPath(fromObject, arrayOf("batchSize")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueLearningRate =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("learningRate"))?.let { learningRate ->
      when (discriminatorValueLearningRate) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("learningRate"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "hyperParameters", "learningRate"),
              Common.getValueByPath(fromObject, arrayOf("learningRate")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("learningRate"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "hyperParameters", "learningRate"),
              Common.getValueByPath(fromObject, arrayOf("learningRate")),
            )
          }
        }
        else -> {}
      }
    }

    Common.getValueByPath(fromObject, arrayOf("labels"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("labels"),
        Common.getValueByPath(fromObject, arrayOf("labels")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("beta"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("preferenceOptimizationSpec", "hyperParameters", "beta"),
        Common.getValueByPath(fromObject, arrayOf("beta")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("baseTeacherModel"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("distillationSpec", "baseTeacherModel"),
        Common.getValueByPath(fromObject, arrayOf("baseTeacherModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tunedTeacherModelSource"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("distillationSpec", "tunedTeacherModelSource"),
        Common.getValueByPath(fromObject, arrayOf("tunedTeacherModelSource")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("sftLossWeightMultiplier"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("distillationSpec", "hyperParameters", "sftLossWeightMultiplier"),
        Common.getValueByPath(fromObject, arrayOf("sftLossWeightMultiplier")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputUri"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("outputUri"),
        Common.getValueByPath(fromObject, arrayOf("outputUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("rewardConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "singleRewardConfig"),
        Common.getValueByPath(fromObject, arrayOf("rewardConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("compositeRewardConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "compositeRewardConfig"),
        Common.getValueByPath(fromObject, arrayOf("compositeRewardConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("samplesPerPrompt"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "hyperParameters", "samplesPerPrompt"),
        Common.getValueByPath(fromObject, arrayOf("samplesPerPrompt")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("evaluateInterval"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "hyperParameters", "evaluateInterval"),
        Common.getValueByPath(fromObject, arrayOf("evaluateInterval")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("checkpointInterval"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "hyperParameters", "checkpointInterval"),
        Common.getValueByPath(fromObject, arrayOf("checkpointInterval")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "hyperParameters", "maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingLevel"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "hyperParameters", "thinkingLevel"),
        Common.getValueByPath(fromObject, arrayOf("thinkingLevel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("validationDatasetUri"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("reinforcementTuningSpec", "validationDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("validationDatasetUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("encryptionSpec"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("encryptionSpec"),
        Common.getValueByPath(fromObject, arrayOf("encryptionSpec")),
      )
    }

    return toObject
  }

  internal fun createTuningJobParametersPrivateToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("baseModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("baseModel"),
        Common.getValueByPath(fromObject, arrayOf("baseModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("preTunedModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("preTunedModel"),
        Common.getValueByPath(fromObject, arrayOf("preTunedModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("trainingDataset"))?.let { node ->
      val unused =
        tuningDatasetToMldev(
          Common.getValueByPath(fromObject, arrayOf("trainingDataset")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        createTuningJobConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    return toObject
  }

  internal fun createTuningJobParametersPrivateToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("baseModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("baseModel"),
        Common.getValueByPath(fromObject, arrayOf("baseModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("preTunedModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("preTunedModel"),
        Common.getValueByPath(fromObject, arrayOf("preTunedModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("trainingDataset"))?.let { node ->
      val unused =
        tuningDatasetToVertex(
          Common.getValueByPath(fromObject, arrayOf("trainingDataset")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        createTuningJobConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    return toObject
  }

  internal fun distillationHyperParametersFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("epochCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("epochCount"),
        Common.getValueByPath(fromObject, arrayOf("epochCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("learningRateMultiplier"),
        Common.getValueByPath(fromObject, arrayOf("learningRateMultiplier")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("adapterSize"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("adapterSize"),
        Common.getValueByPath(fromObject, arrayOf("adapterSize")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("batchSize"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("batchSize"),
        Common.getValueByPath(fromObject, arrayOf("batchSize")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("learningRate"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("learningRate"),
        Common.getValueByPath(fromObject, arrayOf("learningRate")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("generationConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("generationConfig"),
        generationConfigFromVertex(
          Common.getValueByPath(fromObject, arrayOf("generationConfig")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun distillationSamplingSpecFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("promptDatasetUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("promptDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("promptDatasetUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("validationDatasetUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("validationDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("validationDatasetUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("baseTeacherModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("baseTeacherModel"),
        Common.getValueByPath(fromObject, arrayOf("baseTeacherModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tunedTeacherModelSource"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tunedTeacherModelSource"),
        Common.getValueByPath(fromObject, arrayOf("tunedTeacherModelSource")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("hyperparameters"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("hyperparameters"),
        distillationHyperParametersFromVertex(
          Common.getValueByPath(fromObject, arrayOf("hyperparameters")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun distillationSpecFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("baseTeacherModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("baseTeacherModel"),
        Common.getValueByPath(fromObject, arrayOf("baseTeacherModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("hyperParameters"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("hyperParameters"),
        distillationHyperParametersFromVertex(
          Common.getValueByPath(fromObject, arrayOf("hyperParameters")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("pipelineRootDirectory"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("pipelineRootDirectory"),
        Common.getValueByPath(fromObject, arrayOf("pipelineRootDirectory")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("promptDatasetUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("promptDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("promptDatasetUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("studentModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("studentModel"),
        Common.getValueByPath(fromObject, arrayOf("studentModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("trainingDatasetUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("trainingDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("trainingDatasetUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tunedTeacherModelSource"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tunedTeacherModelSource"),
        Common.getValueByPath(fromObject, arrayOf("tunedTeacherModelSource")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tuningMode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tuningMode"),
        Common.getValueByPath(fromObject, arrayOf("tuningMode")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("validationDatasetUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("validationDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("validationDatasetUri")),
      )
    }

    return toObject
  }

  internal fun executableCodeToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("code"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("code"),
        Common.getValueByPath(fromObject, arrayOf("code")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("language"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("language"),
        Common.getValueByPath(fromObject, arrayOf("language")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("id")))) {
      throw IllegalArgumentException(
        "id parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun generationConfigFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("modelConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelSelectionConfig"),
        Common.getValueByPath(fromObject, arrayOf("modelConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseJsonSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseJsonSchema"),
        Common.getValueByPath(fromObject, arrayOf("responseJsonSchema")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioTranscriptionConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioTranscriptionConfig"),
        Common.getValueByPath(fromObject, arrayOf("audioTranscriptionConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioTimestamp"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioTimestamp"),
        Common.getValueByPath(fromObject, arrayOf("audioTimestamp")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidateCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("candidateCount"),
        Common.getValueByPath(fromObject, arrayOf("candidateCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enableAffectiveDialog"),
        Common.getValueByPath(fromObject, arrayOf("enableAffectiveDialog")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("frequencyPenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("frequencyPenalty"),
        Common.getValueByPath(fromObject, arrayOf("frequencyPenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("logprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("logprobs"),
        Common.getValueByPath(fromObject, arrayOf("logprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("presencePenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("presencePenalty"),
        Common.getValueByPath(fromObject, arrayOf("presencePenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseFormat"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseFormat"),
        Common.getValueByPath(fromObject, arrayOf("responseFormat")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseLogprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseLogprobs"),
        Common.getValueByPath(fromObject, arrayOf("responseLogprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseMimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseMimeType"),
        Common.getValueByPath(fromObject, arrayOf("responseMimeType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseModalities"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseModalities"),
        Common.getValueByPath(fromObject, arrayOf("responseModalities")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseSchema"),
        Common.getValueByPath(fromObject, arrayOf("responseSchema")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("routingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("routingConfig"),
        Common.getValueByPath(fromObject, arrayOf("routingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("seed"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("seed"),
        Common.getValueByPath(fromObject, arrayOf("seed")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("speechConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("speechConfig"),
        Common.getValueByPath(fromObject, arrayOf("speechConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("stopSequences"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("stopSequences"),
        Common.getValueByPath(fromObject, arrayOf("stopSequences")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("temperature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("temperature"),
        Common.getValueByPath(fromObject, arrayOf("temperature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thinkingConfig"),
        Common.getValueByPath(fromObject, arrayOf("thinkingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topK"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topK"),
        Common.getValueByPath(fromObject, arrayOf("topK")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topP"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topP"),
        Common.getValueByPath(fromObject, arrayOf("topP")),
      )
    }

    return toObject
  }

  internal fun getTuningJobParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    return toObject
  }

  internal fun getTuningJobParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    return toObject
  }

  internal fun listTuningJobsConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("pageSize"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("_query", "pageSize"),
        Common.getValueByPath(fromObject, arrayOf("pageSize")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("pageToken"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("_query", "pageToken"),
        Common.getValueByPath(fromObject, arrayOf("pageToken")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("filter"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("_query", "filter"),
        Common.getValueByPath(fromObject, arrayOf("filter")),
      )
    }

    return toObject
  }

  internal fun listTuningJobsParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        listTuningJobsConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
          rootObject,
        )
    }

    return toObject
  }

  internal fun listTuningJobsResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("nextPageToken"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("nextPageToken"),
        Common.getValueByPath(fromObject, arrayOf("nextPageToken")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tuningJobs"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(tuningJobFromVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("tuningJobs"), result)
    }

    return toObject
  }

  internal fun partToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("toolCall")))) {
      throw IllegalArgumentException(
        "toolCall parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("toolResponse")))) {
      throw IllegalArgumentException(
        "toolResponse parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("audioTranscription"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("audioTranscription"),
        Common.getValueByPath(fromObject, arrayOf("audioTranscription")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("codeExecutionResult"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("codeExecutionResult"),
        codeExecutionResultToVertex(
          Common.getValueByPath(fromObject, arrayOf("codeExecutionResult")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("executableCode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("executableCode"),
        executableCodeToVertex(
          Common.getValueByPath(fromObject, arrayOf("executableCode")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("fileData"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileData"),
        Common.getValueByPath(fromObject, arrayOf("fileData")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionCall"),
        Common.getValueByPath(fromObject, arrayOf("functionCall")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionResponse"),
        Common.getValueByPath(fromObject, arrayOf("functionResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inlineData"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("inlineData"),
        Common.getValueByPath(fromObject, arrayOf("inlineData")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("text"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("text"),
        Common.getValueByPath(fromObject, arrayOf("text")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thought"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thought"),
        Common.getValueByPath(fromObject, arrayOf("thought")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("thoughtSignature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thoughtSignature"),
        Common.getValueByPath(fromObject, arrayOf("thoughtSignature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("videoMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("videoMetadata"),
        Common.getValueByPath(fromObject, arrayOf("videoMetadata")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("partMetadata")))) {
      throw IllegalArgumentException(
        "partMetadata parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun reinforcementTuningExampleToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("references"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("references"),
        Common.getValueByPath(fromObject, arrayOf("references")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToVertex(item as Map<String, Any?>, toObject, rootObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("systemInstruction"),
        contentToVertex(
          Common.getValueByPath(fromObject, arrayOf("systemInstruction")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun tunedModelFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("endpoint"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    return toObject
  }

  internal fun tuningDatasetToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("gcsUri")))) {
      throw IllegalArgumentException("gcsUri parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource")))) {
      throw IllegalArgumentException(
        "vertexDatasetResource parameter is not supported in Gemini API."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("examples"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("examples", "examples"),
        Common.getValueByPath(fromObject, arrayOf("examples")),
      )
    }

    return toObject
  }

  internal fun tuningDatasetToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    val discriminatorValueGcsUri =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { gcsUri ->
      when (discriminatorValueGcsUri) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "trainingDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("gcsUri")),
            )
          }
        }
        "PREFERENCE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("preferenceOptimizationSpec", "trainingDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("gcsUri")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "promptDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("gcsUri")),
            )
          }
        }
        "REINFORCEMENT_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("reinforcementTuningSpec", "trainingDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("gcsUri")),
            )
          }
        }
        else -> {}
      }
    }

    val discriminatorValueVertexDatasetResource =
      Common.getValueByPath(rootObject, arrayOf("config", "method"))?.toString()
        ?: "SUPERVISED_FINE_TUNING"

    Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource"))?.let { vertexDatasetResource
      ->
      when (discriminatorValueVertexDatasetResource) {
        "SUPERVISED_FINE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("supervisedTuningSpec", "trainingDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource")),
            )
          }
        }
        "PREFERENCE_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("preferenceOptimizationSpec", "trainingDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource")),
            )
          }
        }
        "DISTILLATION" -> {
          Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("distillationSpec", "promptDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource")),
            )
          }
        }
        "REINFORCEMENT_TUNING" -> {
          Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource"))?.let { node ->
            Common.setValueByPath(
              parentObject,
              arrayOf("reinforcementTuningSpec", "trainingDatasetUri"),
              Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource")),
            )
          }
        }
        else -> {}
      }
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("examples")))) {
      throw IllegalArgumentException(
        "examples parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun tuningJobFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("state"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("state"),
        Transformers.tTuningJobStatus(Common.getValueByPath(fromObject, arrayOf("state"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("createTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("createTime"),
        Common.getValueByPath(fromObject, arrayOf("createTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tuningTask", "startTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("startTime"),
        Common.getValueByPath(fromObject, arrayOf("tuningTask", "startTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tuningTask", "completeTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("endTime"),
        Common.getValueByPath(fromObject, arrayOf("tuningTask", "completeTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("updateTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("updateTime"),
        Common.getValueByPath(fromObject, arrayOf("updateTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("description"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("description"),
        Common.getValueByPath(fromObject, arrayOf("description")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("baseModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("baseModel"),
        Common.getValueByPath(fromObject, arrayOf("baseModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("_self"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tunedModel"),
        tunedModelFromMldev(
          Common.getValueByPath(fromObject, arrayOf("_self")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    return toObject
  }

  internal fun tuningJobFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("state"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("state"),
        Transformers.tTuningJobStatus(Common.getValueByPath(fromObject, arrayOf("state"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("createTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("createTime"),
        Common.getValueByPath(fromObject, arrayOf("createTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("startTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("startTime"),
        Common.getValueByPath(fromObject, arrayOf("startTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("endTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("endTime"),
        Common.getValueByPath(fromObject, arrayOf("endTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("updateTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("updateTime"),
        Common.getValueByPath(fromObject, arrayOf("updateTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("error"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("error"),
        Common.getValueByPath(fromObject, arrayOf("error")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("description"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("description"),
        Common.getValueByPath(fromObject, arrayOf("description")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("baseModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("baseModel"),
        Common.getValueByPath(fromObject, arrayOf("baseModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tunedModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tunedModel"),
        Common.getValueByPath(fromObject, arrayOf("tunedModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("preTunedModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("preTunedModel"),
        Common.getValueByPath(fromObject, arrayOf("preTunedModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("supervisedTuningSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("supervisedTuningSpec"),
        Common.getValueByPath(fromObject, arrayOf("supervisedTuningSpec")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("preferenceOptimizationSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("preferenceOptimizationSpec"),
        Common.getValueByPath(fromObject, arrayOf("preferenceOptimizationSpec")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("distillationSamplingSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("distillationSamplingSpec"),
        distillationSamplingSpecFromVertex(
          Common.getValueByPath(fromObject, arrayOf("distillationSamplingSpec"))
            as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("distillationSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("distillationSpec"),
        distillationSpecFromVertex(
          Common.getValueByPath(fromObject, arrayOf("distillationSpec")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("reinforcementTuningSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("reinforcementTuningSpec"),
        Common.getValueByPath(fromObject, arrayOf("reinforcementTuningSpec")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tuningDataStats"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tuningDataStats"),
        Common.getValueByPath(fromObject, arrayOf("tuningDataStats")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("encryptionSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("encryptionSpec"),
        Common.getValueByPath(fromObject, arrayOf("encryptionSpec")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("partnerModelTuningSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("partnerModelTuningSpec"),
        Common.getValueByPath(fromObject, arrayOf("partnerModelTuningSpec")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("customBaseModel"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("customBaseModel"),
        Common.getValueByPath(fromObject, arrayOf("customBaseModel")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("evaluateDatasetRuns"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("evaluateDatasetRuns"),
        Common.getValueByPath(fromObject, arrayOf("evaluateDatasetRuns")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("experiment"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("experiment"),
        Common.getValueByPath(fromObject, arrayOf("experiment")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("fullFineTuningSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fullFineTuningSpec"),
        Common.getValueByPath(fromObject, arrayOf("fullFineTuningSpec")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("labels"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("labels"),
        Common.getValueByPath(fromObject, arrayOf("labels")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("outputUri"),
        Common.getValueByPath(fromObject, arrayOf("outputUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("pipelineJob"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("pipelineJob"),
        Common.getValueByPath(fromObject, arrayOf("pipelineJob")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("serviceAccount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("serviceAccount"),
        Common.getValueByPath(fromObject, arrayOf("serviceAccount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tunedModelDisplayName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tunedModelDisplayName"),
        Common.getValueByPath(fromObject, arrayOf("tunedModelDisplayName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tuningJobMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tuningJobMetadata"),
        Common.getValueByPath(fromObject, arrayOf("tuningJobMetadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tuningJobState"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tuningJobState"),
        Common.getValueByPath(fromObject, arrayOf("tuningJobState")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("veoLoraTuningSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("veoLoraTuningSpec"),
        Common.getValueByPath(fromObject, arrayOf("veoLoraTuningSpec")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("veoTuningSpec"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("veoTuningSpec"),
        Common.getValueByPath(fromObject, arrayOf("veoTuningSpec")),
      )
    }

    return toObject
  }

  internal fun tuningOperationFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("metadata"),
        Common.getValueByPath(fromObject, arrayOf("metadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("done"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("done"),
        Common.getValueByPath(fromObject, arrayOf("done")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("error"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("error"),
        Common.getValueByPath(fromObject, arrayOf("error")),
      )
    }

    return toObject
  }

  internal fun tuningValidationDatasetToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("validationDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("gcsUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("validationDatasetUri"),
        Common.getValueByPath(fromObject, arrayOf("vertexDatasetResource")),
      )
    }

    return toObject
  }

  internal fun validateRewardParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("parent"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "parent"),
        Common.getValueByPath(fromObject, arrayOf("parent")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("sampleResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sampleResponse"),
        contentToVertex(
          Common.getValueByPath(fromObject, arrayOf("sampleResponse")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("example"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("example"),
        reinforcementTuningExampleToVertex(
          Common.getValueByPath(fromObject, arrayOf("example")) as Map<String, Any?>,
          toObject,
          rootObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("singleRewardConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("singleRewardConfig"),
        Common.getValueByPath(fromObject, arrayOf("singleRewardConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("compositeRewardConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("compositeRewardConfig"),
        Common.getValueByPath(fromObject, arrayOf("compositeRewardConfig")),
      )
    }

    return toObject
  }

  internal fun validateRewardResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
    rootObject: Map<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("overallReward"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("overallReward"),
        Common.getValueByPath(fromObject, arrayOf("overallReward")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("error"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("error"),
        Common.getValueByPath(fromObject, arrayOf("error")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("rewardInfoDetails"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("rewardInfoDetails"),
        Common.getValueByPath(fromObject, arrayOf("rewardInfoDetails")),
      )
    }

    return toObject
  }

  internal suspend fun privateGet(name: String, config: GetTuningJobConfig? = null): TuningJob {
    val parameters = GetTuningJobParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = getTuningJobParametersToVertex(parameterMap, null, parameterMap)

      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    } else {

      body = getTuningJobParametersToMldev(parameterMap, null, parameterMap)

      path = Common.formatMap("{name}", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("GET", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = tuningJobFromVertex(responseMap, null, parameterMap)
    } else {
      responseMap = tuningJobFromMldev(responseMap, null, parameterMap)
    }

    val sdkResponse = Common.mapToDataClass<TuningJob>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  internal suspend fun privateList(config: ListTuningJobsConfig? = null): ListTuningJobsResponse {
    val parameters = ListTuningJobsParameters(config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = listTuningJobsParametersToVertex(parameterMap, null, parameterMap)

      path = Common.formatMap("tuningJobs", body["_url"] as? Map<String, Any?>)
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("GET", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = listTuningJobsResponseFromVertex(responseMap, null, parameterMap)
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val sdkResponse = Common.mapToDataClass<ListTuningJobsResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Makes an API request to list tuningJobs.
   *
   * @param config A [ListTuningJobsConfig] for configuring the list request.
   * @return A [Pager] of [TuningJob] objects.
   */
  fun list(config: ListTuningJobsConfig? = null): Pager<TuningJob> {
    val initialConfig = config ?: ListTuningJobsConfig()
    return Pager<TuningJob>(
      name = "tuning_jobs",
      pageSize = initialConfig.pageSize,
      request = { pageToken ->
        val cfg =
          if (pageToken != null) initialConfig.copy(pageToken = pageToken) else initialConfig
        val resp = privateList(config = cfg)
        Triple(resp.tuningJobs, resp.nextPageToken, resp.sdkHttpResponse)
      },
    )
  }

  /**
   * Cancels a tuning job resource.
   *
   * @param name The resource name of the tuning job. For Gemini Enterprise Agent Platform, this is
   *   the full resource name. For Gemini API, this is `tunedModels/{id}`.
   * @param config A [CancelTuningJobConfig] for configuring the cancel request.
   * @return A [CancelTuningJobResponse] object.
   */
  suspend fun cancel(name: String, config: CancelTuningJobConfig? = null): CancelTuningJobResponse {
    val parameters = CancelTuningJobParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = cancelTuningJobParametersToVertex(parameterMap, null, parameterMap)

      path = Common.formatMap("{name}:cancel", body["_url"] as? Map<String, Any?>)
    } else {

      body = cancelTuningJobParametersToMldev(parameterMap, null, parameterMap)

      path = Common.formatMap("{name}:cancel", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = cancelTuningJobResponseFromVertex(responseMap, null, parameterMap)
    } else {
      responseMap = cancelTuningJobResponseFromMldev(responseMap, null, parameterMap)
    }

    val sdkResponse = Common.mapToDataClass<CancelTuningJobResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  internal suspend fun privateTune(
    baseModel: String? = null,
    preTunedModel: PreTunedModel? = null,
    trainingDataset: TuningDataset,
    config: CreateTuningJobConfig? = null,
  ): TuningJob {
    val parameters =
      CreateTuningJobParametersPrivate(baseModel, preTunedModel, trainingDataset, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = createTuningJobParametersPrivateToVertex(parameterMap, null, parameterMap)

      path = Common.formatMap("tuningJobs", body["_url"] as? Map<String, Any?>)
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = tuningJobFromVertex(responseMap, null, parameterMap)
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val sdkResponse = Common.mapToDataClass<TuningJob>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  internal suspend fun privateTuneMldev(
    baseModel: String? = null,
    preTunedModel: PreTunedModel? = null,
    trainingDataset: TuningDataset,
    config: CreateTuningJobConfig? = null,
  ): TuningOperation {
    val parameters =
      CreateTuningJobParametersPrivate(baseModel, preTunedModel, trainingDataset, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {

      body = createTuningJobParametersPrivateToMldev(parameterMap, null, parameterMap)

      path = Common.formatMap("tunedModels", body["_url"] as? Map<String, Any?>)
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {
      responseMap = tuningOperationFromMldev(responseMap, null, parameterMap)
    }

    val sdkResponse = Common.mapToDataClass<TuningOperation>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Validates a reinforcement tuning reward configuration.
   *
   * Allows users to validate a reinforcement tuning reward configuration against a sample response
   * and example before creating a reinforcement tuning job, so that they can iterate on the reward
   * configuration without having to create a tuning job each time.
   *
   * @param parent The resource name of the Location to validate the reward in, e.g.
   *   `projects/{project}/locations/{location}`.
   * @param sampleResponse The sample response for validating the reward configuration.
   * @param example The example to validate the reward configuration.
   * @param singleRewardConfig Single reward function configuration for reinforcement tuning.
   *   Mutually exclusive with [compositeRewardConfig].
   * @param compositeRewardConfig Composite reward function configuration for reinforcement tuning.
   *   Mutually exclusive with [singleRewardConfig].
   * @param config A [ValidateRewardConfig] for configuring the request.
   * @return A [ValidateRewardResponse] with the computed reward(s).
   */
  suspend fun validateReward(
    parent: String,
    sampleResponse: Content,
    example: ReinforcementTuningExample,
    singleRewardConfig: SingleReinforcementTuningRewardConfig? = null,
    compositeRewardConfig: CompositeReinforcementTuningRewardConfig? = null,
    config: ValidateRewardConfig? = null,
  ): ValidateRewardResponse {
    val parameters =
      ValidateRewardParameters(
        parent,
        sampleResponse,
        example,
        singleRewardConfig,
        compositeRewardConfig,
        config,
      )
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = validateRewardParametersToVertex(parameterMap, null, parameterMap)

      path =
        Common.formatMap(
          "{parent}/tuningJobs:validateReinforcementTuningReward",
          body["_url"] as? Map<String, Any?>,
        )
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val queryParams = body["_query"] as? Map<String, Any?>
    val filteredBody = body.filterKeys { it != "_url" && it != "_query" }
    val finalBody = if (filteredBody.isEmpty()) null else Common.mapToJsonObject(filteredBody)

    if (queryParams != null) {
      val queryString =
        queryParams.entries.joinToString("&") {
          "${it.key.encodeURLQueryComponent()}=${it.value.toString().encodeURLQueryComponent()}"
        }
      path = "$path?$queryString"
    }

    val response = apiClient.request("POST", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = validateRewardResponseFromVertex(responseMap, null, parameterMap)
    } else {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Enterprise Agent Platform mode, not in Gemini Developer API mode."
      )
    }

    val sdkResponse = Common.mapToDataClass<ValidateRewardResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Gets a tuning job.
   *
   * @param name The resource name of the tuning job. For Gemini Enterprise Agent Platform, this is
   *   the full resource name. For Gemini API, this is `tunedModels/{id}`.
   * @param config A [GetTuningJobConfig] for configuring the get request.
   * @return A [TuningJob] object.
   */
  suspend fun get(name: String, config: GetTuningJobConfig? = null): TuningJob {
    return privateGet(name = name, config = config)
  }

  /**
   * Creates a fine-tuning job.
   *
   * @param baseModel The base model to tune.
   * @param trainingDataset The training dataset to use for tuning.
   * @param config A [CreateTuningJobConfig] for configuring the create request.
   * @return A [TuningJob] object.
   */
  suspend fun tune(
    baseModel: String,
    trainingDataset: TuningDataset,
    config: CreateTuningJobConfig? = null,
  ): TuningJob {
    if (apiClient.enterprise) {
      val tuningJob: TuningJob
      if (baseModel.startsWith("projects/")) {
        val preTunedModel =
          PreTunedModel(
            tunedModelName = baseModel,
            checkpointId = config?.preTunedModelCheckpointId,
          )
        tuningJob =
          privateTune(
            baseModel = null,
            preTunedModel = preTunedModel,
            trainingDataset = trainingDataset,
            config = config,
          )
      } else {
        tuningJob =
          privateTune(
            baseModel = baseModel,
            preTunedModel = null,
            trainingDataset = trainingDataset,
            config = config,
          )
      }

      return tuningJob
    } else {
      val operation =
        privateTuneMldev(
          baseModel = baseModel,
          preTunedModel = null,
          trainingDataset = trainingDataset,
          config = config,
        )
      val tunedModelName: String
      val metadata = operation.metadata
      if (metadata != null && metadata.containsKey("tunedModel")) {
        tunedModelName = metadata["tunedModel"].toString().removeSurrounding("\"")
      } else {
        val operationName =
          operation.name ?: throw IllegalArgumentException("Operation name is required.")
        tunedModelName = operationName.split("/operations/")[0]
      }
      return TuningJob(name = tunedModelName, state = JobState.JOB_STATE_QUEUED)
    }
  }
}
