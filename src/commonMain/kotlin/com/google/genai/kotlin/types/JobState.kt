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

import kotlin.jvm.JvmInline
import kotlinx.serialization.Serializable

/** Job state. */
@Serializable
@JvmInline
value class JobState(val value: String) {
  companion object {

    /** The job state is unspecified. */
    val JOB_STATE_UNSPECIFIED = JobState("JOB_STATE_UNSPECIFIED")

    /** The job has been just created or resumed and processing has not yet begun. */
    val JOB_STATE_QUEUED = JobState("JOB_STATE_QUEUED")

    /** The service is preparing to run the job. */
    val JOB_STATE_PENDING = JobState("JOB_STATE_PENDING")

    /** The job is in progress. */
    val JOB_STATE_RUNNING = JobState("JOB_STATE_RUNNING")

    /** The job completed successfully. */
    val JOB_STATE_SUCCEEDED = JobState("JOB_STATE_SUCCEEDED")

    /** The job failed. */
    val JOB_STATE_FAILED = JobState("JOB_STATE_FAILED")

    /**
     * The job is being cancelled. From this state the job may only go to either
     * `JOB_STATE_SUCCEEDED`, `JOB_STATE_FAILED` or `JOB_STATE_CANCELLED`.
     */
    val JOB_STATE_CANCELLING = JobState("JOB_STATE_CANCELLING")

    /** The job has been cancelled. */
    val JOB_STATE_CANCELLED = JobState("JOB_STATE_CANCELLED")

    /** The job has been stopped, and can be resumed. */
    val JOB_STATE_PAUSED = JobState("JOB_STATE_PAUSED")

    /** The job has expired. */
    val JOB_STATE_EXPIRED = JobState("JOB_STATE_EXPIRED")

    /**
     * The job is being updated. Only jobs in the `JOB_STATE_RUNNING` state can be updated. After
     * updating, the job goes back to the `JOB_STATE_RUNNING` state.
     */
    val JOB_STATE_UPDATING = JobState("JOB_STATE_UPDATING")

    /** The job is partially succeeded, some results may be missing due to errors. */
    val JOB_STATE_PARTIALLY_SUCCEEDED = JobState("JOB_STATE_PARTIALLY_SUCCEEDED")
  }
}
