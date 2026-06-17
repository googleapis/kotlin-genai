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

package com.google.genai.kotlin.types

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/** A custom serializer for [Instant] that encodes to and decodes from ISO-8601 strings. */
object InstantSerializer : KSerializer<Instant> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: Instant) {
    encoder.encodeString(value.toString())
  }

  override fun deserialize(decoder: Decoder): Instant {
    return Instant.parse(decoder.decodeString())
  }
}

/**
 * A custom serializer for [ByteArray] that encodes to and decodes from Base64 strings.
 *
 * It uses standard Base64 encoding for serialization. For deserialization, it first attempts to
 * decode using standard Base64, and falls back to URL-safe Base64 if that fails.
 */
@OptIn(ExperimentalEncodingApi::class)
object ByteArrayAsBase64Serializer : KSerializer<ByteArray> {
  override val descriptor: SerialDescriptor =
    PrimitiveSerialDescriptor("ByteArrayAsBase64Serializer", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: ByteArray) {
    encoder.encodeString(Base64.Default.encode(value))
  }

  override fun deserialize(decoder: Decoder): ByteArray {
    val stringValue = decoder.decodeString()
    return try {
      Base64.Default.decode(stringValue)
    } catch (e: IllegalArgumentException) {
      Base64.UrlSafe.decode(stringValue)
    }
  }
}
