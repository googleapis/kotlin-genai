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

@file:Suppress("OPT_IN_USAGE")

package com.google.genai.kotlin

import kotlinx.serialization.SerialInfo
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * Describes a function parameter to the model, so it knows what to pass.
 *
 * Put it on a property to describe that parameter, or on a class to describe the object it stands
 * for.
 *
 * ```
 * @Serializable
 * data class PlanTripArgs(
 *   @Describe("Departure city.") val origin: String,
 *   @Describe("Number of nights to stay.") val nights: Int = 2,
 * )
 * ```
 *
 * To describe the function itself, pass a description when you build the `CallableFunction`.
 */
@SerialInfo
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Describe(val value: String)

/**
 * Builds the JSON Schema that describes [descriptor] to the model, for
 * `FunctionDeclaration.parametersJsonSchema`.
 *
 * Nested classes are collected into a `$defs` map on the returned object and referenced by `$ref`,
 * matching what the Python SDK sends. A caller that embeds this schema inside a larger one must
 * lift `$defs` to the root of that schema, since a `$ref` resolves from the document root.
 *
 * Throws [IllegalArgumentException] for a type that cannot be described: a map with keys that are
 * not strings or enums, or a kind with no JSON Schema equivalent.
 */
internal fun jsonSchemaOf(descriptor: SerialDescriptor): JsonObject {
  val defs = Defs()
  val root = schemaOf(descriptor, description = null, defs = defs, inlineObject = true)
  if (defs.schemas.isEmpty()) {
    return root
  }
  val merged = LinkedHashMap<String, JsonElement>(root)
  merged["\$defs"] = JsonObject(defs.schemas)
  return JsonObject(merged)
}

/** Collects the nested classes reached while walking a type, so each is described only once. */
private class Defs {
  val schemas = LinkedHashMap<String, JsonObject>()

  // A class being described right now. A type that reaches itself gets a $ref to an entry that is
  // still being built, which is what lets a recursive type terminate.
  private val inProgress = mutableSetOf<String>()
  private val keys = mutableMapOf<String, String>()

  /** Returns the `$defs` key for a class, assigning one on first use. */
  // Keys are simple names because the model reads them. Two classes can share one, so the second
  // claimant falls back to its fully qualified name.
  fun keyFor(serialName: String): String =
    keys.getOrPut(serialName) {
      val simple = serialName.substringAfterLast('.')
      if (keys.containsValue(simple)) serialName else simple
    }

  /** Whether this class has already been described, or is being described right now. */
  fun isKnown(key: String) = key in schemas || key in inProgress

  /** Describes a class under [key], marking it in progress so a cycle stops at a `$ref`. */
  fun define(key: String, build: () -> JsonObject) {
    inProgress += key
    schemas[key] = build()
    inProgress -= key
  }
}

/** Builds the schema for one type, wrapping it for nullability and attaching its description. */
private fun schemaOf(
  descriptor: SerialDescriptor,
  description: String?,
  defs: Defs,
  inlineObject: Boolean = false,
): JsonObject {
  val core = coreSchemaOf(descriptor, defs, inlineObject)

  // A nullable value cannot carry "null" in its type keyword once it is a $ref, because the type
  // sits behind the reference. anyOf is the one form that works for both, so both use it.
  val nullable =
    if (!descriptor.isNullable) {
      core
    } else {
      JsonObject(
        mapOf(
          "anyOf" to JsonArray(listOf(core, JsonObject(mapOf("type" to JsonPrimitive("null")))))
        )
      )
    }

  // An element's own @Describe wins over one on the class it points at, so the same argument class
  // can be documented differently at each use site.
  val text = description ?: descriptor.annotations.firstDescription()
  if (text == null) {
    return nullable
  }
  val described = LinkedHashMap<String, JsonElement>(nullable)
  described["description"] = JsonPrimitive(text)
  return JsonObject(described)
}

/** Maps a serial kind to its JSON Schema type, ignoring nullability and descriptions. */
private fun coreSchemaOf(
  descriptor: SerialDescriptor,
  defs: Defs,
  inlineObject: Boolean,
): JsonObject {
  val schema = LinkedHashMap<String, JsonElement>()
  when (val kind = descriptor.kind) {
    PrimitiveKind.STRING,
    PrimitiveKind.CHAR -> schema["type"] = JsonPrimitive("string")
    PrimitiveKind.BYTE,
    PrimitiveKind.SHORT,
    PrimitiveKind.INT,
    PrimitiveKind.LONG -> schema["type"] = JsonPrimitive("integer")
    PrimitiveKind.FLOAT,
    PrimitiveKind.DOUBLE -> schema["type"] = JsonPrimitive("number")
    PrimitiveKind.BOOLEAN -> schema["type"] = JsonPrimitive("boolean")
    SerialKind.ENUM -> {
      schema["type"] = JsonPrimitive("string")
      schema["enum"] =
        JsonArray(
          (0 until descriptor.elementsCount).map { JsonPrimitive(descriptor.getElementName(it)) }
        )
    }
    StructureKind.LIST -> {
      schema["type"] = JsonPrimitive("array")
      schema["items"] = schemaOf(descriptor.getElementDescriptor(0), null, defs)
    }
    StructureKind.MAP -> {
      val keyKind = descriptor.getElementDescriptor(0).kind
      require(keyKind == PrimitiveKind.STRING || keyKind == SerialKind.ENUM) {
        "A map argument must have String or enum keys to become a JSON Schema object, but " +
          "${descriptor.serialName} has keys of kind $keyKind."
      }
      schema["type"] = JsonPrimitive("object")
      schema["additionalProperties"] = schemaOf(descriptor.getElementDescriptor(1), null, defs)
    }
    StructureKind.CLASS,
    StructureKind.OBJECT ->
      if (inlineObject) {
        schema += objectSchemaOf(descriptor, defs)
      } else {
        val key = defs.keyFor(descriptor.serialName.removeSuffix("?"))
        if (!defs.isKnown(key)) {
          defs.define(key) { JsonObject(objectSchemaOf(descriptor, defs)) }
        }
        schema["\$ref"] = JsonPrimitive("#/\$defs/$key")
      }
    else ->
      throw IllegalArgumentException(
        "Cannot derive a JSON Schema for ${descriptor.serialName}: the serial kind $kind has no " +
          "JSON Schema equivalent. Wrap the value in a @Serializable class and convert it inside " +
          "the handler."
      )
  }
  return JsonObject(schema)
}

/** Builds the `properties` and `required` of a class, recursing into each field. */
private fun objectSchemaOf(descriptor: SerialDescriptor, defs: Defs): Map<String, JsonElement> {
  val properties =
    (0 until descriptor.elementsCount).associate { i ->
      descriptor.getElementName(i) to
        schemaOf(
          descriptor.getElementDescriptor(i),
          descriptor.getElementAnnotations(i).firstDescription(),
          defs,
        )
    }

  // A field with a default may be left out; a nullable field without one must still be sent, even
  // if its value is null. So this tracks optionality, not nullability.
  val required =
    (0 until descriptor.elementsCount)
      .filterNot { descriptor.isElementOptional(it) }
      .map { JsonPrimitive(descriptor.getElementName(it)) }

  val schema = LinkedHashMap<String, JsonElement>()
  schema["type"] = JsonPrimitive("object")
  schema["properties"] = JsonObject(properties)
  if (required.isNotEmpty()) {
    schema["required"] = JsonArray(required)
  }
  return schema
}

/** Returns the text of the first [Describe] in this list, if there is one. */
private fun List<Annotation>.firstDescription(): String? =
  filterIsInstance<Describe>().firstOrNull()?.value
