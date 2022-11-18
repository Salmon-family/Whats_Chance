package com.thechance.whatschance.ui.login

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import com.thechance.whatschance.domain.usecase.validate.ValidatePhoneNumberUseCase
import com.thechance.whatschance.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val verifyPhoneNumber: ValidatePhoneNumberUseCase
) : ViewModel() {

    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState = _loginUIState.asStateFlow()

    private val _loginEvent = MutableStateFlow<Event<LoginUIEvent?>>(Event(null))
    val loginEvent = _loginEvent.asStateFlow()


    fun onPhoneNumberChange(phone: CharSequence) {
        _loginUIState.update { it.copy(phoneNumber = phone.toString()) }
    }

    fun onCountryCodeChange(code: String) {
        _loginUIState.update { it.copy(countryCode = code) }
    }

    fun login() {
        _loginUIState.update { it.copy(error = "") }
        try {
            if (verifyPhoneNumber(loginUIState.value.phoneNumber)) {
                _loginEvent.update { Event(LoginUIEvent.LoginEvent(loginUIState.value.getFullPhoneNumber())) }
            } else {
                _loginUIState.update { it.copy(error = "incorrect, check your phone number.") }
            }

        } catch (t: Throwable) {
            _loginUIState.update { it.copy(error = t.message.toString()) }
        }
    }

}