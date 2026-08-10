#!/bin/bash

# Runs the curated "shared" subset of the Kotlin E2E tests. TEST_MODE=record proxies
# to the live backend, which is what the nightly job uses. See
# go/genai-sdk:integration-testing.

set -e

export TEST_MODE="${TEST_MODE:-replay}"
export GOOGLE_GENAI_TESTS_SUBDIR="com.google.genai.kotlin.shared"

echo "Client mode: $TEST_MODE"
echo "Tests subdirectory: $GOOGLE_GENAI_TESTS_SUBDIR"
echo "Running Kotlin shared E2E tests via test-server..."

./gradlew jvmTest --no-daemon -PtestMode="${TEST_MODE}" --rerun-tasks --tests "${GOOGLE_GENAI_TESTS_SUBDIR}.*"
