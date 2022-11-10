package com.thechance.whatschance.ui.login

data class LoginUIState(
    val countryCode: String = "",
    val phoneNumber: String = "",
    val error: String = "",
)