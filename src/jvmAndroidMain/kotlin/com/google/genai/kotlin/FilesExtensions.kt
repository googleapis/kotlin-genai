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

package com.google.genai.kotlin

import com.google.genai.kotlin.types.DownloadFileConfig
import com.google.genai.kotlin.types.File
import com.google.genai.kotlin.types.UploadFileConfig
import io.ktor.utils.io.jvm.javaio.toByteReadChannel
import io.ktor.utils.io.readAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Files as NioFiles
import java.nio.file.Path

/**
 * Uploads a file to the API.
 *
 * @param file The file to upload.
 * @param config Optional configuration for the upload.
 * @return The uploaded file.
 */
suspend fun Files.upload(file: java.io.File, config: UploadFileConfig? = null): File {
  val mimeType =
    config?.mimeType ?: NioFiles.probeContentType(file.toPath()) ?: "application/octet-stream"
  val resolvedConfig =
    (config ?: UploadFileConfig()).copy(
      mimeType = mimeType,
      displayName = config?.displayName ?: file.name,
    )
  val channel = file.inputStream().toByteReadChannel()
  return upload(channel, file.length(), resolvedConfig)
}

/**
 * Uploads a file at the specified path to the API.
 *
 * @param path The path of the file to upload.
 * @param config Optional configuration for the upload.
 * @return The uploaded file.
 */
suspend fun Files.upload(path: Path, config: UploadFileConfig? = null): File {
  val mimeType = config?.mimeType ?: NioFiles.probeContentType(path) ?: "application/octet-stream"
  val resolvedConfig =
    (config ?: UploadFileConfig()).copy(
      mimeType = mimeType,
      displayName = config?.displayName ?: path.fileName?.toString() ?: "unknown",
    )
  val channel = NioFiles.newInputStream(path).toByteReadChannel()
  val size = NioFiles.size(path)
  return upload(channel, size, resolvedConfig)
}

/**
 * Downloads a file from the API and streams it directly to a local file path.
 *
 * @param fileName The name of the file to download.
 * @param outputPath The path to save the downloaded file to.
 * @param config Optional configuration for the download.
 */
suspend fun Files.downloadToFile(
  fileName: String,
  outputPath: Path,
  config: DownloadFileConfig? = null,
) {
  val channel = download(fileName, config)
  try {
    withContext(Dispatchers.IO) {
      NioFiles.newOutputStream(outputPath).use { output ->
        val buffer = ByteArray(8192)
        while (!channel.isClosedForRead) {
          val read = channel.readAvailable(buffer, 0, buffer.size)
          if (read == -1) break
          output.write(buffer, 0, read)
        }
      }
    }
  } catch (e: Exception) {
    channel.cancel(e)
    throw e
  }
}

/**
 * Downloads a file from the API and streams it directly to a local file path.
 *
 * @param file The file to download.
 * @param outputPath The path to save the downloaded file to.
 * @param config Optional configuration for the download.
 */
suspend fun Files.downloadToFile(file: File, outputPath: Path, config: DownloadFileConfig? = null) {
  val name = file.name ?: throw IllegalArgumentException("File name is required.")
  downloadToFile(name, outputPath, config)
}
