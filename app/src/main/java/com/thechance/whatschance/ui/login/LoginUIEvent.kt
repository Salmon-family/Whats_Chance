package com.thechance.whatschance.ui.login

sealed interface LoginUIEvent {
    data class LoginEvent(val phoneNumber: String) : LoginUIEvent
}