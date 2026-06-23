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

/** Disabled safety policies for computer use. */
@Serializable
@JvmInline
value class SafetyPolicy(val value: String) {
  companion object {

    /** Unspecified safety policy. This value should not be used. */
    val SAFETY_POLICY_UNSPECIFIED = SafetyPolicy("SAFETY_POLICY_UNSPECIFIED")

    /** Financial transactions safety policy. */
    val FINANCIAL_TRANSACTIONS = SafetyPolicy("FINANCIAL_TRANSACTIONS")

    /** Sensitive data modification safety policy. */
    val SENSITIVE_DATA_MODIFICATION = SafetyPolicy("SENSITIVE_DATA_MODIFICATION")

    /** Communication tool safety policy. */
    val COMMUNICATION_TOOL = SafetyPolicy("COMMUNICATION_TOOL")

    /** Account creation safety policy. */
    val ACCOUNT_CREATION = SafetyPolicy("ACCOUNT_CREATION")

    /** Data modification safety policy. */
    val DATA_MODIFICATION = SafetyPolicy("DATA_MODIFICATION")

    /** User consent management safety policy. */
    val USER_CONSENT_MANAGEMENT = SafetyPolicy("USER_CONSENT_MANAGEMENT")

    /** Legal terms and agreements safety policy. */
    val LEGAL_TERMS_AND_AGREEMENTS = SafetyPolicy("LEGAL_TERMS_AND_AGREEMENTS")
  }
}
