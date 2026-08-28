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

import com.google.genai.kotlin.types.FunctionCall
import com.google.genai.kotlin.types.FunctionDeclaration
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part
import com.google.genai.kotlin.types.Tool
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.serializer

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
 * A function you give to the model, and the handler that runs when the model asks for it.
 *
 * Build one with [callableFunction], then pass it to [AutomaticFunctionCalling].
 */
class CallableFunction
internal constructor(
  /** What the model is told about this function: its name, description and argument schema. */
  val declaration: FunctionDeclaration,
  internal val handler: suspend (JsonObject) -> JsonElement,
)

/**
 * Makes a [CallableFunction] from a function that takes no parameters.
 *
 * ```
 * fun currentTime(): String = ...
 *
 * val getTime = callableFunction("current_time", handler = ::currentTime)
 * ```
 *
 * @param name what the model refers to this function by.
 * @param description what the function does, so the model knows when to ask for it.
 * @param handler the function to run when the model asks for it.
 */
inline fun <reified R> callableFunction(
  name: String,
  description: String? = null,
  noinline handler: suspend () -> R,
): CallableFunction =
  buildFromNamedParameters(name, description, emptyList(), emptyList(), serializer<R>()) {
    handler()
  }

/**
 * Makes a [CallableFunction] from a function that takes one parameter.
 *
 * ```
 * fun getWeather(city: String): String = ...
 *
 * val weather = callableFunction("get_weather", paramName = "city", handler = ::getWeather)
 * ```
 *
 * @param name what the model refers to this function by.
 * @param description what the function does, so the model knows when to ask for it.
 * @param paramName the name the model sends the argument under. Required, because a function's own
 *   parameter names cannot be read back at runtime.
 * @param handler the function to run when the model asks for it.
 */
inline fun <reified A, reified R> callableFunction(
  name: String,
  description: String? = null,
  paramName: String,
  noinline handler: suspend (A) -> R,
): CallableFunction =
  buildFromNamedParameters(
    name,
    description,
    listOf(paramName),
    listOf(serializer<A>()),
    serializer<R>(),
  ) { arguments ->
    handler(arguments[0] as A)
  }

/**
 * Makes a [CallableFunction] from a function that takes two parameters.
 *
 * ```
 * fun getForecast(city: String, days: Int): String = ...
 *
 * val forecast =
 *   callableFunction("get_forecast", paramNames = listOf("city", "days"), handler = ::getForecast)
 * ```
 *
 * @param name what the model refers to this function by.
 * @param description what the function does, so the model knows when to ask for it.
 * @param paramNames the names the model sends the arguments under, in the order the function
 *   declares them.
 * @param handler the function to run when the model asks for it.
 */
inline fun <reified A, reified B, reified R> callableFunction(
  name: String,
  description: String? = null,
  paramNames: List<String>,
  noinline handler: suspend (A, B) -> R,
): CallableFunction =
  buildFromNamedParameters(
    name,
    description,
    paramNames,
    listOf(serializer<A>(), serializer<B>()),
    serializer<R>(),
  ) { arguments ->
    handler(arguments[0] as A, arguments[1] as B)
  }

/**
 * Makes a [CallableFunction] from a function that takes three parameters.
 *
 * For a function with more parameters than this, take a single `@Serializable` class holding them
 * instead.
 *
 * ```
 * fun route(from: String, to: String, metric: Boolean): String = ...
 *
 * val plan =
 *   callableFunction(
 *     "plan_route",
 *     paramNames = listOf("from", "to", "metric"),
 *     handler = ::route,
 *   )
 * ```
 *
 * @param name what the model refers to this function by.
 * @param description what the function does, so the model knows when to ask for it.
 * @param paramNames the names the model sends the arguments under, in the order the function
 *   declares them.
 * @param handler the function to run when the model asks for it.
 */
inline fun <reified A, reified B, reified C, reified R> callableFunction(
  name: String,
  description: String? = null,
  paramNames: List<String>,
  noinline handler: suspend (A, B, C) -> R,
): CallableFunction =
  buildFromNamedParameters(
    name,
    description,
    paramNames,
    listOf(serializer<A>(), serializer<B>(), serializer<C>()),
    serializer<R>(),
  ) { arguments ->
    handler(arguments[0] as A, arguments[1] as B, arguments[2] as C)
  }

/**
 * Makes a [CallableFunction] from a function that takes one `@Serializable` class of parameters.
 *
 * The class's fields are the parameters, so no names are needed. Use [Describe] to document them.
 *
 * ```
 * @Serializable
 * data class PlanTripArgs(
 *   @Describe("Departure city.") val origin: String,
 *   @Describe("Number of nights to stay.") val nights: Int = 2,
 * )
 *
 * val planTrip = callableFunction("plan_trip") { args: PlanTripArgs ->
 *   "Trip from ${args.origin} for ${args.nights} nights"
 * }
 * ```
 *
 * @param name what the model refers to this function by.
 * @param description what the function does, so the model knows when to ask for it.
 * @param handler the function to run when the model asks for it.
 */
inline fun <reified A, reified R> callableFunction(
  name: String,
  description: String? = null,
  noinline handler: suspend (A) -> R,
): CallableFunction =
  buildFromParameterClass(name, description, serializer<A>(), serializer<R>(), handler)

/**
 * The functions the model may ask for during a chat, and the limits on running them.
 *
 * Passing this is what turns automatic function calling on; there is no separate flag. Leave it out
 * and the model's function calls are returned to you to handle yourself.
 *
 * ```
 * val chat = client.chats.create(
 *   model = "gemini-3.6-flash",
 *   automaticFunctionCalling = AutomaticFunctionCalling(getWeather, getForecast),
 * )
 * ```
 *
 * @param functions a list of [CallableFunction]s the model may ask for. Names must be unique.
 * @param maximumRemoteCalls how many requests one turn may send before giving up. Your first
 *   message counts, so it takes at least two requests for AFC: your initial message, and the
 *   FunctionResponse message we send for you after executing your handler. When the limit is
 *   reached, handlers are no longer called and you get the model's FunctionCall back.
 * @param runFunctionsInParallel whether several functions asked for in one response may run at the
 *   same time. Off by default; turn it on only if your handlers are safe to overlap, which the SDK
 *   cannot check for you.
 */
class AutomaticFunctionCalling(
  val functions: List<CallableFunction>,
  val maximumRemoteCalls: Int = 10,
  val runFunctionsInParallel: Boolean = false,
) {
  constructor(vararg functions: CallableFunction) : this(functions.toList())

  /** The functions to dispatch on, keyed by the name the model uses. */
  internal val byName: Map<String, CallableFunction> = functions.associateBy {
    it.declaration.name.orEmpty()
  }

  init {
    require(maximumRemoteCalls > 0) {
      "maximumRemoteCalls must be positive, but was $maximumRemoteCalls. Leave " +
        "automaticFunctionCalling unset to handle the model's function calls yourself."
    }
    // Two functions under one name would leave the model unable to say which it meant, and only
    // one of them reachable.
    require(byName.size == functions.size) {
      "Two functions share a name: ${functions.mapNotNull { it.declaration.name }.sorted()}."
    }
  }
}

/** Builds a [CallableFunction] whose parameters are named one by one. */
@PublishedApi
internal fun buildFromNamedParameters(
  name: String,
  description: String?,
  paramNames: List<String>,
  arguments: List<KSerializer<*>>,
  result: KSerializer<*>,
  handler: suspend (List<Any?>) -> Any?,
): CallableFunction {
  require(paramNames.size == arguments.size) {
    "Function \"$name\" takes ${arguments.size} arguments, but ${paramNames.size} names were " +
      "given: $paramNames."
  }
  // A repeated name would collapse two parameters into one property, so the model would send one
  // value and the handler would receive it twice.
  require(paramNames.toSet().size == paramNames.size) {
    "Function \"$name\" was given the same parameter name twice: $paramNames."
  }

  val schema =
    buildParametersSchema(
      paramNames.mapIndexed { i, paramName ->
        paramName to jsonSchemaOf(arguments[i].descriptor, inlineRoot = false)
      }
    )

  return CallableFunction(
    FunctionDeclaration(
      name = name,
      description = description,
      parametersJsonSchema = schema,
      responseJsonSchema = buildResponseSchema(result),
    )
  ) { json ->
    val decoded = paramNames.mapIndexed { i, paramName ->
      decodeArgument(json, paramName, name, arguments[i])
    }
    encodeResult(result, handler(decoded))
  }
}

/** Builds a [CallableFunction] whose parameters come from the fields of one class. */
@PublishedApi
internal fun <A, R> buildFromParameterClass(
  name: String,
  description: String?,
  argument: KSerializer<A>,
  result: KSerializer<R>,
  handler: suspend (A) -> R,
): CallableFunction {
  // The class stands in for the whole parameter list, so its schema is already the right shape.
  // Anything that is not an object would produce arguments the model cannot name.
  val schema = jsonSchemaOf(argument.descriptor)
  require(schema["type"] == JsonPrimitive("object")) {
    "Function \"$name\" takes a single argument that is not a @Serializable class, so its " +
      "parameter has no name the model can use. Pass `paramName` to name it, or take a " +
      "@Serializable class holding the arguments."
  }

  return CallableFunction(
    FunctionDeclaration(
      name = name,
      description = description,
      parametersJsonSchema = schema,
      responseJsonSchema = buildResponseSchema(result),
    )
  ) { arguments ->
    handlerJson.encodeToJsonElement(
      result,
      handler(handlerJson.decodeFromJsonElement(argument, arguments)),
    )
  }
}

/** Converts between the JSON on the wire and the types a handler declares. */
internal val handlerJson: Json = Json {
  ignoreUnknownKeys = true
  encodeDefaults = true
}

/** Combines the schema of each named parameter into the object the model fills in. */
// A parameter's own $defs are lifted to the root, because a $ref resolves from the document root
// and would dangle once the schema becomes a property.
internal fun buildParametersSchema(named: List<Pair<String, JsonObject>>): JsonObject {
  val defs = LinkedHashMap<String, JsonElement>()
  val properties = LinkedHashMap<String, JsonElement>()
  for ((name, schema) in named) {
    val own = schema["\$defs"]
    if (own is JsonObject) {
      defs.putAll(own)
    }
    properties[name] = JsonObject(schema.filterKeys { it != "\$defs" })
  }

  val out = LinkedHashMap<String, JsonElement>()
  out["type"] = JsonPrimitive("object")
  out["properties"] = JsonObject(properties)
  if (named.isNotEmpty()) {
    out["required"] = JsonArray(named.map { JsonPrimitive(it.first) })
  }
  if (defs.isNotEmpty()) {
    out["\$defs"] = JsonObject(defs)
  }
  return JsonObject(out)
}

/** Describes what a function returns, or null when it returns nothing. */
internal fun buildResponseSchema(result: KSerializer<*>): JsonObject? =
  if (result.descriptor.serialName == "kotlin.Unit") null else jsonSchemaOf(result.descriptor)

/** Encodes a handler's return value for sending back to the model. */
// The serializer and the value come from the same reified call site, so they always agree.
@Suppress("UNCHECKED_CAST")
internal fun encodeResult(result: KSerializer<*>, value: Any?): JsonElement =
  handlerJson.encodeToJsonElement(result as KSerializer<Any?>, value)

/** Reads one argument the model sent, as the type its handler declares. */
internal fun decodeArgument(
  arguments: JsonObject,
  paramName: String,
  name: String,
  serializer: KSerializer<*>,
): Any? {
  val element =
    arguments[paramName]
      ?: throw IllegalArgumentException(
        "The model called \"$name\" without the argument \"$paramName\". It sent: ${arguments.keys}."
      )
  return handlerJson.decodeFromJsonElement(serializer, element)
}

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
internal fun jsonSchemaOf(descriptor: SerialDescriptor, inlineRoot: Boolean = true): JsonObject {
  val defs = Defs()
  val root = schemaOf(descriptor, description = null, defs = defs, inlineObject = inlineRoot)
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

/**
 * Throws if [config] declares functions by hand while [afc] is also running them.
 *
 * The two cannot be combined: a declaration with no handler behind it would reach the model, which
 * would then ask for something nothing can run. Every other kind of tool is executed by the backend
 * and composes with automatic function calling as usual.
 */
internal fun requireNoRawFunctionDeclarations(
  config: GenerateContentConfig?,
  afc: AutomaticFunctionCalling?,
) {
  if (afc == null) {
    return
  }
  val declared = config?.tools.orEmpty().flatMap { it.functionDeclarations.orEmpty() }
  require(declared.isEmpty()) {
    "Cannot combine automatic function calling with function declarations written by hand: " +
      "${declared.mapNotNull { it.name }}. Either wrap them with callableFunction(), or drop " +
      "automaticFunctionCalling and handle every call yourself."
  }
}

/** Returns [config] with [afc]'s functions declared, so the model knows it can ask for them. */
internal fun configWithFunctions(
  afc: AutomaticFunctionCalling,
  config: GenerateContentConfig?,
): GenerateContentConfig {
  val base = config ?: GenerateContentConfig()
  val declared = Tool(functionDeclarations = afc.functions.map { it.declaration })
  return base.copy(tools = base.tools.orEmpty() + declared)
}

/** Runs every function the model asked for, returning the responses in the order it asked. */
// Both captures are immutable: AutomaticFunctionCalling exposes only vals over a frozen list and
// map, and FunctionCall is a serialization data class. What is genuinely not checkable is the
// handlers, which is the contract runFunctionsInParallel makes the caller accept.
@Suppress("UnsafeCoroutineCrossing")
internal suspend fun runFunctionCalls(
  afc: AutomaticFunctionCalling,
  calls: List<FunctionCall>,
): List<Part> =
  if (afc.runFunctionsInParallel) {
    // awaitAll keeps the results in call order however the handlers interleave, and cancelling the
    // turn cancels all of them.
    coroutineScope { calls.map { async { runFunctionCall(afc, it) } }.awaitAll() }
  } else {
    calls.map { runFunctionCall(afc, it) }
  }

/** Runs one call, turning a failure into an error the model can read rather than an exception. */
private suspend fun runFunctionCall(afc: AutomaticFunctionCalling, call: FunctionCall): Part {
  val name = call.name.orEmpty()
  val response =
    try {
      val function = afc.byName[name]
      if (function == null) {
        mapOf("error" to JsonPrimitive("Unknown function: $name"))
      } else {
        mapOf("result" to function.handler(JsonObject(call.args.orEmpty())))
      }
    } catch (cancelled: CancellationException) {
      // Not redundant with the rethrow: CancellationException is an Exception, so without this
      // clause the broader one below would swallow it, report a cancelled turn to the model as a
      // failed function, and carry on looping inside a scope that is already dead.
      throw cancelled
    } catch (failure: Exception) {
      mapOf("error" to JsonPrimitive(failure.message ?: failure.toString()))
    }
  // Carrying the id back is what pairs this response with its call when several were asked for at
  // once, where the name alone is ambiguous.
  return Part(functionResponse = FunctionResponse(id = call.id, name = name, response = response))
}
