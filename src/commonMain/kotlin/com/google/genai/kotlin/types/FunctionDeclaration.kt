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
 * Structured representation of a function declaration as defined by the
 * [OpenAPI 3.0 specification](https://spec.openapis.org/oas/v3.0.3). Included in this declaration
 * are the function name, description, parameters and response type. This FunctionDeclaration is a
 * representation of a block of code that can be used as a `Tool` by the model and executed by the
 * client.
 */
@Serializable
data class FunctionDeclaration(

  /**
   * Optional. Specifies the function Behavior. If not specified, the system keeps the current
   * function call behavior. This field is currently only supported by the BidiGenerateContent
   * method.
   */
  val behavior: Behavior? = null,

  /**
   * Optional. Description and purpose of the function. Model uses it to decide how and whether to
   * call the function.
   */
  val description: String? = null,

  /**
   * Required. The name of the function to call. Must start with a letter or an underscore. Must be
   * a-z, A-Z, 0-9, or contain underscores, dots, colons and dashes, with a maximum length of 128.
   */
  val name: String? = null,

  /**
   * Optional. Describes the parameters to this function in JSON Schema Object format. Reflects the
   * Open API 3.03 Parameter Object. string Key: the name of the parameter. Parameter names are case
   * sensitive. Schema Value: the Schema defining the type used for the parameter. For function with
   * no parameters, this can be left unset. Parameter names must start with a letter or an
   * underscore and must only contain chars a-z, A-Z, 0-9, or underscores with a maximum length
   * of 64. Example with 1 required and 1 optional parameter: type: OBJECT properties: param1: type:
   * STRING param2: type: INTEGER required: - param1
   */
  val parameters: Schema? = null,

  /**
   * Optional. Describes the parameters to the function in JSON Schema format. The schema must
   * describe an object where the properties are the parameters to the function. For example: ``` {
   * "type": "object", "properties": { "name": { "type": "string" }, "age": { "type": "integer" } },
   * "additionalProperties": false, "required": ["name", "age"], "propertyOrdering": ["name", "age"]
   * } ``` This field is mutually exclusive with `parameters`.
   */
  val parametersJsonSchema: JsonElement? = null,

  /**
   * Optional. Describes the output from this function in JSON Schema format. Reflects the Open API
   * 3.03 Response Object. The Schema defines the type used for the response value of the function.
   */
  val response: Schema? = null,

  /**
   * Optional. Describes the output from this function in JSON Schema format. The value specified by
   * the schema is the response value of the function. This field is mutually exclusive with
   * `response`.
   */
  val responseJsonSchema: JsonElement? = null,
)
