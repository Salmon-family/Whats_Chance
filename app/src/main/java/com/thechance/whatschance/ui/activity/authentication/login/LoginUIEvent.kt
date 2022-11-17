package com.thechance.whatschance.ui.activity.authentication.login

sealed interface LoginUIEvent {
    data class LoginEvent(val phoneNumber: String) : LoginUIEvent
}