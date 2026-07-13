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

import com.google.genai.kotlin.types.BatchJob
import com.google.genai.kotlin.types.BatchJobSource
import com.google.genai.kotlin.types.CancelBatchJobConfig
import com.google.genai.kotlin.types.CancelBatchJobParameters
import com.google.genai.kotlin.types.CreateBatchJobConfig
import com.google.genai.kotlin.types.CreateBatchJobParameters
import com.google.genai.kotlin.types.CreateEmbeddingsBatchJobConfig
import com.google.genai.kotlin.types.CreateEmbeddingsBatchJobParameters
import com.google.genai.kotlin.types.DeleteBatchJobConfig
import com.google.genai.kotlin.types.DeleteBatchJobParameters
import com.google.genai.kotlin.types.DeleteResourceJob
import com.google.genai.kotlin.types.EmbeddingsBatchJobSource
import com.google.genai.kotlin.types.GetBatchJobConfig
import com.google.genai.kotlin.types.GetBatchJobParameters
import com.google.genai.kotlin.types.HttpResponse
import com.google.genai.kotlin.types.ListBatchJobsConfig
import com.google.genai.kotlin.types.ListBatchJobsParameters
import com.google.genai.kotlin.types.ListBatchJobsResponse
import io.ktor.http.encodeURLQueryComponent

class Batches internal constructor(internal val apiClient: ApiClient) {

  internal fun authConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("apiKey"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("apiKey"),
        Common.getValueByPath(fromObject, arrayOf("apiKey")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("apiKeyConfig")))) {
      throw IllegalArgumentException("apiKeyConfig parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("authType")))) {
      throw IllegalArgumentException("authType parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("googleServiceAccountConfig")))) {
      throw IllegalArgumentException(
        "googleServiceAccountConfig parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("httpBasicAuthConfig")))) {
      throw IllegalArgumentException(
        "httpBasicAuthConfig parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("oauthConfig")))) {
      throw IllegalArgumentException("oauthConfig parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("oidcConfig")))) {
      throw IllegalArgumentException("oidcConfig parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun batchJobDestinationFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("responsesFile"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileName"),
        Common.getValueByPath(fromObject, arrayOf("responsesFile")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inlinedResponses", "inlinedResponses"))?.let { node
      ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(inlinedResponseFromMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("inlinedResponses"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("inlinedEmbedContentResponses", "inlinedResponses"))
      ?.let { node ->
        Common.setValueByPath(
          toObject,
          arrayOf("inlinedEmbedContentResponses"),
          Common.getValueByPath(
            fromObject,
            arrayOf("inlinedEmbedContentResponses", "inlinedResponses"),
          ),
        )
      }

    return toObject
  }

  internal fun batchJobDestinationFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("predictionsFormat"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("format"),
        Common.getValueByPath(fromObject, arrayOf("predictionsFormat")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("gcsDestination", "outputUriPrefix"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("gcsUri"),
        Common.getValueByPath(fromObject, arrayOf("gcsDestination", "outputUriPrefix")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("bigqueryDestination", "outputUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("bigqueryUri"),
        Common.getValueByPath(fromObject, arrayOf("bigqueryDestination", "outputUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("vertexMultimodalDatasetDestination"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("vertexDataset"),
        vertexMultimodalDatasetDestinationFromVertex(
          Common.getValueByPath(fromObject, arrayOf("vertexMultimodalDatasetDestination"))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun batchJobDestinationToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("format"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("predictionsFormat"),
        Common.getValueByPath(fromObject, arrayOf("format")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("gcsDestination", "outputUriPrefix"),
        Common.getValueByPath(fromObject, arrayOf("gcsUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("bigqueryUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("bigqueryDestination", "outputUri"),
        Common.getValueByPath(fromObject, arrayOf("bigqueryUri")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("fileName")))) {
      throw IllegalArgumentException(
        "fileName parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("inlinedResponses")))) {
      throw IllegalArgumentException(
        "inlinedResponses parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (
      !Common.isZero(Common.getValueByPath(fromObject, arrayOf("inlinedEmbedContentResponses")))
    ) {
      throw IllegalArgumentException(
        "inlinedEmbedContentResponses parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("vertexDataset"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("vertexMultimodalDatasetDestination"),
        vertexMultimodalDatasetDestinationToVertex(
          Common.getValueByPath(fromObject, arrayOf("vertexDataset")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun batchJobFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata", "displayName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("metadata", "displayName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata", "state"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("state"),
        Transformers.tJobState(Common.getValueByPath(fromObject, arrayOf("metadata", "state"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata", "createTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("createTime"),
        Common.getValueByPath(fromObject, arrayOf("metadata", "createTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata", "endTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("endTime"),
        Common.getValueByPath(fromObject, arrayOf("metadata", "endTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata", "updateTime"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("updateTime"),
        Common.getValueByPath(fromObject, arrayOf("metadata", "updateTime")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata", "model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Common.getValueByPath(fromObject, arrayOf("metadata", "model")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata", "output"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("dest"),
        batchJobDestinationFromMldev(
          Transformers.tRecvBatchJobDestination(
            Common.getValueByPath(fromObject, arrayOf("metadata", "output"))
          ) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun batchJobFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("state"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("state"),
        Transformers.tJobState(Common.getValueByPath(fromObject, arrayOf("state"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("error"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("error"),
        Common.getValueByPath(fromObject, arrayOf("error")),
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

    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Common.getValueByPath(fromObject, arrayOf("model")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inputConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("src"),
        batchJobSourceFromVertex(
          Common.getValueByPath(fromObject, arrayOf("inputConfig")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("dest"),
        batchJobDestinationFromVertex(
          Transformers.tRecvBatchJobDestination(
            Common.getValueByPath(fromObject, arrayOf("outputConfig"))
          ) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("completionStats"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("completionStats"),
        Common.getValueByPath(fromObject, arrayOf("completionStats")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputInfo"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("outputInfo"),
        Common.getValueByPath(fromObject, arrayOf("outputInfo")),
      )
    }

    return toObject
  }

  internal fun batchJobSourceFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("instancesFormat"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("format"),
        Common.getValueByPath(fromObject, arrayOf("instancesFormat")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("gcsSource", "uris"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("gcsUri"),
        Common.getValueByPath(fromObject, arrayOf("gcsSource", "uris")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("bigquerySource", "inputUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("bigqueryUri"),
        Common.getValueByPath(fromObject, arrayOf("bigquerySource", "inputUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("vertexMultimodalDatasetSource", "datasetName"))
      ?.let { node ->
        Common.setValueByPath(
          toObject,
          arrayOf("vertexDatasetName"),
          Common.getValueByPath(fromObject, arrayOf("vertexMultimodalDatasetSource", "datasetName")),
        )
      }

    return toObject
  }

  internal fun batchJobSourceToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("format")))) {
      throw IllegalArgumentException("format parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("gcsUri")))) {
      throw IllegalArgumentException("gcsUri parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("bigqueryUri")))) {
      throw IllegalArgumentException("bigqueryUri parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("fileName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileName"),
        Common.getValueByPath(fromObject, arrayOf("fileName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inlinedRequests"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(inlinedRequestToMldev(apiClient, item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("requests", "requests"), result)
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("vertexDatasetName")))) {
      throw IllegalArgumentException("vertexDatasetName parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun batchJobSourceToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("format"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("instancesFormat"),
        Common.getValueByPath(fromObject, arrayOf("format")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("gcsUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("gcsSource", "uris"),
        Common.getValueByPath(fromObject, arrayOf("gcsUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("bigqueryUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("bigquerySource", "inputUri"),
        Common.getValueByPath(fromObject, arrayOf("bigqueryUri")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("fileName")))) {
      throw IllegalArgumentException(
        "fileName parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("inlinedRequests")))) {
      throw IllegalArgumentException(
        "inlinedRequests parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("vertexDatasetName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("vertexMultimodalDatasetSource", "datasetName"),
        Common.getValueByPath(fromObject, arrayOf("vertexDatasetName")),
      )
    }

    return toObject
  }

  internal fun blobToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("data"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("data"),
        Common.getValueByPath(fromObject, arrayOf("data")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("displayName")))) {
      throw IllegalArgumentException("displayName parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("mimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mimeType"),
        Common.getValueByPath(fromObject, arrayOf("mimeType")),
      )
    }

    return toObject
  }

  internal fun cancelBatchJobParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tBatchJobName(apiClient, Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun cancelBatchJobParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tBatchJobName(apiClient, Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun candidateFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("content"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("content"),
        Common.getValueByPath(fromObject, arrayOf("content")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("citationMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("citationMetadata"),
        citationMetadataFromMldev(
          Common.getValueByPath(fromObject, arrayOf("citationMetadata")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("tokenCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("tokenCount"),
        Common.getValueByPath(fromObject, arrayOf("tokenCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("finishReason"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("finishReason"),
        Common.getValueByPath(fromObject, arrayOf("finishReason")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("groundingMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("groundingMetadata"),
        Common.getValueByPath(fromObject, arrayOf("groundingMetadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("avgLogprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("avgLogprobs"),
        Common.getValueByPath(fromObject, arrayOf("avgLogprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("index"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("index"),
        Common.getValueByPath(fromObject, arrayOf("index")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("logprobsResult"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("logprobsResult"),
        Common.getValueByPath(fromObject, arrayOf("logprobsResult")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetyRatings"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("safetyRatings"),
        Common.getValueByPath(fromObject, arrayOf("safetyRatings")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("urlContextMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("urlContextMetadata"),
        Common.getValueByPath(fromObject, arrayOf("urlContextMetadata")),
      )
    }

    return toObject
  }

  internal fun citationMetadataFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("citationSources"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("citations"),
        Common.getValueByPath(fromObject, arrayOf("citationSources")),
      )
    }

    return toObject
  }

  internal fun contentToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("parts"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(partToMldev(item as Map<String, Any?>, toObject))
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

  internal fun createBatchJobConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("batch", "displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("dest")))) {
      throw IllegalArgumentException("dest parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("webhookConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("batch", "webhookConfig"),
        Common.getValueByPath(fromObject, arrayOf("webhookConfig")),
      )
    }

    return toObject
  }

  internal fun createBatchJobConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("dest"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("outputConfig"),
        batchJobDestinationToVertex(
          Transformers.tBatchJobDestination(Common.getValueByPath(fromObject, arrayOf("dest")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("webhookConfig")))) {
      throw IllegalArgumentException(
        "webhookConfig parameter is not supported in Gemini Enterprise Agent Platform."
      )
    }

    return toObject
  }

  internal fun createBatchJobParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("src"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("batch", "inputConfig"),
        batchJobSourceToMldev(
          apiClient,
          Transformers.tBatchJobSource(Common.getValueByPath(fromObject, arrayOf("src")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        createBatchJobConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun createBatchJobParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("src"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("inputConfig"),
        batchJobSourceToVertex(
          Transformers.tBatchJobSource(Common.getValueByPath(fromObject, arrayOf("src")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        createBatchJobConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun createEmbeddingsBatchJobConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("batch", "displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    return toObject
  }

  internal fun createEmbeddingsBatchJobParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("src"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("batch", "inputConfig"),
        embeddingsBatchJobSourceToMldev(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("src")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        createEmbeddingsBatchJobConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun deleteBatchJobParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tBatchJobName(apiClient, Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun deleteBatchJobParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tBatchJobName(apiClient, Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun deleteResourceJobFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun deleteResourceJobFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun embedContentBatchToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("requests[]", "request", "content"),
        Transformers.tContentsForEmbed(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("contents")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_self"),
        embedContentConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        ),
      )
      Common.moveValueByPath(toObject, mapOf("requests[].*" to "requests[].request.*"))
    }

    return toObject
  }

  internal fun embedContentConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("taskType"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("requests[]", "taskType"),
        Common.getValueByPath(fromObject, arrayOf("taskType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("title"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("requests[]", "title"),
        Common.getValueByPath(fromObject, arrayOf("title")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("outputDimensionality"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("requests[]", "outputDimensionality"),
        Common.getValueByPath(fromObject, arrayOf("outputDimensionality")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("mimeType")))) {
      throw IllegalArgumentException("mimeType parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("autoTruncate")))) {
      throw IllegalArgumentException("autoTruncate parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("documentOcr")))) {
      throw IllegalArgumentException("documentOcr parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("audioTrackExtraction")))) {
      throw IllegalArgumentException(
        "audioTrackExtraction parameter is not supported in Gemini API."
      )
    }

    return toObject
  }

  internal fun embeddingsBatchJobSourceToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("fileName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("file_name"),
        Common.getValueByPath(fromObject, arrayOf("fileName")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("inlinedRequests"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("requests"),
        embedContentBatchToMldev(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("inlinedRequests")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun fileDataToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("displayName")))) {
      throw IllegalArgumentException("displayName parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("fileUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileUri"),
        Common.getValueByPath(fromObject, arrayOf("fileUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mimeType"),
        Common.getValueByPath(fromObject, arrayOf("mimeType")),
      )
    }

    return toObject
  }

  internal fun functionCallToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("id"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("id"),
        Common.getValueByPath(fromObject, arrayOf("id")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("args"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("args"),
        Common.getValueByPath(fromObject, arrayOf("args")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("name"),
        Common.getValueByPath(fromObject, arrayOf("name")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("partialArgs")))) {
      throw IllegalArgumentException("partialArgs parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("willContinue")))) {
      throw IllegalArgumentException("willContinue parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun functionCallingConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("allowedFunctionNames"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("allowedFunctionNames"),
        Common.getValueByPath(fromObject, arrayOf("allowedFunctionNames")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mode"),
        Common.getValueByPath(fromObject, arrayOf("mode")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("streamFunctionCallArguments")))) {
      throw IllegalArgumentException(
        "streamFunctionCallArguments parameter is not supported in Gemini API."
      )
    }

    return toObject
  }

  internal fun generateContentConfigToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()

    Common.getValueByPath(fromObject, arrayOf("systemInstruction"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("systemInstruction"),
        contentToMldev(
          Transformers.tContent(Common.getValueByPath(fromObject, arrayOf("systemInstruction")))
            as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("temperature"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("temperature"),
        Common.getValueByPath(fromObject, arrayOf("temperature")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topP"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topP"),
        Common.getValueByPath(fromObject, arrayOf("topP")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("topK"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("topK"),
        Common.getValueByPath(fromObject, arrayOf("topK")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidateCount"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("candidateCount"),
        Common.getValueByPath(fromObject, arrayOf("candidateCount")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("maxOutputTokens"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("maxOutputTokens"),
        Common.getValueByPath(fromObject, arrayOf("maxOutputTokens")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("stopSequences"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("stopSequences"),
        Common.getValueByPath(fromObject, arrayOf("stopSequences")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseLogprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseLogprobs"),
        Common.getValueByPath(fromObject, arrayOf("responseLogprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("logprobs"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("logprobs"),
        Common.getValueByPath(fromObject, arrayOf("logprobs")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("presencePenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("presencePenalty"),
        Common.getValueByPath(fromObject, arrayOf("presencePenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("frequencyPenalty"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("frequencyPenalty"),
        Common.getValueByPath(fromObject, arrayOf("frequencyPenalty")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("seed"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("seed"),
        Common.getValueByPath(fromObject, arrayOf("seed")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseMimeType"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseMimeType"),
        Common.getValueByPath(fromObject, arrayOf("responseMimeType")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseSchema"),
        Transformers.tSchema(Common.getValueByPath(fromObject, arrayOf("responseSchema"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseJsonSchema"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseJsonSchema"),
        Common.getValueByPath(fromObject, arrayOf("responseJsonSchema")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("modelSelectionConfig")))) {
      throw IllegalArgumentException(
        "modelSelectionConfig parameter is not supported in Gemini API."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("safetySettings"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(safetySettingToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("safetySettings"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("tools"))?.let { node ->
      val keyArray = Transformers.tTools(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(toolToMldev(Transformers.tTool(item) as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(parentObject, arrayOf("tools"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("toolConfig"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("toolConfig"),
        toolConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("toolConfig")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("cachedContent"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("cachedContent"),
        Transformers.tCachedContentName(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("cachedContent")),
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseModalities"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseModalities"),
        Common.getValueByPath(fromObject, arrayOf("responseModalities")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("speechConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("speechConfig"),
        Transformers.tSpeechConfig(Common.getValueByPath(fromObject, arrayOf("speechConfig"))),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("audioTimestamp")))) {
      throw IllegalArgumentException("audioTimestamp parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("thinkingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("thinkingConfig"),
        Common.getValueByPath(fromObject, arrayOf("thinkingConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("imageConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageConfig"),
        imageConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("imageConfig")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableEnhancedCivicAnswers"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enableEnhancedCivicAnswers"),
        Common.getValueByPath(fromObject, arrayOf("enableEnhancedCivicAnswers")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("modelArmorConfig")))) {
      throw IllegalArgumentException("modelArmorConfig parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("serviceTier"))?.let { node ->
      Common.setValueByPath(
        parentObject,
        arrayOf("serviceTier"),
        Common.getValueByPath(fromObject, arrayOf("serviceTier")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("routingConfig")))) {
      throw IllegalArgumentException("routingConfig parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("labels")))) {
      throw IllegalArgumentException("labels parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun generateContentResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("sdkHttpResponse"),
        Common.getValueByPath(fromObject, arrayOf("sdkHttpResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("candidates"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(candidateFromMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("candidates"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("modelVersion"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelVersion"),
        Common.getValueByPath(fromObject, arrayOf("modelVersion")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("promptFeedback"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("promptFeedback"),
        Common.getValueByPath(fromObject, arrayOf("promptFeedback")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("responseId"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("responseId"),
        Common.getValueByPath(fromObject, arrayOf("responseId")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("usageMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("usageMetadata"),
        Common.getValueByPath(fromObject, arrayOf("usageMetadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("modelStatus"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("modelStatus"),
        Common.getValueByPath(fromObject, arrayOf("modelStatus")),
      )
    }

    return toObject
  }

  internal fun getBatchJobParametersToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tBatchJobName(apiClient, Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun getBatchJobParametersToVertex(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "name"),
        Transformers.tBatchJobName(apiClient, Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun googleMapsToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("authConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("authConfig"),
        authConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("authConfig")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("enableWidget"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("enableWidget"),
        Common.getValueByPath(fromObject, arrayOf("enableWidget")),
      )
    }

    return toObject
  }

  internal fun googleSearchToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("searchTypes"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("searchTypes"),
        Common.getValueByPath(fromObject, arrayOf("searchTypes")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("blockingConfidence")))) {
      throw IllegalArgumentException("blockingConfidence parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("excludeDomains")))) {
      throw IllegalArgumentException("excludeDomains parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("timeRangeFilter"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("timeRangeFilter"),
        Common.getValueByPath(fromObject, arrayOf("timeRangeFilter")),
      )
    }

    return toObject
  }

  internal fun imageConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("aspectRatio"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("aspectRatio"),
        Common.getValueByPath(fromObject, arrayOf("aspectRatio")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("imageSize"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("imageSize"),
        Common.getValueByPath(fromObject, arrayOf("imageSize")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("personGeneration")))) {
      throw IllegalArgumentException("personGeneration parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("prominentPeople")))) {
      throw IllegalArgumentException("prominentPeople parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("outputMimeType")))) {
      throw IllegalArgumentException("outputMimeType parameter is not supported in Gemini API.")
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("outputCompressionQuality")))) {
      throw IllegalArgumentException(
        "outputCompressionQuality parameter is not supported in Gemini API."
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("imageOutputOptions")))) {
      throw IllegalArgumentException("imageOutputOptions parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun inlinedRequestToMldev(
    apiClient: ApiClient,
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("model"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("request", "model"),
        Transformers.tModel(apiClient, Common.getValueByPath(fromObject, arrayOf("model"))),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("contents"))?.let { node ->
      val keyArray = Transformers.tContents(node) as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(contentToMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("request", "contents"), result)
    }

    Common.getValueByPath(fromObject, arrayOf("metadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("metadata"),
        Common.getValueByPath(fromObject, arrayOf("metadata")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("request", "generationConfig"),
        generateContentConfigToMldev(
          apiClient,
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    return toObject
  }

  internal fun inlinedResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("response"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("response"),
        generateContentResponseFromMldev(
          Common.getValueByPath(fromObject, arrayOf("response")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("metadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("metadata"),
        Common.getValueByPath(fromObject, arrayOf("metadata")),
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

  internal fun listBatchJobsConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("filter")))) {
      throw IllegalArgumentException("filter parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun listBatchJobsConfigToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

  internal fun listBatchJobsParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        listBatchJobsConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun listBatchJobsParametersToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        listBatchJobsConfigToVertex(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun listBatchJobsResponseFromMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

    Common.getValueByPath(fromObject, arrayOf("operations"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(batchJobFromMldev(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("batchJobs"), result)
    }

    return toObject
  }

  internal fun listBatchJobsResponseFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
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

    Common.getValueByPath(fromObject, arrayOf("batchPredictionJobs"))?.let { node ->
      val keyArray = node as? List<*> ?: emptyList<Any?>()
      val result = mutableListOf<Any?>()

      for (item in keyArray) {
        if (item is Map<*, *>) {
          result.add(batchJobFromVertex(item as Map<String, Any?>, toObject))
        }
      }
      Common.setValueByPath(toObject, arrayOf("batchJobs"), result)
    }

    return toObject
  }

  internal fun partToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("mediaResolution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mediaResolution"),
        Common.getValueByPath(fromObject, arrayOf("mediaResolution")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("codeExecutionResult"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("codeExecutionResult"),
        Common.getValueByPath(fromObject, arrayOf("codeExecutionResult")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("executableCode"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("executableCode"),
        Common.getValueByPath(fromObject, arrayOf("executableCode")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("fileData"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileData"),
        fileDataToMldev(
          Common.getValueByPath(fromObject, arrayOf("fileData")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionCall"),
        functionCallToMldev(
          Common.getValueByPath(fromObject, arrayOf("functionCall")) as Map<String, Any?>,
          toObject,
        ),
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
        blobToMldev(
          Common.getValueByPath(fromObject, arrayOf("inlineData")) as Map<String, Any?>,
          toObject,
        ),
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

    Common.getValueByPath(fromObject, arrayOf("toolCall"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolCall"),
        Common.getValueByPath(fromObject, arrayOf("toolCall")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("toolResponse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("toolResponse"),
        Common.getValueByPath(fromObject, arrayOf("toolResponse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("partMetadata"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("partMetadata"),
        Common.getValueByPath(fromObject, arrayOf("partMetadata")),
      )
    }

    return toObject
  }

  internal fun safetySettingToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("category"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("category"),
        Common.getValueByPath(fromObject, arrayOf("category")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("method")))) {
      throw IllegalArgumentException("method parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("threshold"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("threshold"),
        Common.getValueByPath(fromObject, arrayOf("threshold")),
      )
    }

    return toObject
  }

  internal fun toolConfigToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("retrievalConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("retrievalConfig"),
        Common.getValueByPath(fromObject, arrayOf("retrievalConfig")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionCallingConfig"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionCallingConfig"),
        functionCallingConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("functionCallingConfig")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("includeServerSideToolInvocations"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("includeServerSideToolInvocations"),
        Common.getValueByPath(fromObject, arrayOf("includeServerSideToolInvocations")),
      )
    }

    return toObject
  }

  internal fun toolToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("retrieval")))) {
      throw IllegalArgumentException("retrieval parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("computerUse"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("computerUse"),
        Common.getValueByPath(fromObject, arrayOf("computerUse")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("fileSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("fileSearch"),
        Common.getValueByPath(fromObject, arrayOf("fileSearch")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleSearch"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleSearch"),
        googleSearchToMldev(
          Common.getValueByPath(fromObject, arrayOf("googleSearch")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleMaps"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleMaps"),
        googleMapsToMldev(
          Common.getValueByPath(fromObject, arrayOf("googleMaps")) as Map<String, Any?>,
          toObject,
        ),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("codeExecution"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("codeExecution"),
        Common.getValueByPath(fromObject, arrayOf("codeExecution")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("enterpriseWebSearch")))) {
      throw IllegalArgumentException(
        "enterpriseWebSearch parameter is not supported in Gemini API."
      )
    }

    Common.getValueByPath(fromObject, arrayOf("functionDeclarations"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("functionDeclarations"),
        Common.getValueByPath(fromObject, arrayOf("functionDeclarations")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("googleSearchRetrieval"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("googleSearchRetrieval"),
        Common.getValueByPath(fromObject, arrayOf("googleSearchRetrieval")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("parallelAiSearch")))) {
      throw IllegalArgumentException("parallelAiSearch parameter is not supported in Gemini API.")
    }

    Common.getValueByPath(fromObject, arrayOf("urlContext"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("urlContext"),
        Common.getValueByPath(fromObject, arrayOf("urlContext")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("mcpServers"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("mcpServers"),
        Common.getValueByPath(fromObject, arrayOf("mcpServers")),
      )
    }

    if (!Common.isZero(Common.getValueByPath(fromObject, arrayOf("exaAiSearch")))) {
      throw IllegalArgumentException("exaAiSearch parameter is not supported in Gemini API.")
    }

    return toObject
  }

  internal fun vertexMultimodalDatasetDestinationFromVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("bigqueryDestination", "outputUri"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("bigqueryDestination"),
        Common.getValueByPath(fromObject, arrayOf("bigqueryDestination", "outputUri")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    return toObject
  }

  internal fun vertexMultimodalDatasetDestinationToVertex(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("bigqueryDestination"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("bigqueryDestination", "outputUri"),
        Common.getValueByPath(fromObject, arrayOf("bigqueryDestination")),
      )
    }

    Common.getValueByPath(fromObject, arrayOf("displayName"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("displayName"),
        Common.getValueByPath(fromObject, arrayOf("displayName")),
      )
    }

    return toObject
  }

  internal suspend fun privateCreate(
    model: String? = null,
    src: BatchJobSource,
    config: CreateBatchJobConfig? = null,
  ): BatchJob {
    val parameters = CreateBatchJobParameters(model, src, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = createBatchJobParametersToVertex(this.apiClient, parameterMap, null)

      path = Common.formatMap("batchPredictionJobs", body["_url"] as? Map<String, Any?>)
    } else {

      body = createBatchJobParametersToMldev(this.apiClient, parameterMap, null)

      path = Common.formatMap("{model}:batchGenerateContent", body["_url"] as? Map<String, Any?>)
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
      responseMap = batchJobFromVertex(responseMap, null)
    } else {
      responseMap = batchJobFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<BatchJob>(responseMap)
    return sdkResponse
  }

  internal suspend fun privateCreateEmbeddings(
    model: String? = null,
    src: EmbeddingsBatchJobSource,
    config: CreateEmbeddingsBatchJobConfig? = null,
  ): BatchJob {
    val parameters = CreateEmbeddingsBatchJobParameters(model, src, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {

      body = createEmbeddingsBatchJobParametersToMldev(this.apiClient, parameterMap, null)

      path = Common.formatMap("{model}:asyncBatchEmbedContent", body["_url"] as? Map<String, Any?>)
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
    if (!apiClient.enterprise) {
      responseMap = batchJobFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<BatchJob>(responseMap)
    return sdkResponse
  }

  /**
   * Gets a batch job.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *   "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *   initialized in the Gemini Enterprise Agent Platform client. Or "batches/abc" using the Gemini
   *   Developer API client.
   * @param config A [GetBatchJobConfig] for configuring the get request.
   * @return A [BatchJob] object that contains the info of the batch job.
   */
  suspend fun get(name: String, config: GetBatchJobConfig? = null): BatchJob {
    val parameters = GetBatchJobParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = getBatchJobParametersToVertex(this.apiClient, parameterMap, null)

      path = Common.formatMap("batchPredictionJobs/{name}", body["_url"] as? Map<String, Any?>)
    } else {

      body = getBatchJobParametersToMldev(this.apiClient, parameterMap, null)

      path = Common.formatMap("batches/{name}", body["_url"] as? Map<String, Any?>)
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
      responseMap = batchJobFromVertex(responseMap, null)
    } else {
      responseMap = batchJobFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<BatchJob>(responseMap)
    return sdkResponse
  }

  /**
   * Cancels a batch job. Only available for batch jobs that are running or pending.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *   "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *   initialized in the Gemini Enterprise Agent Platform client. Or "batches/abc" using the Gemini
   *   Developer API client.
   * @param config A [CancelBatchJobConfig] for configuring the cancel request.
   */
  suspend fun cancel(name: String, config: CancelBatchJobConfig? = null): Unit {
    val parameters = CancelBatchJobParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = cancelBatchJobParametersToVertex(this.apiClient, parameterMap, null)

      path =
        Common.formatMap("batchPredictionJobs/{name}:cancel", body["_url"] as? Map<String, Any?>)
    } else {

      body = cancelBatchJobParametersToMldev(this.apiClient, parameterMap, null)

      path = Common.formatMap("batches/{name}:cancel", body["_url"] as? Map<String, Any?>)
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
  }

  internal suspend fun privateList(config: ListBatchJobsConfig? = null): ListBatchJobsResponse {
    val parameters = ListBatchJobsParameters(config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = listBatchJobsParametersToVertex(parameterMap, null)

      path = Common.formatMap("batchPredictionJobs", body["_url"] as? Map<String, Any?>)
    } else {

      body = listBatchJobsParametersToMldev(parameterMap, null)

      path = Common.formatMap("batches", body["_url"] as? Map<String, Any?>)
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
      responseMap = listBatchJobsResponseFromVertex(responseMap, null)
    } else {
      responseMap = listBatchJobsResponseFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<ListBatchJobsResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Makes an API request to list batchJobs.
   *
   * @param config A [ListBatchJobsConfig] for configuring the list request.
   * @return A [Pager] of [BatchJob] objects.
   */
  fun list(config: ListBatchJobsConfig? = null): Pager<BatchJob> {
    val initialConfig = config ?: ListBatchJobsConfig()
    return Pager<BatchJob>(
      name = "batch_jobs",
      pageSize = initialConfig.pageSize,
      request = { pageToken ->
        val cfg =
          if (pageToken != null) initialConfig.copy(pageToken = pageToken) else initialConfig
        val resp = privateList(config = cfg)
        Triple(resp.batchJobs, resp.nextPageToken, resp.sdkHttpResponse)
      },
    )
  }

  /**
   * Deletes a batch job.
   *
   * @param name A fully-qualified BatchJob resource name or ID. Example:
   *   "projects/.../locations/.../batchPredictionJobs/456" or "456" when project and location are
   *   initialized in the Gemini Enterprise Agent Platform client. Or "batches/abc" using the Gemini
   *   Developer API client.
   * @param config A [DeleteBatchJobConfig] for configuring the delete request.
   * @return A [DeleteResourceJob] object that shows the status of the deletion.
   */
  suspend fun delete(name: String, config: DeleteBatchJobConfig? = null): DeleteResourceJob {
    val parameters = DeleteBatchJobParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {

      body = deleteBatchJobParametersToVertex(this.apiClient, parameterMap, null)

      path = Common.formatMap("batchPredictionJobs/{name}", body["_url"] as? Map<String, Any?>)
    } else {

      body = deleteBatchJobParametersToMldev(this.apiClient, parameterMap, null)

      path = Common.formatMap("batches/{name}", body["_url"] as? Map<String, Any?>)
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

    val response = apiClient.request("DELETE", path, finalBody, httpOptions = config?.httpOptions)

    val responseString = response.body()
    val headersMap = response.headers.entries().associate { it.key to it.value.joinToString(",") }

    var responseMap = Common.jsonStringToMap(responseString)
    if (apiClient.enterprise) {
      responseMap = deleteResourceJobFromVertex(responseMap, null)
    } else {
      responseMap = deleteResourceJobFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<DeleteResourceJob>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Makes an API request to create the batch job.
   *
   * @param model the name of the GenAI model to use for generation
   * @param src The [BatchJobSource] of the batch job.
   * @param config The configuration [CreateBatchJobConfig] for the batch job.
   * @return A BatchJob.
   */
  suspend fun create(
    model: String,
    src: BatchJobSource,
    config: CreateBatchJobConfig? = null,
  ): BatchJob {
    if (apiClient.enterprise) {
      if (src.inlinedRequests != null) {
        throw IllegalArgumentException(
          "inlinedRequests is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
        )
      }
      if (src.fileName != null) {
        throw IllegalArgumentException(
          "fileName is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
        )
      }
      var count = 0
      if (src.gcsUri != null) count++
      if (src.bigqueryUri != null) count++
      if (src.vertexDatasetName != null) count++
      if (count > 1) {
        throw IllegalArgumentException(
          "Only one of gcsUri, bigqueryUri, and vertexDatasetName can be set."
        )
      }
      if (count == 0) {
        throw IllegalArgumentException(
          "One of gcsUri, bigqueryUri, or vertexDatasetName must be set."
        )
      }
    } else {
      if (src.fileName != null && src.inlinedRequests != null) {
        throw IllegalArgumentException("Only one of fileName and InlinedRequests can be set.")
      }
      if (src.fileName == null && src.inlinedRequests == null) {
        throw IllegalArgumentException("One of fileName and InlinedRequests must be set.")
      }
    }
    return privateCreate(model = model, src = src, config = config)
  }

  /**
   * Makes an API request to create the batch embeddings job.
   *
   * @param model the name of the GenAI model to use for generation
   * @param src The [EmbeddingsBatchJobSource] of the batch job.
   * @param config The configuration [CreateEmbeddingsBatchJobConfig] for the batch job.
   * @return A BatchJob.
   */
  suspend fun createEmbeddings(
    model: String,
    src: EmbeddingsBatchJobSource,
    config: CreateEmbeddingsBatchJobConfig? = null,
  ): BatchJob {
    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "Gemini Enterprise Agent Platform (previously known as Vertex AI) does not support batches.createEmbeddings."
      )
    }
    return privateCreateEmbeddings(model = model, src = src, config = config)
  }
}
