package com.thechance.whatschance.ui.authentication.login

sealed interface LoginUIEvent {
    data class LoginEvent(val phoneNumber: String, val userName: String) : LoginUIEvent
}