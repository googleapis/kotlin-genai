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

package com.google.genai.kotlin.types

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Schema is used to define the format of input/output data.
 *
 * Represents a select subset of an
 * [OpenAPI 3.0 schema object](https://spec.openapis.org/oas/v3.0.3#schema-object). More fields may
 * be added in the future as needed.
 */
@Serializable
data class Schema(

  /**
   * Optional. The instance must be valid against any (one or more) of the subschemas listed in
   * `any_of`.
   */
  val anyOf: List<Schema>? = null,

  /** Optional. Default value to use if the field is not specified. */
  val default: JsonElement? = null,

  /**
   * Optional. Describes the data. The model uses this field to understand the purpose of the schema
   * and how to use it. It is a best practice to provide a clear and descriptive explanation for the
   * schema and its properties here, rather than in the prompt.
   */
  val description: String? = null,

  /**
   * Optional. Possible values of the field. This field can be used to restrict a value to a fixed
   * set of values. To mark a field as an enum, set `format` to `enum` and provide the list of
   * possible values in `enum`. For example: 1. To define directions: `{type:STRING, format:enum,
   * enum:["EAST", "NORTH", "SOUTH", "WEST"]}` 2. To define apartment numbers: `{type:INTEGER,
   * format:enum, enum:["101", "201", "301"]}`
   */
  val enum: List<String>? = null,

  /** Optional. Example of an instance of this schema. */
  val example: JsonElement? = null,

  /**
   * Optional. The format of the data. For `NUMBER` type, format can be `float` or `double`. For
   * `INTEGER` type, format can be `int32` or `int64`. For `STRING` type, format can be `email`,
   * `byte`, `date`, `date-time`, `password`, and other formats to further refine the data type.
   */
  val format: String? = null,

  /** Optional. If type is `ARRAY`, `items` specifies the schema of elements in the array. */
  val items: Schema? = null,

  /**
   * Optional. If type is `ARRAY`, `max_items` specifies the maximum number of items in an array.
   */
  val maxItems: Long? = null,

  /** Optional. If type is `STRING`, `max_length` specifies the maximum length of the string. */
  val maxLength: Long? = null,

  /**
   * Optional. If type is `OBJECT`, `max_properties` specifies the maximum number of properties that
   * can be provided.
   */
  val maxProperties: Long? = null,

  /** Optional. If type is `INTEGER` or `NUMBER`, `maximum` specifies the maximum allowed value. */
  val maximum: Double? = null,

  /**
   * Optional. If type is `ARRAY`, `min_items` specifies the minimum number of items in an array.
   */
  val minItems: Long? = null,

  /** Optional. If type is `STRING`, `min_length` specifies the minimum length of the string. */
  val minLength: Long? = null,

  /**
   * Optional. If type is `OBJECT`, `min_properties` specifies the minimum number of properties that
   * can be provided.
   */
  val minProperties: Long? = null,

  /** Optional. If type is `INTEGER` or `NUMBER`, `minimum` specifies the minimum allowed value. */
  val minimum: Double? = null,

  /** Optional. Indicates if the value of this field can be null. */
  val nullable: Boolean? = null,

  /**
   * Optional. If type is `STRING`, `pattern` specifies a regular expression that the string must
   * match.
   */
  val pattern: String? = null,

  /**
   * Optional. If type is `OBJECT`, `properties` is a map of property names to schema definitions
   * for each property of the object.
   */
  val properties: Map<String, Schema>? = null,

  /**
   * Optional. Order of properties displayed or used where order matters. This is not a standard
   * field in OpenAPI specification, but can be used to control the order of properties.
   */
  val propertyOrdering: List<String>? = null,

  /**
   * Optional. If type is `OBJECT`, `required` lists the names of properties that must be present.
   */
  val required: List<String>? = null,

  /** Optional. Title for the schema. */
  val title: String? = null,

  /** Optional. Data type of the schema field. */
  val type: Type? = null,
)
