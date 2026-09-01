# Google Gen AI Kotlin SDK

--------
**Documentation:** https://googleapis.github.io/kotlin-genai/
--------

The Google Gen AI Kotlin SDK provides an idiomatic Kotlin interface for
developers to integrate Google's generative models into their applications. It
supports both the [Gemini Developer API](https://ai.google.dev/gemini-api/docs)
and the
[Gemini Enterprise Agent Platform API](https://cloud.google.com/vertex-ai/generative-ai/docs/learn/overview)
(formerly Vertex AI).

> [!WARNING]
> **Mobile Security: API Keys & Cloud Credentials**
>
> While this SDK supports Android targets via Kotlin Multiplatform, **we
> strongly discourage embedding API keys or Google Cloud IAM credentials (such
> as Service Account JSON keys or OAuth tokens)** directly into public mobile
> client applications due to the risk of credential theft and cloud project
> compromise via reverse engineering.
>
> * **For public mobile apps** connecting directly to generative models from
> client devices, we strongly recommend using **[Firebase AI
> Logic](https://firebase.google.com/docs/ai-logic)** with **Firebase App
> Check** enabled. Firebase provides secure client-side authentication and
> device integrity protection without exposing your Google Cloud project
> credentials.
> * **Use this SDK on Android** only when connecting through your own secure
> backend service, or for internal/prototype applications where credentials are
> securely managed.

## Requirements

The SDK requires the following minimum platform versions:

* **Java**: JDK 17
* **Android**: API level 21 (Android 5.0)

## Installation

[//]: # ({x-version-update-start:google-genai-kotlin:released})
### Kotlin Multiplatform (KMP)

For multiplatform projects, add the dependency to your `commonMain` source set:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("com.google.genai:google-genai-kotlin:0.5.0")
        }
    }
}
```

### Gradle

Add the dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("com.google.genai:google-genai-kotlin:0.5.0")
}
```

### Maven

For Maven projects (JVM only), use the `-jvm` suffixed artifact:

```xml
<dependency>
    <groupId>com.google.genai</groupId>
    <artifactId>google-genai-kotlin-jvm</artifactId>
    <version>0.5.0</version>
</dependency>
```
[//]: # ({x-version-update-end})

## Getting Started

### Create a client

The `Client` class is the main entry point for the SDK. You can initialize it to
use either the Gemini Developer API or the Gemini Enterprise Agent Platform API.

#### 1. Using Environment Variables (Recommended)

The client can automatically pick up configuration from environment variables.

**For Gemini Developer API:** Set the `GOOGLE_API_KEY`.

```bash
export GOOGLE_API_KEY="your-api-key"
```

**For Gemini Enterprise Agent Platform API:** Set `GOOGLE_GENAI_USE_ENTERPRISE`,
`GOOGLE_CLOUD_PROJECT`, and `GOOGLE_CLOUD_LOCATION`.

```bash
export GOOGLE_GENAI_USE_ENTERPRISE=true
export GOOGLE_CLOUD_PROJECT="your-project-id"
export GOOGLE_CLOUD_LOCATION="us-central1"
```

After setting the variables, initialize the client:

```kotlin
import com.google.genai.kotlin.Client

val client = Client()
```

#### 2. Explicit Configuration

You can also pass the configuration explicitly when creating the client.

**For Gemini Developer API:**

```kotlin
val client = Client(apiKey = "your-api-key")
```

**For Gemini Enterprise Agent Platform API:**

```kotlin
val client = Client(
    project = "your-project-id",
    location = "us-central1",
    enterprise = true
)
```

## Usage

### Generate Content

Use `generateContent` for simple text generation. This is a suspending function
and should be called within a coroutine scope.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Use .use to automatically close the client and release resources
    Client().use { client ->
        val response = client.models.generateContent(
            model = "gemini-3.5-flash",
            text = "Why is the sky blue?"
        )

        val text = response.text
        println(text)
    }
}
```

`response.text` concatenates the text parts of the first candidate. A few other
accessors read the same first candidate, each returning `null` when the response
does not carry that kind of content:

| Accessor | Returns |
| --- | --- |
| `response.text` | the concatenated text parts |
| `response.parts` | all the parts in the response |
| `response.functionCalls` | the function calls the model requested |
| `response.finishReason` | why the model stopped, once it has |
| `response.groundingMetadata` | the sources and queries behind a grounded answer |
| `response.executableCode` | code the model asked to have run |
| `response.codeExecutionResult` | the output of running that code |

### Stream Generated Content

Use `generateContentStream` to get a streaming response (using Kotlin `Flow`)
for faster perceived latency.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        println("Streaming response: ")

        val responseFlow = client.models.generateContentStream(
            model = "gemini-3.5-flash",
            text = "Write a short story about a robot."
        )

        responseFlow.collect { response ->
            val chunkText = response.text
            if (chunkText != null) {
                print(chunkText)
            }
        }
        println() // End with a newline
    }
}
```

### Chat

Use `chats.create` for a multi-turn conversation. The session keeps the history
and sends it with each new message, so the model has the earlier turns for
context.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        val chat = client.chats.create(model = "gemini-3.6-flash")

        val first = chat.sendMessage("My favourite colour is blue.")
        println(first.text)

        // Answering this needs the first turn, which the session sends for you.
        val second = chat.sendMessage("What is my favourite colour?")
        println(second.text)

        // Two entries per exchange: the message and the response.
        println("History entries: ${chat.getHistory().size}")
    }
}
```

Pass `history` to `create` to resume an earlier conversation, and `config` to
apply a `GenerateContentConfig` to every turn. A config passed to an individual
`sendMessage` replaces the session config for that turn rather than merging with
it.

### Stream Chat Responses

`sendMessageStream` returns the response as a `Flow`. Nothing is sent until the
flow is collected, and the turn is added to the history once the flow completes,
so collect each turn fully before starting the next one.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        val chat = client.chats.create(model = "gemini-3.6-flash")

        chat.sendMessageStream("Tell me a two sentence story about a robot.")
            .collect { chunk -> chunk.text?.let { print(it) } }
        println()
    }
}
```

Each flow carries one turn and is collected once. Collecting it again after the
turn has completed throws `IllegalStateException` rather than quietly sending
the
message a second time, so collect into a list if you need to read the response
more than once:

```kotlin
val chunks = chat.sendMessageStream("Tell me a story.").toList()
val text = chunks.joinToString("") { it.text ?: "" }
```

Streaming keeps every chunk it received, so a ten chunk response adds ten model
turns to the history rather than one.

`getHistory()` returns every turn. `getHistory(curated = true)` returns only the
turns that will be sent to the model on the next request, leaving out any whose
response came back empty or blocked.

### Automatic Function Calling

Give the model functions you want to use, and the SDK runs them for you when the
model asks for one and sends the result back, so you no longer have to execute
functions yourself. Passing `automaticFunctionCalling` is what turns this on.

```kotlin
import com.google.genai.kotlin.AutomaticFunctionCalling
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.callableFunction
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // A mocked function to keep the example short. Use the real one you want here.
    val getWeather = callableFunction("get_weather", paramName = "city") { city: String ->
        "18 degrees and sunny in $city"
    }

    Client().use { client ->
        val chat = client.chats.create(
            model = "gemini-3.6-flash",
            automaticFunctionCalling = AutomaticFunctionCalling(getWeather),
        )

        // One call, several requests: the model asks for get_weather, the SDK runs it and sends
        // the result back, and this returns once the model has an answer.
        println(chat.sendMessage("What is the weather in Zurich?").text)
    }
}
```

There are overloads taking `paramNames` for functions of two or three
parameters, and one for a function that takes no parameters at all.

The whole interaction is recorded as a single turn, so the history holds your
message, the model's function call, the result the SDK sent back on your behalf,
and the model's answer.

#### Describing parameters with a class

For a complex function, take a `@Serializable` class instead. Its fields are the
parameters, so they need no names, and `@Describe` documents them for the model.

```kotlin
import com.google.genai.kotlin.Describe
import com.google.genai.kotlin.callableFunction
import kotlinx.serialization.Serializable

@Serializable
data class PlanTripArgs(
    @Describe("City the trip starts from.") val origin: String,
    @Describe("City the trip ends in.") val destination: String,
    @Describe("Number of nights to stay.") val nights: Int = 2,
)

val planTrip = callableFunction("plan_trip") { trip: PlanTripArgs ->
    "Take the train from ${trip.origin} to ${trip.destination}"
}
```

#### Advanced AFC Configuration

```kotlin
AutomaticFunctionCalling(
    functions = listOf(getWeather, planTrip),
    maximumRemoteCalls = 10,
    runFunctionsInParallel = false,
)
```

`maximumRemoteCalls` caps how many requests one turn may send before giving up.
Your first message counts, so it takes at least two requests for AFC: your
initial message, and the FunctionResponse message we send for you after
running your function. When the limit is reached, functions are no longer called
and you get the model's FunctionCall back.

`runFunctionsInParallel` lets several functions asked for in one response run at
the same time. It is off by default; turn it on only if your functions are safe
to overlap, which the SDK cannot check for you.

A function that throws is reported to the model as a failed function rather than
to you, so the model can recover or explain. Cancellation is the exception and
is rethrown.

#### Streaming

`sendMessageStream` works the same way, with one flow spanning the whole
exchange however many requests it takes. Everything the model sends is emitted,
function calls included; the results this side sends back are not, since they
are you answering the model rather than the model speaking.

```kotlin
chat.sendMessageStream("What is the weather in Zurich?").collect { chunk ->
    chunk.functionCalls?.forEach { println("model asked for ${it.name}") }
    chunk.text?.let { print(it) }
}
```

#### Handling calls yourself

Leave `automaticFunctionCalling` out and nothing is run for you: declare the
functions as `FunctionDeclaration`s in `config.tools` and the model's calls come
back in `response.functionCalls` for you to answer. The two cannot be combined —
passing hand-written function declarations alongside `automaticFunctionCalling`
throws `IllegalArgumentException`.

### Advanced Configuration

You can pass a `GenerateContentConfig` to customize the request, such as setting
system instructions or temperature.

```kotlin
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part

val config = GenerateContentConfig(
    systemInstruction = Content.fromText("You are a helpful assistant."),
    temperature = 0.5,
    maxOutputTokens = 1024
)

val response = client.models.generateContent(
    model = "gemini-3.5-flash",
    text = "What is your name?",
    config = config
)
```

### Retries

By default the SDK makes a single attempt per request. Pass `HttpRetryOptions` to
retry transient failures -- a retryable HTTP status or a transport error such as a
timeout -- with exponential backoff and jitter.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.HttpOptions
import com.google.genai.kotlin.types.HttpRetryOptions

val client = Client(
    httpOptions = HttpOptions(
        retryOptions = HttpRetryOptions(
            attempts = 5,          // Including the initial call.
            initialDelay = 1.0,    // Seconds.
            maxDelay = 60.0,       // Seconds.
            expBase = 2.0,
            jitter = 1.0,
            httpStatusCodes = listOf(408, 429, 500, 502, 503, 504)
        )
    )
)
```

Every field is optional and falls back to the value shown above. Setting
`httpStatusCodes` replaces the default list rather than adding to it.

The same options can be set per request, which overrides the client-level value:

```kotlin
val response = client.models.generateContent(
    model = "gemini-3.5-flash",
    text = "What is your name?",
    config = GenerateContentConfig(
        httpOptions = HttpOptions(retryOptions = HttpRetryOptions(attempts = 3))
    )
)
```

### Proxy Options

If your environment requires connecting through a proxy, you can configure it
by passing `ProxyOptions` inside `ClientOptions` when creating the client. The
SDK supports `HTTP`, `SOCKS`, and `DIRECT` (no proxy) connection types, along
with basic proxy authentication.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.ClientOptions
import com.google.genai.kotlin.types.ProxyOptions
import com.google.genai.kotlin.types.ProxyType

val proxyOptions = ProxyOptions(
    host = "proxy.example.com",
    port = 8080,
    type = ProxyType.HTTP, // Optional: defaults to HTTP
    username = "proxy-user", // Optional: credentials if authentication is required
    password = "proxy-password"
)

val client = Client(
    clientOptions = ClientOptions(proxyOptions = proxyOptions)
)
```

Setting `type` to `ProxyType.DIRECT` enforces a direct connection, bypassing any
system-level proxy settings.

### Custom HTTP Client

If you need advanced control over HTTP transport—such as configuring custom timeouts, interceptors, SSL settings, connection pools, or supplying your own Ktor `HttpClientEngine`—you can pass a `customHttpClient` inside `ClientOptions`.

When `customHttpClient` is provided, it takes precedence over any `proxyOptions` configured in `ClientOptions`.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.ClientOptions
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

// Create a customized Ktor HttpClientEngine
val customEngine = OkHttp.create {
    config {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        // Add custom interceptors, connection pools, authenticator, etc.
    }
}

val client = Client(
    clientOptions = ClientOptions(customHttpClient = customEngine)
)
```

### Live API

The Gemini Live API allows for real-time, bidirectional interaction with
Gemini models over a persistent WebSocket connection, supporting audio, text,
and function calling.

#### Overview

Key concepts:
* **Session**: A persistent WebSocket connection to the model managed via
  `client.live.connect(...)`.
* **Config**: Settings for modalities (text, audio transcription), tools, and
  system instructions via `LiveConnectConfig`.
* **Real-time Input**: Asynchronously streaming text, audio, or video frames to
  the session.

#### Connecting to the Live API

Start a Live API session using `client.live.connect(model, config)`. The
returned session object manages the underlying WebSocket connection.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.AudioTranscriptionConfig
import com.google.genai.kotlin.types.LiveConnectConfig
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Optional. Configure the Live session (e.g. audio transcription).
    val config = LiveConnectConfig(
        outputAudioTranscription = AudioTranscriptionConfig()
    )
    val model = if (client.enterprise) "gemini-live-2.5-flash-native-audio"
            else "gemini-3.1-flash-live-preview"

    client.live.connect(model, config).use { session ->
        println("Connected to Live session!")
        // Send and receive content here...
    }
}
```

#### Sending text

Text messages can be sent to an active session using
`sendRealtimeInput(text = ...)`.

> [!NOTE]
> For **Gemini 3.1**, `sendClientContent` is only supported for seeding initial
> context history (requiring `initialHistoryInClientContent` in session config).
> Use `sendRealtimeInput` to send text messages during the conversation. For
> **Gemini 2.5**, `sendClientContent` is supported throughout the conversation.

```kotlin
session.sendRealtimeInput(text = "Hello, how are you?")
```

#### Sending audio

Audio chunks (such as 16kHz, 16-bit mono raw PCM data) are sent using
`sendRealtimeInput(audio = Blob(...))`.

```kotlin
import com.google.genai.kotlin.types.Blob

val pcmBytes: ByteArray = ... // Raw PCM audio data (16kHz, 16-bit, mono)

session.sendRealtimeInput(
    audio = Blob(data = pcmBytes, mimeType = "audio/pcm;rate=16000")
)
// Signal the end of an audio stream input (most time the model auto-detects the
// end of audio so this is not needed)
session.sendRealtimeInput(audioStreamEnd = true)
```

#### Receiving audio

The model streams audio responses back as raw binary data chunks in
`serverMessage.data`.

```kotlin
session.receive().collect { serverMessage ->
    serverMessage.data?.let { audioData ->
        println("Received model audio chunk: ${audioData.size} bytes")
        // Process or play audio bytes
    }
}
```

#### Receiving text

Transcriptions for both user input and model output are available in
`serverContent` when transcription is enabled through `LiveConnectConfig`.

```kotlin
session.receive().collect { serverMessage ->
    serverMessage.serverContent?.run {
        inputTranscription?.text?.let { println("[User]: $it") }
        outputTranscription?.text?.let { println("[Model]: $it") }

        if (turnComplete == true || interrupted == true) {
            println("[Turn Complete]")
            session.closeSession()
        }
    }
}
```

#### Handling tool calls (Function calling)

When the model requests a tool call, a `toolCall` is received in
`serverMessage`. Execute the function and return the response using
`sendToolResponse`.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.FunctionDeclaration
import com.google.genai.kotlin.types.FunctionResponse
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.Schema
import com.google.genai.kotlin.types.Tool
import com.google.genai.kotlin.types.Type
import kotlinx.serialization.json.JsonPrimitive

val getWeatherDeclaration = FunctionDeclaration(
    name = "GetWeather",
    description = "return the real time weather of the location",
    parameters = Schema(
        type = Type.OBJECT,
        properties = mapOf("location" to Schema(type = Type.STRING)),
        required = listOf("location")
    )
)

val config = LiveConnectConfig(
    tools = listOf(Tool(functionDeclarations = listOf(getWeatherDeclaration)))
)

client.live.connect(model, config).use { session ->
    session.sendRealtimeInput(text = "What is the weather in Seattle?")

    session.receive().collect { serverMessage ->
        serverMessage.toolCall?.let { toolCall ->
            val functionResponses = toolCall.functionCalls?.map { call ->
                FunctionResponse(
                    id = call.id,
                    name = call.name,
                    response = mapOf("temperature" to JsonPrimitive("72F"))
                )
            }
            if (functionResponses != null) {
                session.sendToolResponse(functionResponses)
            }
        }
    }
}
```

### Embed Content

Use `embedContent` to generate vector embeddings for text or multimodal content.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.EmbedContentConfig
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        // Generate embedding for text
        val response = client.models.embedContent(
            model = "gemini-embedding-2",
            text = "What is the capital of France?"
        )

        val embeddings = response.embeddings
        if (embeddings != null && embeddings.isNotEmpty()) {
            val vector = embeddings[0].values
            println("Embedding vector size: ${vector?.size}")
            println("First 5 values: ${vector?.take(5)}")
        }
    }
}
```

To configure task type or output dimensionality:

```kotlin
val config = EmbedContentConfig(
    outputDimensionality = 16,
    taskType = "RETRIEVAL_DOCUMENT",
    title = "Document Title"
)

val response = client.models.embedContent(
    model = "gemini-embedding-2",
    text = "What is the capital of France?",
    config = config
)
```

#### Multimodal Embedding

You can generate embeddings for multimodal content (text and images).

**Using Inline Image Bytes (Works on both Gemini Developer API and Gemini
Enterprise Agent Platform):**

```kotlin
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.Part

val imageBytes: ByteArray = ... // Load your image bytes

val response = client.models.embedContent(
    model = "gemini-embedding-2",
    contents = listOf(
        Content(
            parts = listOf(
                Part(text = "Similar things to the following image:"),
                Part(inlineData = Blob(mimeType = "image/png", data = imageBytes))
            )
        )
    )
)
```

**Using Google Cloud Storage (Gemini Enterprise Agent Platform only):**

```kotlin
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.FileData
import com.google.genai.kotlin.types.Part

val response = client.models.embedContent(
    model = "gemini-embedding-2",
    contents = listOf(
        Content(
            parts = listOf(
                Part(text = "Similar things to the following image:"),
                Part(fileData = FileData(fileUri = "gs://your-bucket/image.png", mimeType = "image/png"))
            )
        )
    )
)
```

### Files

The SDK provides methods for managing files, which is useful for uploading media
files or datasets to the Gemini Developer API.

> [!NOTE] The files API is only supported in the Gemini Developer API.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.UploadFileConfig
import com.google.genai.kotlin.types.ListFilesConfig
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        // Upload a file using a ByteArray
        // This is dummy data; replace with your actual file data.
        val fileData = "Dummy file content".encodeToByteArray()
        val file = client.files.upload(
            byteArray = fileData,
            config = UploadFileConfig(
                mimeType = "text/plain",
                displayName = "my-file.txt"
            )
        )
        println("Uploaded file: ${file.name}")

        // Get a file
        val retrievedFile = client.files.get(name = file.name!!)
        println("File state: ${retrievedFile.state}")

        // List files
        val pager = client.files.list(config = ListFilesConfig(pageSize = 10))
        pager.forEach { f ->
            println("Found file: ${f.name}")
        }

        // Delete a file
        client.files.delete(name = file.name!!)
        println("Deleted file: ${file.name}")

        // Download a generated file (for files that have a downloadUri)
        // val channel = client.files.download(retrievedFile)
        // You can then read from this ByteReadChannel
    }
}
```

#### Register Files

You can register a file using a URI (e.g. Google Cloud Storage). This targets the Gemini Developer API and needs Google Cloud credentials (OAuth) *in addition to* your API key: the client still authenticates with the API key, while the service uses the OAuth token to read the object out of your bucket. Pass the credentials to `registerFiles` only — the `Client` constructor treats credentials and API keys as mutually exclusive.

The credentials must carry the `devstorage.read_only` scope, or the call fails with `403 ACCESS_TOKEN_SCOPE_INSUFFICIENT`. Note that `createScoped` only applies to service account credentials; end-user credentials from `gcloud auth application-default login` have fixed scopes, so request them at login time:

```
gcloud auth application-default login \
    --scopes="https://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/devstorage.read_only"
```

```kotlin
import com.google.genai.kotlin.Client
import com.google.auth.oauth2.GoogleCredentials
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // Application Default Credentials provide the OAuth token used to read from GCS.
    val credentials = GoogleCredentials.getApplicationDefault()
        .createScoped(
            listOf(
                "https://www.googleapis.com/auth/cloud-platform",
                "https://www.googleapis.com/auth/devstorage.read_only"
            )
        )

    // The client authenticates with GEMINI_API_KEY / GOOGLE_API_KEY from the environment.
    Client().use { client ->
        val gcsUri = "gs://cloud-samples-data/generative-ai/image/a-man-and-a-dog.png"
        val response = client.files.registerFiles(
            credentials = credentials,
            uris = listOf(gcsUri)
        )

        val registeredFile = response.files?.firstOrNull()
        println("Registered file: ${registeredFile?.name}")
    }
}
```

### Context Caching

You can cache content to reduce latency and cost for repetitive requests.
(Note: Listing cached contents is coming soon.)

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.Blob
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.CreateCachedContentConfig
import com.google.genai.kotlin.types.Part
import kotlin.time.Duration.Companion.minutes
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        // This is dummy data; use your own bytes data or file URI instead.
        val bytesData = Part(
            inlineData = Blob(
                mimeType = "text/plain",
                data = "Hello Gemini ".repeat(10_000).encodeToByteArray(),
            )
        )

        val config = CreateCachedContentConfig(
            systemInstruction = Content.fromText("You are an expert."),
            ttl = 60.minutes,
            contents = listOf(Content(role = "user", parts = listOf(bytesData)))
        )

        // Create cached content
        val cachedContent = client.caches.create(model = "gemini-3.5-flash", config = config)
        println("Created cached content: ${cachedContent.name}")

        // Get cached content
        val fetchedCache = client.caches.get(name = cachedContent.name!!)
        println("Got cached content: ${fetchedCache.name}")

        // Update cached content
        val updatedCache =
          client.caches.update(
            name = cachedContent.name!!,
            config = UpdateCachedContentConfig(ttl = 10.minutes),
          )

        // Use the cached content to generate content
        val response =
          client.models.generateContent(
            model = "gemini-3.5-flash",
            text = "Summarize the cached data.",
            config = GenerateContentConfig(cachedContent = updatedCache.name!!),
          )
        println("Generate content with the cached content. Response: ${response.text}")

        // Delete cached content
        client.caches.delete(cachedContent.name!!)
    }
}
```

### Tunings

The SDK provides methods for creating and managing fine-tuned models. Tunings
are only supported in the Gemini Enterprise Agent Platform API.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.CreateTuningJobConfig
import com.google.genai.kotlin.types.TuningDataset
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client(
        project = "your-project-id",
        location = "us-central1",
        enterprise = true
    ).use { client ->
        // Create a tuning job
        val tuningJob = client.tunings.tune(
            baseModel = "gemini-3.5-flash",
            trainingDataset = TuningDataset(
                gcsUri = "gs://your-bucket/training-data.jsonl"
            ),
            config = CreateTuningJobConfig(
                tunedModelDisplayName = "my-tuned-model"
            )
        )
        println("Tuning job created: ${tuningJob.name}")

        // Get a tuning job
        val fetchedJob = client.tunings.get(name = tuningJob.name!!)
        println("Job state: ${fetchedJob.state}")

        // List tuning jobs
        val jobs = client.tunings.list()
        jobs.collect { job ->
            println("Found job: ${job.name}")
        }

        // Cancel a tuning job
        client.tunings.cancel(name = tuningJob.name!!)
    }
}
```

### Batches

The SDK provides methods for creating and managing batch jobs.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.BatchJobSource
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        // Create a batch job
        val src = BatchJobSource(
            fileName = "YOUR_FILE_NAME" // Or use gcsUri/bigqueryUri for Gemini Enterprise Agent Platform
        )

        val batchJob = client.batches.create(
            model = "gemini-3.5-flash",
            src = src
        )
        println("Created batch job: ${batchJob.name}")

        // Get a batch job
        val fetchedJob = client.batches.get(name = batchJob.name!!)
        println("Job state: ${fetchedJob.state}")

        // List batch jobs
        val jobs = client.batches.list()
        jobs.collect { job ->
            println("Batch Job name: ${job.name}")
        }

        // Delete a batch job
        client.batches.delete(name = batchJob.name!!)
    }
}
```

#### Batch Embeddings

You can also create batch jobs specifically for embeddings. This feature is only
supported by the Gemini Developer API.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.EmbedContentBatch
import com.google.genai.kotlin.types.EmbeddingsBatchJobSource
import com.google.genai.kotlin.types.Part
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        // Create an embeddings batch job with inlined requests
        val src = EmbeddingsBatchJobSource(
            inlinedRequests = EmbedContentBatch(
                contents = listOf(
                    Content.fromText("Hello world"),
                    Content.fromText("Batch embedding example")
                )
            )
        )

        val batchJob = client.batches.createEmbeddings(
            model = "gemini-embedding-2",
            src = src
        )
        println("Created batch embeddings job: ${batchJob.name}")
    }
}
```

### Count and Compute Tokens

You can count the number of tokens in a prompt before sending it to the model.
The SDK provides two methods for this: `countTokens` and `computeTokens`.

Use `countTokens` to get the total number of tokens for a given prompt. This
method is supported by both the Gemini Developer API and the Gemini Enterprise
Agent Platform API.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        val response = client.models.countTokens(
            model = "gemini-3.5-flash",
            text = "Why is the sky blue?"
        )

        println("Total tokens: ${response.totalTokens}")
    }
}
```

To get detailed token information, including a list of token IDs and their
corresponding representations, use `computeTokens`. `computeTokens` is only
supported by the Gemini Enterprise Agent Platform API.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client(
        project = "your-project-id",
        location = "us-central1",
        enterprise = true
    ).use { client ->
        val response = client.models.computeTokens(
            model = "gemini-3.5-flash",
            text = "Why is the sky blue?"
        )

        response.tokensInfo?.forEach { info ->
            println("Role: ${info.role}")
            println("Token IDs: ${info.tokenIds}")
            println("Tokens: ${info.tokens?.map { it.decodeToString() }}")
        }
    }
}
```

### Get a Model

To retrieve information about a model by its name, use `models.get`.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        // Gemini Enterprise Agent Platform also supports getting info on a
        // tuned model. To do this, instantiate a Client with your project and
        // location and pass the tuned model ID to get, i.e.
        // "projects/your-project-id/locations/us-central1/models/your-model-id"
        val modelInfo = client.models.get(model = "gemini-3.5-flash")

        println("Model Name: ${modelInfo.name}")
        println("Display Name: ${modelInfo.displayName}")
        println("Input Token Limit: ${modelInfo.inputTokenLimit}")
    }
}
```

### Update a Tuned Model

Use `models.update` to update the properties of an existing tuned model.

**Note**:`models.update` is only supported on Gemini Enterprise Agent
Platform.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.UpdateModelConfig
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client(
        project = "your-project-id",
        location = "us-central1",
        enterprise = true
    ).use { client ->
        val config = UpdateModelConfig(
            displayName = "New Display Name",
            description = "Updated description for my tuned model",
            updateMask = "display_name,description"
        )
        val updatedModel = client.models.update(
            model = "projects/your-project-id/locations/us-central1/models/your-model-id",
            config = config
        )
        println("Successfully updated model: ${updatedModel.name}")
    }
}
```

### Delete a Tuned Model

Use `models.delete` to delete a tuned model.

**Note**:`models.delete` is only supported on Gemini Enterprise Agent
Platform.

```kotlin
import com.google.genai.kotlin.Client
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client(
        project = "your-project-id",
        location = "us-central1",
        enterprise = true
    ).use { client ->
        client.models.delete(model = "projects/your-project-id/locations/us-central1/models/your-model-id")
        println("Tuned model successfully deleted.")
    }
}
```

### List Models

Use `list` to retrieve the available base models. The method returns a
`Pager<Model>` that you can iterate over.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.types.ListModelsConfig
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    Client().use { client ->
        println("Available Base Models:")
        // List models and process the first 5 results
        client.models.list(ListModelsConfig(pageSize = 5)).take(5).collect { model ->
            println("- ${model.name} (${model.displayName})")
        }
    }
}
```

### Ephemeral Tokens

The SDK supports creating and using ephemeral authentication tokens. This allows you to generate a short-lived token on a secure backend and pass it to a client application. The client can then connect directly to the Live API without exposing your primary API keys or Google Cloud credentials.

> [!NOTE]
> Ephemeral tokens are currently only supported by the Live API in the Gemini Developer API (not Gemini Enterprise Agent Platform) and require setting the API version to `v1alpha`. The tokens API is also marked as `@ExperimentalGenAiApi`.

To create a token, use `client.authTokens.create` with a `CreateAuthTokenConfig` that defines the constraints for the Live session. Then, initialize a new `Client` on the client application using the generated token as the `apiKey`.

```kotlin
import com.google.genai.kotlin.Client
import com.google.genai.kotlin.ExperimentalGenAiApi
import com.google.genai.kotlin.types.CreateAuthTokenConfig
import com.google.genai.kotlin.types.HttpOptions
import com.google.genai.kotlin.types.LiveConnectConfig
import com.google.genai.kotlin.types.LiveConnectConstraints
import com.google.genai.kotlin.types.Modality
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalGenAiApi::class)
fun main() = runBlocking {
    val model = "gemini-3.1-flash-live-preview"

    // Create an ephemeral auth token using standard credentials (e.g. backend server).
    val token = Client().use { client ->
        val tokenConfig = CreateAuthTokenConfig(
            uses = 1, // Number of times the token can be used to connect
            liveConnectConstraints = LiveConnectConstraints(
                model = model,
                config = LiveConnectConfig(
                    responseModalities = listOf(Modality.AUDIO),
                    temperature = 0.7
                )
            ),
            httpOptions = HttpOptions(apiVersion = "v1alpha")
        )
        client.authTokens.create(tokenConfig)
    }

    val tokenName = token.name ?: error("Token creation failed, name is null.")
    println("Created ephemeral token: $tokenName")

    // Initialize a new client with the ephemeral token (e.g. client app).
    Client(
        apiKey = tokenName,
        httpOptions = HttpOptions(apiVersion = "v1alpha")
    ).use { client ->
        client.live.connect(model).use { session ->
            println("Connected! Sending a message...")
            session.sendRealtimeInput(text = "Hello from an ephemeral token session!")

            session.receive()
                .catch { e -> println("Session closed or error: ${e.message}") }
                .collect { serverMessage ->
                    // Handle the server message stream...
                    serverMessage.serverContent?.modelTurn?.parts?.forEach { part ->
                        part.text?.let { println("[Model Text: $it]") }
                    }
                    if (serverMessage.serverContent?.turnComplete == true) {
                        session.closeSession()
                    }
                }
        }
    }
}

