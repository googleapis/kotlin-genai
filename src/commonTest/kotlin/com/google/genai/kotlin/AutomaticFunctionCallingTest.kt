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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.serializer

private inline fun <reified T> schemaOf(): JsonObject = jsonSchemaOf(serializer<T>().descriptor)

private fun assertSchema(expected: String, actual: JsonObject) {
  assertEquals(Json.parseToJsonElement(expected), actual)
}

@Serializable
private enum class Unit1 {
  CELSIUS,
  FAHRENHEIT,
}

@Serializable private data class Point(val x: Int, val y: Int)

@Serializable
private data class Args(
  val required: String,
  val withDefault: Int = 2,
  val nullable: String?,
  @SerialName("renamed") val original: Boolean,
)

@Serializable
private data class Documented(
  @Describe("Departure city.") val origin: String,
  @Describe("Number of nights.") val nights: Int = 2,
)

@Describe("A place on a map.") @Serializable private data class DescribedClass(val x: Int)

@Serializable private data class Outer(val inner: Point, val tags: List<String>)

@Serializable private data class Trip(val from: Point, val to: Point)

@Serializable private data class SelfReferential(val next: SelfReferential?)

@Serializable private data class HoldsSelfReferential(val start: SelfReferential)

class AutomaticFunctionCallingTest {

  @Test
  fun testStringMapsToString() {
    assertSchema("""{"type":"string"}""", schemaOf<String>())
  }

  @Test
  fun testCharMapsToString() {
    assertSchema("""{"type":"string"}""", schemaOf<Char>())
  }

  @Test
  fun testIntegerTypesMapToInteger() {
    for (schema in listOf(schemaOf<Int>(), schemaOf<Long>(), schemaOf<Short>(), schemaOf<Byte>())) {
      assertSchema("""{"type":"integer"}""", schema)
    }
  }

  @Test
  fun testFloatingPointTypesMapToNumber() {
    for (schema in listOf(schemaOf<Float>(), schemaOf<Double>())) {
      assertSchema("""{"type":"number"}""", schema)
    }
  }

  @Test
  fun testBooleanMapsToBoolean() {
    assertSchema("""{"type":"boolean"}""", schemaOf<Boolean>())
  }

  @Test
  fun testEnumMapsToStringWithConstants() {
    assertSchema("""{"type":"string","enum":["CELSIUS","FAHRENHEIT"]}""", schemaOf<Unit1>())
  }

  @Test
  fun testListMapsToArrayWithItems() {
    assertSchema("""{"type":"array","items":{"type":"string"}}""", schemaOf<List<String>>())
  }

  @Test
  fun testMapMapsToObjectWithAdditionalProperties() {
    assertSchema(
      """{"type":"object","additionalProperties":{"type":"integer"}}""",
      schemaOf<Map<String, Int>>(),
    )
  }

  @Test
  fun testMapWithNonStringKeyThrows() {
    val error = assertFailsWith<IllegalArgumentException> { schemaOf<Map<Int, String>>() }
    assertTrue(error.message!!.contains("String or enum keys"), error.message!!)
  }

  @Test
  fun testClassMapsToObjectWithPropertiesAndRequired() {
    assertSchema(
      """
      {
        "type": "object",
        "properties": {
          "x": {"type": "integer"},
          "y": {"type": "integer"}
        },
        "required": ["x", "y"]
      }
      """,
      schemaOf<Point>(),
    )
  }

  @Test
  fun testNestedClassGoesToDefsAndIsReferenced() {
    assertSchema(
      """
      {
        "type": "object",
        "properties": {
          "inner": {"${'$'}ref": "#/${'$'}defs/Point"},
          "tags": {"type": "array", "items": {"type": "string"}}
        },
        "required": ["inner", "tags"],
        "${'$'}defs": {
          "Point": {
            "type": "object",
            "properties": {"x": {"type": "integer"}, "y": {"type": "integer"}},
            "required": ["x", "y"]
          }
        }
      }
      """,
      schemaOf<Outer>(),
    )
  }

  @Test
  fun testRepeatedNestedClassIsDefinedOnce() {
    assertSchema(
      """
      {
        "type": "object",
        "properties": {
          "from": {"${'$'}ref": "#/${'$'}defs/Point"},
          "to": {"${'$'}ref": "#/${'$'}defs/Point"}
        },
        "required": ["from", "to"],
        "${'$'}defs": {
          "Point": {
            "type": "object",
            "properties": {"x": {"type": "integer"}, "y": {"type": "integer"}},
            "required": ["x", "y"]
          }
        }
      }
      """,
      schemaOf<Trip>(),
    )
  }

  @Test
  fun testDefaultIsOmittedFromRequiredAndNullableIsNot() {
    assertSchema(
      """
      {
        "type": "object",
        "properties": {
          "required": {"type": "string"},
          "withDefault": {"type": "integer"},
          "nullable": {"anyOf": [{"type": "string"}, {"type": "null"}]},
          "renamed": {"type": "boolean"}
        },
        "required": ["required", "nullable", "renamed"]
      }
      """,
      schemaOf<Args>(),
    )
  }

  @Test
  fun testDescribeAnnotationSetsDescription() {
    assertSchema(
      """
      {
        "type": "object",
        "properties": {
          "origin": {"type": "string", "description": "Departure city."},
          "nights": {"type": "integer", "description": "Number of nights."}
        },
        "required": ["origin"]
      }
      """,
      schemaOf<Documented>(),
    )
  }

  @Test
  fun testDescribeOnClassDescribesTheObject() {
    assertSchema(
      """
      {
        "type": "object",
        "properties": {"x": {"type": "integer"}},
        "required": ["x"],
        "description": "A place on a map."
      }
      """,
      schemaOf<DescribedClass>(),
    )
  }

  @Test
  fun testSelfReferentialClassTerminatesViaRef() {
    assertSchema(
      """
      {
        "type": "object",
        "properties": {"start": {"${'$'}ref": "#/${'$'}defs/SelfReferential"}},
        "required": ["start"],
        "${'$'}defs": {
          "SelfReferential": {
            "type": "object",
            "properties": {
              "next": {
                "anyOf": [{"${'$'}ref": "#/${'$'}defs/SelfReferential"}, {"type": "null"}]
              }
            },
            "required": ["next"]
          }
        }
      }
      """,
      schemaOf<HoldsSelfReferential>(),
    )
  }
}
