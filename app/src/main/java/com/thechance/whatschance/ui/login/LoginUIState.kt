package com.thechance.whatschance.ui.login

data class LoginUIState(
    val countryCode: String = "+1",
    val phoneNumber: String = "",
    val error: String = "",
) {
    fun getFullPhoneNumber(): String {
        return countryCode + phoneNumber
    }
}