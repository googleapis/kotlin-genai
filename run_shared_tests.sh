#!/bin/bash

# Runs the curated "shared" subset of the Kotlin E2E tests.
#
# TEST_MODE=replay plays back the checked-in recordings.
# TEST_MODE=record proxies to the live backend -- this is what the nightly
# integration job uses. See go/genai-sdk:integration-testing.

# Fail on any error.
set -e

export TEST_MODE="${TEST_MODE:-replay}"
export GOOGLE_GENAI_TESTS_SUBDIR="com.google.genai.kotlin.shared"

echo "Client mode: $TEST_MODE"
echo "Tests subdirectory: $GOOGLE_GENAI_TESTS_SUBDIR"
echo "Running Kotlin shared E2E tests via test-server..."

# Run only the shared tests.
./gradlew jvmTest -PtestMode="${TEST_MODE}" --rerun-tasks --tests "${GOOGLE_GENAI_TESTS_SUBDIR}.*"
