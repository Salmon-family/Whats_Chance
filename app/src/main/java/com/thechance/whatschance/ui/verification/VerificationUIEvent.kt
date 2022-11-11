package com.thechance.whatschance.ui.verification

sealed interface VerificationUIEvent {
    object VerifyCodeEvent : VerificationUIEvent
}