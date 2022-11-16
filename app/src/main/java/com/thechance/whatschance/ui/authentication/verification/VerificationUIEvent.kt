package com.thechance.whatschance.ui.authentication.verification

sealed interface VerificationUIEvent {
    object VerifyCodeEvent : VerificationUIEvent
}