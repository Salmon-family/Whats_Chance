package com.thechance.whatschance.ui.authentication.login

import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.domain.usecase.ValidatePhoneNumberUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import com.thechance.whatschance.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val verifyPhoneNumber: ValidatePhoneNumberUseCase,
    getColorThemeUseCase: GetColorThemeUseCase
) : BaseViewModel() {

    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState = _loginUIState.asStateFlow()

    private val _loginEvent = MutableStateFlow<Event<LoginUIEvent?>>(Event(null))
    val loginEvent = _loginEvent.asStateFlow()

    init {
        getColor(getColorThemeUseCase)
    }

    fun onPhoneNumberChange(phone: CharSequence) {
        _loginUIState.update { it.copy(phoneNumber = phone.toString()) }
    }


    fun onNameChange(name: CharSequence) {
        _loginUIState.update { it.copy(name = name.toString()) }
    }

    fun onCountryCodeChange(code: String) {
        _loginUIState.update { it.copy(countryCode = code) }
    }

    fun login() {
        _loginUIState.update { it.copy(error = "") }

        try {
            if (verifyPhoneNumber(loginUIState.value.phoneNumber)) {
                _loginEvent.update {
                    Event(
                        LoginUIEvent.LoginEvent(
                            loginUIState.value.getFullPhoneNumber(),
                            loginUIState.value.name
                        )
                    )
                }
            } else {
                _loginUIState.update { it.copy(error = "incorrect, check your phone number.") }
            }

        } catch (t: Throwable) {
            _loginUIState.update { it.copy(error = t.message.toString()) }
        }
    }


}