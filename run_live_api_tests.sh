#!/bin/bash

# Runs the live (bidirectional WebSocket) api-mode tests against the real
# backend, through the test-server proxy in record mode.
#
# These ship no recordings and only ever run against the live service.
# See go/genai-sdk:integration-testing.

# Fail on any error.
set -e

export TEST_MODE=record
export GOOGLE_GENAI_LIVE_TESTS_PACKAGE="com.google.genai.kotlin.liveapi"

echo "Client mode: $TEST_MODE"
echo "Tests package: $GOOGLE_GENAI_LIVE_TESTS_PACKAGE"
echo "Running Kotlin live E2E tests via test-server..."

# --rerun-tasks defeats Gradle's up-to-date check, which would skip an unchanged nightly.
./gradlew jvmTest -PtestMode="${TEST_MODE}" --rerun-tasks --tests "${GOOGLE_GENAI_LIVE_TESTS_PACKAGE}.*"
