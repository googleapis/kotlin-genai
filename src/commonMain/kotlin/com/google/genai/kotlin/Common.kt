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

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.longOrNull

/** Common utility methods for the GenAI SDK. */
internal object Common {

  @Suppress("OPT_IN_USAGE")
  val JSON = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
    allowSpecialFloatingPointValues = true
  }

  /**
   * Sets the value of an object by a path.
   *
   * <p>setValueByPath({}, arrayOf("a", "b"), v) -> {"a": {"b": v}}
   *
   * <p>setValueByPath({}, ['a', 'b[]', c], [v1, v2]) -> {'a': {'b': [{'c': v1}, {'c': v2}]}}
   *
   * <p>setValueByPath({'a': {'b':[{'c': v1}, {'c': v2}]}}, ['a', 'b[]', 'd'], v3) -> {'a': {'b':
   * [{'c': v1, 'd': v3}, {'c': v2,'d': v3}]}}
   */
  fun setValueByPath(jsonObject: MutableMap<String, Any?>?, path: Array<String>, value: Any?) {
    require(path.isNotEmpty()) { "Path cannot be empty." }
    require(jsonObject != null) { "JSON object cannot be null." }

    var currentObject: MutableMap<String, Any?> = jsonObject

    for (i in 0 until path.size - 1) {
      val key = path[i]

      when {
        key.endsWith("[]") -> {
          val keyName = key.removeSuffix("[]")
          if (!currentObject.containsKey(keyName)) {
            currentObject[keyName] = mutableListOf<Any?>()
          }
          @Suppress("UNCHECKED_CAST") val arrayNode = currentObject[keyName] as MutableList<Any?>
          val remainingPath = path.drop(i + 1).toTypedArray()

          if (value is List<*>) {
            if (arrayNode.size != value.size) {
              arrayNode.clear()
              repeat(value.size) { arrayNode.add(mutableMapOf<String, Any?>()) }
            }
            for (j in arrayNode.indices) {
              @Suppress("UNCHECKED_CAST")
              setValueByPath(arrayNode[j] as MutableMap<String, Any?>, remainingPath, value[j])
            }
          } else {
            if (arrayNode.isEmpty()) {
              arrayNode.add(mutableMapOf<String, Any?>())
            }
            for (j in arrayNode.indices) {
              @Suppress("UNCHECKED_CAST")
              setValueByPath(arrayNode[j] as MutableMap<String, Any?>, remainingPath, value)
            }
          }
          return
        }
        key.endsWith("[0]") -> {
          val keyName = key.removeSuffix("[0]")
          if (!currentObject.containsKey(keyName)) {
            currentObject[keyName] = mutableListOf(mutableMapOf<String, Any?>())
          }
          @Suppress("UNCHECKED_CAST") val arrayNode = currentObject[keyName] as MutableList<Any?>

          @Suppress("UNCHECKED_CAST")
          currentObject = arrayNode[0] as MutableMap<String, Any?>
        }
        else -> {
          if (!currentObject.containsKey(key)) {
            currentObject[key] = mutableMapOf<String, Any?>()
          }

          @Suppress("UNCHECKED_CAST")
          currentObject = currentObject[key] as MutableMap<String, Any?>
        }
      }
    }

    val keyToSet = path.last()
    if (keyToSet == "_self" && value is Map<*, *>) {
      @Suppress("UNCHECKED_CAST") currentObject.putAll(value as Map<String, Any?>)
    } else {
      currentObject[keyToSet] = value
    }
  }

  /**
   * Gets the value of an object by a path.
   *
   * <p>getValueByPath({'a': {'b': v}}, ['a', 'b']) -> v
   *
   * <p>getValueByPath({'a': {'b': [{'c': v1}, {'c': v2}]}}, ['a', 'b[]', 'c']) -> [v1, v2]
   */
  fun getValueByPath(jsonObject: Any?, keys: Array<String>, defaultValue: Any? = null): Any? {
    if (jsonObject == null) return defaultValue
    if (keys.size == 1 && keys[0] == "_self") return jsonObject

    var currentObject: Any? = jsonObject
    for (i in keys.indices) {
      val key = keys[i]

      when {
        key.endsWith("[]") -> {
          val keyName = key.removeSuffix("[]")
          val mapNode = currentObject as? Map<*, *>
          val arrayNode = mapNode?.get(keyName) as? List<*> ?: return defaultValue

          if (i == keys.size - 1) {
            return arrayNode
          }

          val result = mutableListOf<Any?>()
          val remainingKeys = keys.drop(i + 1).toTypedArray()

          for (element in arrayNode) {
            val node = getValueByPath(element, remainingKeys, defaultValue)
            if (node != null) {
              result.add(node)
            }
          }
          return result
        }
        key.endsWith("[0]") -> {
          val keyName = key.removeSuffix("[0]")
          val mapNode = currentObject as? Map<*, *>
          val arrayNode = mapNode?.get(keyName) as? List<*>

          if (arrayNode != null && arrayNode.isNotEmpty()) {
            currentObject = arrayNode[0]
          } else {
            return defaultValue
          }
        }
        else -> {
          val mapNode = currentObject as? Map<*, *>
          if (mapNode != null && mapNode.containsKey(key)) {
            currentObject = mapNode[key]
          } else {
            return defaultValue
          }
        }
      }
    }

    return currentObject
  }

  /**
   * Formats a template with a map of data.
   *
   * <p>formatMap("{model}:generateContent", "gemini-3.0-flash") ->
   * "gemini-3.0-flash:generateContent"
   */
  fun formatMap(template: String, data: Map<String, Any?>?): String {
    if (data == null) return template

    var formattedTemplate = template
    for ((key, value) in data) {
      val placeholder = "{$key}"
      if (formattedTemplate.contains(placeholder)) {
        formattedTemplate = formattedTemplate.replace(placeholder, value.toString())
      }
    }
    return formattedTemplate
  }

  /** Returns true if the object is zero or null in the given type. */
  fun isZero(obj: Any?): Boolean {
    return when (obj) {
      null -> true
      is Number -> obj.toDouble() == 0.0
      is Char -> obj == '\u0000'
      is Boolean -> !obj
      else -> false
    }
  }

  /** Converts a Map (or MutableMap) into a kotlinx.serialization JsonObject. */
  fun mapToJsonObject(map: Map<String, Any?>): JsonObject {
    val jsonMap = map.entries.associate { it.key to anyToJsonElement(it.value) }
    return JsonObject(jsonMap)
  }

  /** Parses a JSON string directly into a MutableMap<String, Any?>. */
  fun jsonStringToMap(jsonString: String): MutableMap<String, Any?> {
    val jsonElement = JSON.parseToJsonElement(jsonString)

    require(jsonElement is JsonObject) { "JSON string must represent a JSON object." }

    @Suppress("UNCHECKED_CAST")
    return jsonElementToAny(jsonElement) as MutableMap<String, Any?>
  }

  /** Converts a Map<String, Any?> directly into a specified Data Class. */
  inline fun <reified T> mapToDataClass(map: Map<String, Any?>): T {
    val jsonObject = mapToJsonObject(map)
    return JSON.decodeFromJsonElement<T>(jsonObject)
  }

  /** Converts any Data Class directly into a MutableMap<String, Any?>. */
  inline fun <reified T> dataClassToMap(data: T): MutableMap<String, Any?> {
    val jsonElement = JSON.encodeToJsonElement(data)
    require(jsonElement is JsonObject) { "Data class must serialize to a JSON object." }

    @Suppress("UNCHECKED_CAST")
    return jsonElementToAny(jsonElement) as MutableMap<String, Any?>
  }

  /** Converts any object to a JsonElement. */
  private fun anyToJsonElement(value: Any?): JsonElement {
    return when (value) {
      null -> JsonNull
      is JsonElement -> value
      is String -> JsonPrimitive(value)
      is Number -> JsonPrimitive(value)
      is Boolean -> JsonPrimitive(value)
      is Map<*, *> -> {
        val mapContent = value.entries.associate { it.key.toString() to anyToJsonElement(it.value) }
        JsonObject(mapContent)
      }
      is Iterable<*> -> JsonArray(value.map { anyToJsonElement(it) })
      is Array<*> -> JsonArray(value.map { anyToJsonElement(it) })
      else -> throw IllegalArgumentException("Cannot convert $value to JsonElement")
    }
  }

  /** Converts a JsonElement to any object. */
  private fun jsonElementToAny(element: JsonElement): Any? {
    return when (element) {
      is JsonNull -> null
      is JsonPrimitive -> {
        if (element.isString) {
          element.content
        } else {
          // Fallback parsing for primitives (booleans and numbers)
          element.booleanOrNull
            ?: element.intOrNull
            ?: element.longOrNull
            ?: element.doubleOrNull
            ?: element.content
        }
      }
      is JsonArray -> {
        element.map { jsonElementToAny(it) }.toMutableList()
      }
      is JsonObject -> {
        val map = mutableMapOf<String, Any?>()
        for ((key, value) in element) {
          map[key] = jsonElementToAny(value)
        }
        map
      }
    }
  }
}
