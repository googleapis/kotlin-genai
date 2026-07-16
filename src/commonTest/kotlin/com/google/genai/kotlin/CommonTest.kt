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

import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put

class CommonTest {

  @Test
  fun testSetValueByPath_simpleObject() {
    val data = mutableMapOf<String, Any?>()
    Common.setValueByPath(data, arrayOf("a", "b"), "v")

    // Expected: {"a": {"b": "v"}}
    val aNode = data["a"] as Map<*, *>
    assertEquals("v", aNode["b"])
  }

  @Test
  fun testSetValueByPath_arrayObject() {
    val data = mutableMapOf<String, Any?>()
    Common.setValueByPath(data, arrayOf("a", "b[]", "c"), listOf("v1", "v2"))

    // Expected: {"a": {"b": [{"c": "v1"}, {"c": "v2"}]}}
    val aNode = data["a"] as Map<*, *>
    val bArray = aNode["b"] as List<*>

    val firstElement = bArray[0] as Map<*, *>
    val secondElement = bArray[1] as Map<*, *>

    assertEquals("v1", firstElement["c"])
    assertEquals("v2", secondElement["c"])
  }

  @Test
  fun testSetValueByPath_arrayObject_existing() {
    val data = mutableMapOf<String, Any?>()
    Common.setValueByPath(data, arrayOf("a", "b[]", "c"), listOf("v1", "v2"))
    Common.setValueByPath(data, arrayOf("a", "b[]", "d"), "v3")

    // Expected: {"a": {"b": [{"c": "v1", "d": "v3"}, {"c": "v2", "d": "v3"}]}}
    val aNode = data["a"] as Map<*, *>
    val bArray = aNode["b"] as List<*>

    val firstElement = bArray[0] as Map<*, *>
    val secondElement = bArray[1] as Map<*, *>

    assertEquals("v1", firstElement["c"])
    assertEquals("v2", secondElement["c"])
    assertEquals("v3", firstElement["d"])
    assertEquals("v3", secondElement["d"])
  }

  @Test
  fun testSetValueByPath_firstArrayElement() {
    val data = mutableMapOf<String, Any?>()
    Common.setValueByPath(data, arrayOf("a", "b[0]", "c"), "v")

    // Expected: {"a": {"b": [{"c": "v"}]}}
    val aNode = data["a"] as Map<*, *>
    val bArray = aNode["b"] as List<*>
    val firstElement = bArray[0] as Map<*, *>

    assertEquals("v", firstElement["c"])
  }

  @Test
  fun testSetValueByPath_selfKey() {
    val data = mutableMapOf<String, Any?>("a" to mutableMapOf("b" to mutableMapOf<String, Any?>()))
    val value = mutableMapOf<String, Any?>("c" to "v")
    Common.setValueByPath(data, arrayOf("_self"), value)

    // Expected: {"a": {"b": {"c": "v"}}}
    val aNode = data["a"] as Map<*, *>

    assertEquals(emptyMap<Any, Any>(), aNode["b"])
    assertEquals("v", data["c"])
  }

  @Test
  fun testSetValueByPath_selfKey_deepMerge() {
    val data = mutableMapOf<String, Any?>(
      "a" to mutableMapOf<String, Any?>("b" to "v1", "c" to mutableMapOf<String, Any?>("d" to "v2"))
    )
    val value = mapOf(
      "a" to mapOf("c" to mapOf("e" to "v3"), "f" to "v4"),
      "g" to "v5"
    )
    Common.setValueByPath(data, arrayOf("_self"), value)

    val aNode = data["a"] as Map<*, *>
    assertEquals("v1", aNode["b"])
    assertEquals("v4", aNode["f"])

    val cNode = aNode["c"] as Map<*, *>
    assertEquals("v2", cNode["d"])
    assertEquals("v3", cNode["e"])

    assertEquals("v5", data["g"])
  }

  @Test
  fun testSetValueByPath_regularKey_deepMerge() {
    val data = mutableMapOf<String, Any?>(
      "target" to mutableMapOf<String, Any?>("b" to "v1", "c" to mutableMapOf<String, Any?>("d" to "v2"))
    )
    val value = mapOf(
      "c" to mapOf("e" to "v3"),
      "f" to "v4"
    )
    Common.setValueByPath(data, arrayOf("target"), value)

    val targetNode = data["target"] as Map<*, *>
    assertEquals("v1", targetNode["b"])
    assertEquals("v4", targetNode["f"])

    val cNode = targetNode["c"] as Map<*, *>
    assertEquals("v2", cNode["d"])
    assertEquals("v3", cNode["e"])
  }

  @Test
  fun testGetValueByPath_simpleObject() {
    // {"a": {"b": "v"}}
    val data = mapOf("a" to mapOf("b" to "v"))
    assertEquals("v", Common.getValueByPath(data, arrayOf("a", "b")))
  }

  @Test
  fun testGetValueByPath_arrayObject() {
    // {"a": {"b": [{"c": "v1"}, {"c": "v2"}]}}
    val data = mapOf("a" to mapOf("b" to listOf(mapOf("c" to "v1"), mapOf("c" to "v2"))))

    val result = Common.getValueByPath(data, arrayOf("a", "b[]", "c")) as List<*>
    assertEquals("v1", result[0])
    assertEquals("v2", result[1])
  }

  @Test
  fun testGetValueByPath_firstArrayElement() {
    // {"a": {"b": [{"c": "v"}]}}
    val data = mapOf("a" to mapOf("b" to listOf(mapOf("c" to "v"))))
    assertEquals("v", Common.getValueByPath(data, arrayOf("a", "b[0]", "c")))
  }

  @Test
  fun testGetValueByPath_notFound() {
    val data = emptyMap<String, Any?>()
    assertNull(Common.getValueByPath(data, arrayOf("a", "b")))
  }

  @Test
  fun testGetValueByPath_nullObject() {
    assertNull(Common.getValueByPath(null, arrayOf("a", "b")))
  }

  @Test
  fun testGetValueByPath_self() {
    val data = mapOf("test" to "value")
    assertEquals(data, Common.getValueByPath(data, arrayOf("_self")))
  }

  @Test
  fun testFormatMap_simple() {
    val data = mapOf("name" to "Alice", "age" to "30")
    val template = "My name is {name} and I am {age} years old."
    val expected = "My name is Alice and I am 30 years old."
    assertEquals(expected, Common.formatMap(template, data))
  }

  @Test
  fun testFormatMap_missingKey() {
    val data = mapOf("name" to "Bob")
    val template = "My name is {name} and I live in {city}."
    val expected = "My name is Bob and I live in {city}."
    assertEquals(expected, Common.formatMap(template, data))
  }

  @Test
  fun testFormatMap_emptyData() {
    val data = emptyMap<String, Any?>()
    val template = "This is a test."
    val expected = "This is a test."
    assertEquals(expected, Common.formatMap(template, data))
  }

  @Test
  fun testFormatMap_nullData() {
    val template = "This is a test."
    val expected = "This is a test."
    assertEquals(expected, Common.formatMap(template, null))
  }

  @Test
  fun testIsZero_null() {
    assertTrue(Common.isZero(null))
  }

  @Test
  fun testIsZero_numbers() {
    assertTrue(Common.isZero(0))
    assertTrue(Common.isZero(0.0))
    assertTrue(Common.isZero(0f))
    assertTrue(Common.isZero(0L))
    assertTrue(Common.isZero(0.0.toBigDecimal()))

    assertFalse(Common.isZero(1))
    assertFalse(Common.isZero(-1))
    assertFalse(Common.isZero(0.00001))
  }

  @Test
  fun testIsZero_characters() {
    assertTrue(Common.isZero('\u0000'))

    assertFalse(Common.isZero('0'))
    assertFalse(Common.isZero('A'))
    assertFalse(Common.isZero(' '))
  }

  @Test
  fun testIsZero_booleans() {
    assertTrue(Common.isZero(false))

    assertFalse(Common.isZero(true))
  }

  @Test
  fun testIsZero_otherTypes() {
    assertFalse(Common.isZero("0"))
    assertFalse(Common.isZero(""))
    assertFalse(Common.isZero("false"))
    assertFalse(Common.isZero(emptyList<Any>()))
    assertFalse(Common.isZero(Any()))
  }

  @Test
  fun testMapToJsonObject_primitives() {
    val map = mapOf("stringKey" to "hello", "intKey" to 42, "booleanKey" to true, "nullKey" to null)

    // We can confidently use Json.parseToJsonElement to build the expected Object
    val expectedJson =
      Json.parseToJsonElement(
          """{"stringKey":"hello","intKey":42,"booleanKey":true,"nullKey":null}"""
        )
        .jsonObject

    assertEquals(expectedJson, Common.mapToJsonObject(map))
  }

  @Test
  fun testMapToJsonObject_nestedAndCollections() {
    val map =
      mapOf("nestedMap" to mapOf("innerKey" to "innerValue"), "listKey" to listOf("a", 1, false))

    val expectedJson =
      Json.parseToJsonElement("""{"nestedMap":{"innerKey":"innerValue"},"listKey":["a",1,false]}""")
        .jsonObject

    assertEquals(expectedJson, Common.mapToJsonObject(map))
  }

  @Test
  fun testJsonStringToMap_primitives() {
    val jsonString = """{"stringKey":"hello","intKey":42,"booleanKey":true,"nullKey":null}"""
    val result = Common.jsonStringToMap(jsonString)

    assertEquals("hello", result["stringKey"])
    assertEquals(42, result["intKey"])
    assertEquals(true, result["booleanKey"])
    assertNull(result["nullKey"])
    assertTrue(result.containsKey("nullKey"))
  }

  @Test
  fun testJsonStringToMap_nestedAndCollections() {
    val jsonString = """{"nestedMap":{"innerKey":"innerValue"},"listKey":["a",1,false]}"""
    val result = Common.jsonStringToMap(jsonString)

    val nestedMap = result["nestedMap"] as Map<*, *>
    assertEquals("innerValue", nestedMap["innerKey"])

    val listKey = result["listKey"] as List<*>
    assertEquals(3, listKey.size)
    assertEquals("a", listKey[0])
    assertEquals(1, listKey[1])
    assertEquals(false, listKey[2])
  }

  @Test
  fun testJsonStringToMap_notAnObject() {
    val jsonString = """["a", "b"]"""

    // Assert that the require(jsonElement is JsonObject) throws correctly
    assertFailsWith<IllegalArgumentException> { Common.jsonStringToMap(jsonString) }
  }

  @Test
  fun testConfigToMap_emptyConfig() {
    // A completely empty config (all defaults)
    val config = GenerateContentConfig()

    // Because explicitNulls = false, this should result in an entirely empty map!
    val map = Common.dataClassToMap(config)

    assertTrue(map.isEmpty(), "Expected empty map but got: $map")
  }

  @Test
  fun testConfigToMap_withPrimitives() {
    val config =
      GenerateContentConfig(
        temperature = 0.7,
        maxOutputTokens = 1024,
        responseMimeType = "application/json",
        stopSequences = listOf("END", "STOP"),
      )

    val map = Common.dataClassToMap(config)

    assertEquals(4, map.size)
    assertEquals(0.7, map["temperature"])
    assertEquals(1024, map["maxOutputTokens"])
    assertEquals("application/json", map["responseMimeType"])

    val stopSeq = map["stopSequences"] as List<*>
    assertEquals(2, stopSeq.size)
    assertEquals("END", stopSeq[0])
    assertEquals("STOP", stopSeq[1])
  }

  @Test
  fun testConfigToMap_withNestedDataClass() {
    val config =
      GenerateContentConfig(
        systemInstruction = Content(parts = listOf(Part(text = "You are a helpful AI.")))
      )

    val map = Common.dataClassToMap(config)

    // Verify the Content class was correctly unwrapped into dictionaries
    val systemInstructionMap = map["systemInstruction"] as Map<*, *>
    val partsList = systemInstructionMap["parts"] as List<*>
    val firstPart = partsList[0] as Map<*, *>

    assertEquals("You are a helpful AI.", firstPart["text"])
  }

  @Test
  fun testConfigToMap_withJsonObject() {
    // The user builds a raw kotlinx JsonObject
    val schema = buildJsonObject {
      put("type", "object")
      put("title", "MySchema")
    }

    val config = GenerateContentConfig(responseJsonSchema = schema)

    val map = Common.dataClassToMap(config)

    // Verify the JsonObject was safely converted into a standard MutableMap<String, Any?>
    // so that the Python generator's Common.setValueByPath can interact with it safely!
    val schemaMap = map["responseJsonSchema"] as Map<*, *>

    assertEquals("object", schemaMap["type"])
    assertEquals("MySchema", schemaMap["title"])
  }

  @Test
  fun testMapToConfig_validMap() {
    // Simulating a map returned from your server or modified by the generator
    val sourceMap = mapOf("temperature" to 0.5, "topK" to 40.0, "candidateCount" to 2)

    val config = Common.mapToDataClass<GenerateContentConfig>(sourceMap)

    assertEquals(0.5, config.temperature)
    assertEquals(40.0, config.topK)
    assertEquals(2, config.candidateCount)

    // Everything else should be null
    assertNull(config.systemInstruction)
    assertNull(config.responseMimeType)
  }
}
