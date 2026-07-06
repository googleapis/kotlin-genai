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

/** Tool details of a tool that the model may use to generate a response. */
@Serializable
data class Tool(

  /**
   * Optional. Retrieval tool type. System will always execute the provided retrieval tool(s) to get
   * external knowledge to answer the prompt. Retrieval results are presented to the model for
   * generation. This field is not supported in Gemini API.
   */
  val retrieval: Retrieval? = null,

  /**
   * Optional. Tool to support the model interacting directly with the computer. If enabled, it
   * automatically populates computer-use specific Function Declarations.
   */
  val computerUse: ComputerUse? = null,

  /**
   * Optional. FileSearch tool type. Tool to retrieve knowledge from Semantic Retrieval corpora.
   * This field is not supported in Vertex AI.
   */
  val fileSearch: FileSearch? = null,

  /**
   * Optional. GoogleSearch tool type. Tool to support Google Search in Model. Powered by Google.
   */
  val googleSearch: GoogleSearch? = null,

  /**
   * Optional. Tool that allows grounding the model's response with geospatial context related to
   * the user's query.
   */
  val googleMaps: GoogleMaps? = null,

  /** Optional. CodeExecution tool type. Enables the model to execute code as part of generation. */
  val codeExecution: ToolCodeExecution? = null,

  /**
   * Optional. Tool to support searching public web data, powered by Vertex AI Search and Sec4
   * compliance. This field is not supported in Gemini API.
   */
  val enterpriseWebSearch: EnterpriseWebSearch? = null,

  /**
   * Optional. Function tool type. One or more function declarations to be passed to the model along
   * with the current user query. Model may decide to call a subset of these functions by populating
   * FunctionCall in the response. User should provide a FunctionResponse for each function call in
   * the next turn. Based on the function responses, Model will generate the final response back to
   * the user. Maximum 512 function declarations can be provided.
   */
  val functionDeclarations: List<FunctionDeclaration>? = null,

  /** Optional. Specialized retrieval tool that is powered by Google Search. */
  val googleSearchRetrieval: GoogleSearchRetrieval? = null,

  /**
   * Optional. If specified, Vertex AI will use Parallel.ai to search for information to answer user
   * queries. The search results will be grounded on Parallel.ai and presented to the model for
   * response generation. This field is not supported in Gemini API.
   */
  val parallelAiSearch: ToolParallelAiSearch? = null,

  /** Optional. Tool to support URL context retrieval. */
  val urlContext: UrlContext? = null,

  /** Optional. MCP Servers to connect to. This field is not supported in Vertex AI. */
  val mcpServers: List<McpServer>? = null,

  /**
   * Optional. Uses Exa.ai to search for information to answer user queries. The search results will
   * be grounded on Exa.ai and presented to the model for response generation. This field is not
   * supported in Gemini API.
   */
  val exaAiSearch: ToolExaAiSearch? = null,
)
