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

> [!NOTE] This SDK is currently in early development. At this stage, only
> `generateContent` and `generateContentStream` are supported.

## Installation

Add the dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("com.google.genai.kotlin:google-genai:0.1.0")
}
```

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

        val text = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
        println(text)
    }
}
```

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
            val chunkText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            if (chunkText != null) {
                print(chunkText)
            }
        }
        println() // End with a newline
    }
}
```

### Advanced Configuration

You can pass a `GenerateContentConfig` to customize the request, such as setting
system instructions or temperature.

```kotlin
import com.google.genai.kotlin.types.Content
import com.google.genai.kotlin.types.GenerateContentConfig
import com.google.genai.kotlin.types.Part

val config = GenerateContentConfig(
    systemInstruction = Content(parts = listOf(Part(text = "You are a helpful assistant."))),
    temperature = 0.5,
    maxOutputTokens = 1024
)

val response = client.models.generateContent(
    model = "gemini-3.5-flash",
    text = "What is your name?",
    config = config
)
```
