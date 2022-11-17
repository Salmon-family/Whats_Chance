package com.thechance.whatschance.ui.verification

data class VerificationUIState (
    val code: String = "",
    val error: String = "",
    val loading: Boolean = false,
)