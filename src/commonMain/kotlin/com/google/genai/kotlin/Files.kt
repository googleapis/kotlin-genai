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

import com.google.genai.kotlin.types.CreateFileConfig
import com.google.genai.kotlin.types.CreateFileParameters
import com.google.genai.kotlin.types.CreateFileResponse
import com.google.genai.kotlin.types.DeleteFileConfig
import com.google.genai.kotlin.types.DeleteFileParameters
import com.google.genai.kotlin.types.DeleteFileResponse
import com.google.genai.kotlin.types.DownloadFileConfig
import com.google.genai.kotlin.types.File
import com.google.genai.kotlin.types.GetFileConfig
import com.google.genai.kotlin.types.GetFileParameters
import com.google.genai.kotlin.types.HttpOptions
import com.google.genai.kotlin.types.HttpResponse
import com.google.genai.kotlin.types.InternalRegisterFilesParameters
import com.google.genai.kotlin.types.ListFilesConfig
import com.google.genai.kotlin.types.ListFilesParameters
import com.google.genai.kotlin.types.ListFilesResponse
import com.google.genai.kotlin.types.RegisterFilesConfig
import com.google.genai.kotlin.types.RegisterFilesResponse
import com.google.genai.kotlin.types.UploadFileConfig
import io.ktor.http.encodeURLQueryComponent
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readAvailable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class Files internal constructor(internal val apiClient: ApiClient) {

  internal fun createFileParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("file"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("file"),
        Common.getValueByPath(fromObject, arrayOf("file")),
      )
    }

    return toObject
  }

  internal fun createFileResponseFromMldev(
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

    return toObject
  }

  internal fun deleteFileParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "file"),
        Transformers.tFileName(Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun deleteFileResponseFromMldev(
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

    return toObject
  }

  internal fun getFileParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("name"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("_url", "file"),
        Transformers.tFileName(Common.getValueByPath(fromObject, arrayOf("name"))),
      )
    }

    return toObject
  }

  internal fun internalRegisterFilesParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("uris"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("uris"),
        Common.getValueByPath(fromObject, arrayOf("uris")),
      )
    }

    return toObject
  }

  internal fun listFilesConfigToMldev(
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

    return toObject
  }

  internal fun listFilesParametersToMldev(
    fromObject: Map<String, Any?>?,
    parentObject: MutableMap<String, Any?>?,
  ): MutableMap<String, Any?> {

    val toObject = mutableMapOf<String, Any?>()
    Common.getValueByPath(fromObject, arrayOf("config"))?.let { node ->
      val unused =
        listFilesConfigToMldev(
          Common.getValueByPath(fromObject, arrayOf("config")) as Map<String, Any?>,
          toObject,
        )
    }

    return toObject
  }

  internal fun listFilesResponseFromMldev(
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

    Common.getValueByPath(fromObject, arrayOf("files"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("files"),
        Common.getValueByPath(fromObject, arrayOf("files")),
      )
    }

    return toObject
  }

  internal fun registerFilesResponseFromMldev(
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

    Common.getValueByPath(fromObject, arrayOf("files"))?.let { node ->
      Common.setValueByPath(
        toObject,
        arrayOf("files"),
        Common.getValueByPath(fromObject, arrayOf("files")),
      )
    }

    return toObject
  }

  internal suspend fun privateList(config: ListFilesConfig? = null): ListFilesResponse {
    val parameters = ListFilesParameters(config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {

      body = listFilesParametersToMldev(parameterMap, null)

      path = Common.formatMap("files", body["_url"] as? Map<String, Any?>)
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
    if (!apiClient.enterprise) {
      responseMap = listFilesResponseFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<ListFilesResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  /**
   * Makes an API request to list files.
   *
   * @param config A [ListFilesConfig] for configuring the list request.
   * @return A [Pager] of [File] objects.
   */
  fun list(config: ListFilesConfig? = null): Pager<File> {
    val initialConfig = config ?: ListFilesConfig()
    return Pager<File>(
      name = "files",
      pageSize = initialConfig.pageSize,
      request = { pageToken ->
        val cfg =
          if (pageToken != null) initialConfig.copy(pageToken = pageToken) else initialConfig
        val resp = privateList(config = cfg)
        Triple(resp.files, resp.nextPageToken, resp.sdkHttpResponse)
      },
    )
  }

  internal suspend fun privateCreate(
    file: File,
    config: CreateFileConfig? = null,
  ): CreateFileResponse {
    val parameters = CreateFileParameters(file, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {

      body = createFileParametersToMldev(parameterMap, null)

      path = Common.formatMap("upload/v1beta/files", body["_url"] as? Map<String, Any?>)
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

    if (config?.shouldReturnHttpResponse == true) {
      return CreateFileResponse(
        sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
      )
    }

    var responseMap = Common.jsonStringToMap(responseString)
    if (!apiClient.enterprise) {
      responseMap = createFileResponseFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<CreateFileResponse>(responseMap)
    return sdkResponse
  }

  /**
   * Retrieves the file information from the service.
   *
   * @param name The name identifier for the file to retrieve.
   * @param config Optional configuration for the get method.
   * @return A File object representing the file.
   */
  suspend fun get(name: String, config: GetFileConfig? = null): File {
    val parameters = GetFileParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {

      body = getFileParametersToMldev(parameterMap, null)

      path = Common.formatMap("files/{file}", body["_url"] as? Map<String, Any?>)
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

    val sdkResponse = Common.mapToDataClass<File>(responseMap)
    return sdkResponse
  }

  /**
   * Deletes a remotely stored file.
   *
   * @param name The name identifier for the file to delete.
   * @param config Optional configuration for the delete method.
   * @return The DeleteFileResponse, the response for the delete method.
   */
  suspend fun delete(name: String, config: DeleteFileConfig? = null): DeleteFileResponse {
    val parameters = DeleteFileParameters(name, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {

      body = deleteFileParametersToMldev(parameterMap, null)

      path = Common.formatMap("files/{file}", body["_url"] as? Map<String, Any?>)
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
    if (!apiClient.enterprise) {
      responseMap = deleteFileResponseFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<DeleteFileResponse>(responseMap)

    return sdkResponse.copy(
      sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
    )
  }

  internal suspend fun privateRegisterFiles(
    uris: List<String>,
    config: RegisterFilesConfig? = null,
  ): RegisterFilesResponse {
    val parameters = InternalRegisterFilesParameters(uris, config)
    val parameterMap = Common.dataClassToMap(parameters)

    var body: MutableMap<String, Any?>
    var path: String

    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    } else {

      body = internalRegisterFilesParametersToMldev(parameterMap, null)

      path = Common.formatMap("files:register", body["_url"] as? Map<String, Any?>)
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

    if (config?.shouldReturnHttpResponse == true) {
      return RegisterFilesResponse(
        sdkHttpResponse = HttpResponse(body = responseString, headers = headersMap)
      )
    }

    var responseMap = Common.jsonStringToMap(responseString)
    if (!apiClient.enterprise) {
      responseMap = registerFilesResponseFromMldev(responseMap, null)
    }

    val sdkResponse = Common.mapToDataClass<RegisterFilesResponse>(responseMap)
    return sdkResponse
  }

  /**
   * Uploads a file to the API.
   *
   * @param byteArray The byte array of the file to upload.
   * @param config Optional configuration for the upload.
   * @return The uploaded file.
   */
  suspend fun upload(byteArray: ByteArray, config: UploadFileConfig? = null): File {
    val channel = ByteReadChannel(byteArray)
    return upload(channel, byteArray.size.toLong(), config)
  }

  /**
   * Uploads a file to the API.
   *
   * @param channel The ByteReadChannel of the file to upload.
   * @param size The size of the file to upload.
   * @param config Optional configuration for the upload.
   * @return The uploaded file.
   */
  suspend fun upload(channel: ByteReadChannel, size: Long, config: UploadFileConfig? = null): File {
    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    }
    try {
      val mimeType = config?.mimeType
      val fileName = config?.displayName
      val uploadUrl = createFileInApi(config, mimeType, fileName, size)
      val userHttpOptions = config?.httpOptions

      val responseBody = uploadChunked(uploadUrl, channel, size, userHttpOptions)
      return fileFromUploadResponseBody(responseBody)
    } catch (e: Exception) {
      channel.cancel(e)
      throw e
    }
  }

  /**
   * Downloads a file from the API.
   *
   * @param fileName The name of the file to download.
   * @param config Optional configuration for the download.
   * @return The ByteReadChannel of the downloaded file.
   */
  suspend fun download(fileName: String, config: DownloadFileConfig? = null): ByteReadChannel {
    if (apiClient.enterprise) {
      throw UnsupportedOperationException(
        "This method is only supported in Gemini Developer API mode, not in Gemini Enterprise Agent Platform mode."
      )
    }
    val extractedFileName =
      Transformers.tFileName(fileName)
        ?: throw IllegalArgumentException("Invalid file name: $fileName")
    val httpOptions = config?.httpOptions
    val response =
      apiClient.requestChannel(
        method = "GET",
        path = "files/$extractedFileName:download?alt=media",
        httpOptions = httpOptions,
      )
    return response.bodyAsChannel()
  }

  /**
   * Downloads a file from the API.
   *
   * @param file The file to download.
   * @param config Optional configuration for the download.
   * @return The ByteReadChannel of the downloaded file.
   */
  suspend fun download(file: File, config: DownloadFileConfig? = null): ByteReadChannel {
    val name = file.name ?: throw IllegalArgumentException("File name is required.")
    return download(name, config)
  }

  /**
   * Registers a file.
   *
   * @param credentials The Google credentials used to authenticate.
   * @param uris The list of URIs to register.
   * @param config Optional configuration for the registration.
   * @return The [RegisterFilesResponse] containing the registered files.
   */
  suspend fun registerFiles(
    credentials: GoogleCredentials,
    uris: List<String>,
    config: RegisterFilesConfig? = null,
  ): RegisterFilesResponse {
    if (uris.isEmpty()) throw IllegalArgumentException("uris list must not be empty")

    var updatedConfig = config
    if (apiClient.credentials !== credentials) {
      val builder = io.ktor.client.request.HttpRequestBuilder()
      credentials.applyToRequest(builder)

      val baseHeaders = config?.httpOptions?.headers ?: emptyMap()
      val newHeaders = baseHeaders.toMutableMap()

      builder.headers.entries().forEach { (k, v) -> newHeaders[k] = v.last() }

      val updatedHttpOptions = (config?.httpOptions ?: HttpOptions()).copy(headers = newHeaders)
      updatedConfig = (config ?: RegisterFilesConfig()).copy(httpOptions = updatedHttpOptions)
    }

    return privateRegisterFiles(uris = uris, config = updatedConfig)
  }

  private suspend fun createFileInApi(
    config: UploadFileConfig?,
    mimeType: String?,
    fileName: String?,
    size: Long,
  ): String {
    val apiFileBuilder =
      File(name = config?.name, mimeType = config?.mimeType, displayName = config?.displayName)

    var apiFile = apiFileBuilder
    val apiFileName = apiFile.name
    if (apiFileName != null && !apiFileName.startsWith("files/")) {
      apiFile = apiFile.copy(name = "files/$apiFileName")
    }

    val resolvedMimeType = mimeType ?: apiFile.mimeType ?: "application/octet-stream"

    val headers =
      mutableMapOf(
        "Content-Type" to "application/json",
        "X-Goog-Upload-Protocol" to "resumable",
        "X-Goog-Upload-Command" to "start",
        "X-Goog-Upload-Header-Content-Length" to size.toString(),
        "X-Goog-Upload-Header-Content-Type" to resolvedMimeType,
      )
    if (fileName != null) {
      headers["X-Goog-Upload-File-Name"] = fileName
    }

    val createFileHttpOptions =
      (config?.httpOptions ?: HttpOptions()).copy(apiVersion = "", headers = headers)

    val response =
      privateCreate(
        file = apiFile,
        config =
          CreateFileConfig(httpOptions = createFileHttpOptions, shouldReturnHttpResponse = true),
      )

    val responseHeaders =
      response.sdkHttpResponse?.headers
        ?: throw IllegalStateException(
          "Failed to create file. HTTP response headers were not returned."
        )
    val uploadUrl =
      responseHeaders.entries
        .firstOrNull { it.key.equals("x-goog-upload-url", ignoreCase = true) }
        ?.value
        ?: throw IllegalStateException(
          "Failed to create file. Upload URL was not returned in the response headers."
        )
    return uploadUrl
  }

  private suspend fun uploadChunked(
    uploadUrl: String,
    channel: ByteReadChannel,
    totalSize: Long,
    httpOptions: HttpOptions?,
  ): String {
    val chunkSize = 8388608
    val buffer = ByteArray(chunkSize)
    var offset = 0L
    var isFirstChunk = true

    while (!channel.isClosedForRead || isFirstChunk) {
      var bytesRead = 0
      while (bytesRead < chunkSize && !channel.isClosedForRead) {
        val remaining = chunkSize - bytesRead
        val read = channel.readAvailable(buffer, bytesRead, remaining)
        if (read == -1) break
        bytesRead += read
      }

      val isLastChunk = channel.isClosedForRead || offset + bytesRead >= totalSize
      val chunk =
        if (bytesRead == 0) {
          ByteArray(0)
        } else if (bytesRead == chunkSize) {
          buffer
        } else {
          buffer.copyOf(bytesRead)
        }

      val uploadCommand = if (isLastChunk) "upload, finalize" else "upload"

      val chunkResponse =
        uploadChunkWithRetries(
          uploadUrl = uploadUrl,
          chunk = chunk,
          offset = offset,
          uploadCommand = uploadCommand,
          httpOptions = httpOptions,
        )

      offset += chunk.size
      isFirstChunk = false

      val status = chunkResponse.uploadStatus
      if (isLastChunk) {
        if (status != "final") {
          throw IllegalStateException("Unexpected final upload status: $status please try again.")
        }
        return chunkResponse.body
          ?: throw IllegalStateException("No body returned in final upload response.")
      } else {
        if (status != "active") {
          throw IllegalStateException("Unexpected upload status: $status please try again.")
        }
      }
      if (isLastChunk) break
    }
    throw IllegalStateException("Upload finished without final response.")
  }

  private suspend fun uploadChunkWithRetries(
    uploadUrl: String,
    chunk: ByteArray,
    offset: Long,
    uploadCommand: String,
    httpOptions: HttpOptions?,
  ): UploadChunkResponse {
    val maxRetries = 3
    var lastException: Exception? = null
    var delayMs = 1000L

    val headers = (httpOptions?.headers ?: emptyMap()).toMutableMap()
    headers["X-Goog-Upload-Command"] = uploadCommand
    headers["X-Goog-Upload-Offset"] = offset.toString()
    headers["Content-Type"] = "application/octet-stream"

    val chunkHttpOptions = (httpOptions ?: HttpOptions()).copy(headers = headers)

    for (retryCount in 0 until maxRetries) {
      try {
        val response =
          apiClient.request(
            method = "POST",
            path = uploadUrl,
            body = chunk,
            httpOptions = chunkHttpOptions,
          )

        val statusHeader =
          response.headers
            .entries()
            .firstOrNull { it.key.equals("x-goog-upload-status", ignoreCase = true) }
            ?.value
            ?.firstOrNull()

        if (statusHeader != null) {
          return UploadChunkResponse(statusHeader, response.body())
        } else {
          lastException = IllegalStateException("Missing x-goog-upload-status header in response.")
        }
      } catch (e: CancellationException) {
        throw e
      } catch (e: ClientException) {
        if (e.code == 408 || e.code == 429) {
          lastException = e
        } else {

          throw e
        }
      } catch (e: Exception) {
        lastException = e
      }

      if (retryCount < maxRetries - 1) {
        delay(delayMs)
        delayMs *= 2
      }
    }
    throw IllegalStateException(
      "Upload failed. Retries exhausted, please try again.",
      lastException,
    )
  }

  private fun fileFromUploadResponseBody(responseBody: String): File {
    val json = Json { ignoreUnknownKeys = true }
    val jsonElement = json.parseToJsonElement(responseBody)
    val fileElement =
      jsonElement.jsonObject["file"]
        ?: throw IllegalStateException("Response body does not contain 'file' field.")
    return json.decodeFromJsonElement(File.serializer(), fileElement)
  }

  private data class UploadChunkResponse(val uploadStatus: String, val body: String?)
}
