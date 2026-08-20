# Changelog

## [0.5.0](https://github.com/googleapis/kotlin-genai/compare/v0.4.0...v0.5.0) (2026-08-20)


### Features

* Add `mode` enum (`VERBATIM`, `SMART`) to `AudioTranscriptionConfig` and `TranscriptionConfig`. ([b39ad39](https://github.com/googleapis/kotlin-genai/commit/b39ad39527b047f5f03e0c8195f1ccc4beb7ae2b))
* add Client.clientOptions.proxyOptions support ([434a825](https://github.com/googleapis/kotlin-genai/commit/434a825a88559c65202a242617b25d6a524b77cd))
* Add enable_data_retention to ToolParallelAiSearch, Add step_count to ReinforcementTuningHyperParameters, Add BidiGenerateContentSetup ([78b7e3b](https://github.com/googleapis/kotlin-genai/commit/78b7e3b77d63b507dd8d297e443d7a88b7838153))
* Add IDLE state to live connection status enum and mark REQUIRES_ACTION as deprecated. ([c1f6eab](https://github.com/googleapis/kotlin-genai/commit/c1f6eab43a2de83be854edc52c2a237c4d6f1a92))
* Support customHttpClient in Client configuration ([a1f0a7f](https://github.com/googleapis/kotlin-genai/commit/a1f0a7f67ffedb797f4c12402b87ce5fcf3b2bb8))


### Documentation

* Add ProxyOptions example to readme ([eaae45c](https://github.com/googleapis/kotlin-genai/commit/eaae45c7cb6feff0ce2d9ccd93fea310bbfdba59))

## [0.4.0](https://github.com/googleapis/kotlin-genai/compare/v0.3.0...v0.4.0) (2026-08-12)


### Features

* add Content.fromText for building a single text content ([a336377](https://github.com/googleapis/kotlin-genai/commit/a336377fc1f4e0e5d3924bd50983ff4804d103bc))
* add finishReason, executableCode and codeExecutionResult to GenerateContentResponse ([bcd85ca](https://github.com/googleapis/kotlin-genai/commit/bcd85cacb83de6eadcab0ffaed657783c972f6f4))
* add HTTP retry support via HttpOptions.retryOptions ([133c7ea](https://github.com/googleapis/kotlin-genai/commit/133c7ea504a18eb9134e184dbda00c2231cbb3bc))
* Add interaction_status to LiveServerContent ([eb5e572](https://github.com/googleapis/kotlin-genai/commit/eb5e5724a687f5fd62fad6bd97165e786b712284))
* Add TOO_MANY_TOOL_CALLS to FinishReason enum. ([628a067](https://github.com/googleapis/kotlin-genai/commit/628a067c0380fcd1732ca9455c34ae81ed7a5b23))
* support multi-turn chat with Chat.sendMessage ([2a07922](https://github.com/googleapis/kotlin-genai/commit/2a07922a95f01e8af1953b8cc9dceeaa96e6a865))
* support streaming chat with Chat.sendMessageStream ([e72af6d](https://github.com/googleapis/kotlin-genai/commit/e72af6d5b8ec1eee1943730a8158be3ff1b50011))


### Bug Fixes

* Add propertyOrdering auto-population for ResponseSchema and ResponseJsonSchema for Dotnet SDK ([de6e8c1](https://github.com/googleapis/kotlin-genai/commit/de6e8c155953a4a34b898cd61e1c6773d680f071))


### Documentation

* add chat examples and document the chat session in README ([c9125dd](https://github.com/googleapis/kotlin-genai/commit/c9125ddbbc4969275cc952190f8748da72b6af81))
* document the HTTP retry options ([2f8e69d](https://github.com/googleapis/kotlin-genai/commit/2f8e69de1286e4b7ee65586461cde3275e7eef97))

## [0.3.0](https://github.com/googleapis/kotlin-genai/compare/v0.2.0...v0.3.0) (2026-08-03)


### ⚠ BREAKING CHANGES

* **android:** block Client initialization with API key or credentials on Android

### Features

* [GenerateContentConfig] Add GenerationConfig.audio_transcription_config and Part.audio_transcription. ([80094fc](https://github.com/googleapis/kotlin-genai/commit/80094fc7b2c0516e5ffb6e319702393a7b859606))
* Add Batches module, methods: 'create', 'create_embeddings', 'get', 'cancel', 'list', 'delete' ([340f315](https://github.com/googleapis/kotlin-genai/commit/340f3159cab9df90d574a989abf030eb3a853b1d))
* Add countTokens and computeTokens methods ([eef2bf1](https://github.com/googleapis/kotlin-genai/commit/eef2bf15b311bee82552f7611760effec6def9de))
* Add Files module, methods: get, upload, list, download, delete ([059e99c](https://github.com/googleapis/kotlin-genai/commit/059e99c405c84ab0bc9cb684ceb0535a92f43c50))
* Add flat `language_codes` field to `AudioTranscriptionConfig`. ([055b8d5](https://github.com/googleapis/kotlin-genai/commit/055b8d531a920827a808f10dc7c0bb085dd645f2))
* Add helper methods to LiveServerMessage to quickly access text and data ([f916666](https://github.com/googleapis/kotlin-genai/commit/f916666475259c288fad04e776e160bc83c78fd7))
* Add Live API types ([bdbade2](https://github.com/googleapis/kotlin-genai/commit/bdbade26132533941e3b6c721cc7584fcdb4a2af))
* Add Models.get, Models.delete, Models.list and Models.UpdateTunedModel methods ([295daca](https://github.com/googleapis/kotlin-genai/commit/295daca465e0d4d99fabffca8fd45a9890a275bd))
* add Pager&lt;T&gt; and support Caches.list() method ([f701987](https://github.com/googleapis/kotlin-genai/commit/f701987c3cf51e26598cad90cc5da71126ee1ca1))
* Add support for Live API, including connect(), receive(), sendRealtimeInput(), sendClientContent(), and sendToolResponse() ([fe0967e](https://github.com/googleapis/kotlin-genai/commit/fe0967e041464fdf31e901aa8f28127cb1f27c7a))
* Add Tunings module, methods: get, list, cancel, tune, validate_reward ([6f9027f](https://github.com/googleapis/kotlin-genai/commit/6f9027f80d3fa71ca14c593be8728eeda4945e14))
* Allow api key + proj/location for enterprise mode ([d43ec22](https://github.com/googleapis/kotlin-genai/commit/d43ec2281942ea739f930189b5548838e9c1c538))
* Support GoogleMaps Tool grounding_types places and routing ([1a73730](https://github.com/googleapis/kotlin-genai/commit/1a73730c2bb8740203e0016b31f2116dd09107d5))
* support models.embedContent() ([9229adf](https://github.com/googleapis/kotlin-genai/commit/9229adffc546b70422786f5dbc64ebfa901fc37a))


### Bug Fixes

* **android:** block Client initialization with API key or credentials on Android ([ac6d089](https://github.com/googleapis/kotlin-genai/commit/ac6d08938f8c6a573c924c4789c031e38097cbcf))


### Documentation

* Add examples and README for Caches API ([44123b8](https://github.com/googleapis/kotlin-genai/commit/44123b894f5e68f79e110cdb4bfe69bc212f4dd3))
* Add examples for countTokens and computeTokens ([150f3f0](https://github.com/googleapis/kotlin-genai/commit/150f3f0b6c53ec924196b30d16a0613042633dd3))
* Add examples for Live API usage ([7f823d3](https://github.com/googleapis/kotlin-genai/commit/7f823d33f03fb9ddb9e64a901953ff811289ced0))
* Add readme example for computeTokens and countTokens ([f46e08a](https://github.com/googleapis/kotlin-genai/commit/f46e08a13cdb47086950825336b66ef674cec0a2))
* Add README update for Batches ([d09f97a](https://github.com/googleapis/kotlin-genai/commit/d09f97a85f991385c695bd75558d7e5d403a59f1))
* Add README update for Files ([a47d4ba](https://github.com/googleapis/kotlin-genai/commit/a47d4bae58cc1c7c5432bed9e052da0f271c4a09))
* Add README update for Models.embedContent() ([b942d4d](https://github.com/googleapis/kotlin-genai/commit/b942d4d29e2337c4b55c30a11e8a6892bde2c7c5))
* Add README update for Tunings ([af174d5](https://github.com/googleapis/kotlin-genai/commit/af174d5a0e49fcb1025b7fe0a342042026094b68))
* Add samples for models.get, models.update, models.list, and models.delete ([0db4dd9](https://github.com/googleapis/kotlin-genai/commit/0db4dd94bfad201ec0a7138160b5536d0db7980a))

## [0.2.0](https://github.com/googleapis/kotlin-genai/compare/v0.1.0...v0.2.0) (2026-07-09)


### Features

* Add response_format and Translation_config in GenerationConfig ([9480030](https://github.com/googleapis/kotlin-genai/commit/9480030a870f82cb2fbec5e25fce837050721074))
* Add support for Caches API (create, get, update, delete) ([ca71d46](https://github.com/googleapis/kotlin-genai/commit/ca71d4657037c58255c277c6b39eec4677655ac1))
* Add Tool.exa_ai_search for Gemini Enterprise API ([9480030](https://github.com/googleapis/kotlin-genai/commit/9480030a870f82cb2fbec5e25fce837050721074))
* Support labels and routingConfig in GenerateContentConfig (for Gemini Enterprise API only) ([f1587c8](https://github.com/googleapis/kotlin-genai/commit/f1587c80bc9aa3799bcb20b09105ab0bd3a216dc))
* voice consent signature types across all SDK languages. ([e0d4428](https://github.com/googleapis/kotlin-genai/commit/e0d4428cc715c2575ff1ba3204993f22707a6f56))


### Documentation

* Acknowledge that this SDK is not for mobile app usage ([a56dbf4](https://github.com/googleapis/kotlin-genai/commit/a56dbf4efbd98e383eae5ed0b37cb10d587b5c84))
* Update README with installation guide ([5c4f0d9](https://github.com/googleapis/kotlin-genai/commit/5c4f0d9b11f72c5b33097d31a7ee02c854c3f831))
