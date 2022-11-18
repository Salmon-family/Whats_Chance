package com.thechance.whatschance.ui.authentication.verification

data class VerificationUIState (
    val code: String = "",
    val error: String = "",
    val loading: Boolean = false,
    val time: Long = 0,
    val clickResend: Boolean = false,
    val enableResend: Boolean = false,
    )