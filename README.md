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
> `generateContent`, `generateContentStream`, `embedContent`, and Context Caching (`caches`) are supported.

> [!WARNING]
> **Mobile Security: API Keys & Cloud Credentials**
>
> While this SDK supports Android targets via Kotlin Multiplatform, **we strongly discourage embedding API keys or Google Cloud IAM credentials (such as Service Account JSON keys or OAuth tokens)** directly into public mobile client applications due to the risk of credential theft and cloud project compromise via reverse engineering.
>
> * **For public mobile apps** connecting directly to generative models from client devices, we strongly recommend using **[Firebase AI Logic](https://firebase.google.com/docs/ai-logic)** with **Firebase App Check** enabled. Firebase provides secure client-side authentication and device integrity protection without exposing your Google Cloud project credentials.
> * **Use this SDK on Android** only when connecting through your own secure backend service, or for internal/prototype applications where credentials are securely managed.

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
            implementation("com.google.genai:google-genai-kotlin:0.3.0")
        }
    }
}
```

### Gradle

Add the dependency to your `build.gradle.kts` file:

```kotlin
dependencies {
    implementation("com.google.genai:google-genai-kotlin:0.3.0")
}
```

### Maven

For Maven projects (JVM only), use the `-jvm` suffixed artifact:

```xml
<dependency>
    <groupId>com.google.genai</groupId>
    <artifactId>google-genai-kotlin-jvm</artifactId>
    <version>0.3.0</version>
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

**Using Inline Image Bytes (Works on both Gemini Developer API and Gemini Enterprise Agent Platform):**

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

The SDK provides methods for managing files, which is useful for uploading media files or datasets to the Gemini Developer API.

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
            systemInstruction = Content(parts = listOf(Part(text = "You are an expert."))),
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

The SDK provides methods for creating and managing fine-tuned models. Tunings are only supported in the Gemini Enterprise Agent Platform API.

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

You can also create batch jobs specifically for embeddings. This feature is only supported by the Gemini Developer API.

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
                    Content(parts = listOf(Part(text = "Hello world"))),
                    Content(parts = listOf(Part(text = "Batch embedding example")))
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
