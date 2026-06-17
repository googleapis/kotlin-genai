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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.serialization.json.Json

class SerializersTest {

  @Test
  fun testByteArrayAsBase64Serializer_serialize() {
    val bytes = "Hello World".toByteArray()
    val jsonStr = Json.encodeToString(ByteArrayAsBase64Serializer, bytes)
    assertEquals("\"SGVsbG8gV29ybGQ=\"", jsonStr)
  }

  @Test
  fun testByteArrayAsBase64Serializer_deserialize_standard() {
    val jsonStr = "\"SGVsbG8gV29ybGQ=\""
    val decoded = Json.decodeFromString(ByteArrayAsBase64Serializer, jsonStr)
    assertTrue("Hello World".toByteArray().contentEquals(decoded))
  }

  @Test
  fun testByteArrayAsBase64Serializer_deserialize_urlSafe() {
    val jsonStr = "\"c3ViamVjdHM_Pw==\""
    val decoded = Json.decodeFromString(ByteArrayAsBase64Serializer, jsonStr)
    assertTrue("subjects??".toByteArray().contentEquals(decoded))
  }

  @Test
  fun testInstantSerializer_serialize() {
    val instantStr = "2026-04-20T22:00:00Z"
    val instant = Instant.parse(instantStr)
    val jsonStr = Json.encodeToString(InstantSerializer, instant)
    assertEquals("\"$instantStr\"", jsonStr)
  }

  @Test
  fun testInstantSerializer_deserialize() {
    val instantStr = "2026-04-20T22:00:00Z"
    val jsonStr = "\"$instantStr\""
    val decoded = Json.decodeFromString(InstantSerializer, jsonStr)
    assertEquals(instantStr, decoded.toString())
  }
}
