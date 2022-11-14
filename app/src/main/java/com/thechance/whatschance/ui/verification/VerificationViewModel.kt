package com.thechance.whatschance.ui.verification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.PhoneAuthOptions
import com.thechance.whatschance.data.PhoneAuthCallBack
import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.usecase.validate.VerifyPhoneUseCase
import com.thechance.whatschance.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    state: SavedStateHandle,
    val verifyPhoneCode: VerifyPhoneUseCase,
    private val authCallbacks: PhoneAuthCallBack,
    private val repository: UserRepository
) : ViewModel() {

    val args = VerificationFragmentArgs.fromSavedStateHandle(state)

    private val _verifyCodeUIState = MutableStateFlow(VerificationUIState())
    val verifyCodeUIState = _verifyCodeUIState.asStateFlow()

    private val _verifyCodeEvent = MutableStateFlow<Event<VerificationUIEvent?>>(Event(null))
    val verifyCodeEvent = _verifyCodeEvent.asStateFlow()

    fun sendSmsCode(options: PhoneAuthOptions) {
        verifyPhoneCode.sendAuthenticationCode(options)
    }

    fun onCodeChange(smsCode: CharSequence) {
        _verifyCodeUIState.update { it.copy(code = smsCode.toString()) }
    }

    fun onClickVerify() {
        try {
            verifyPhoneCode(verifyCodeUIState.value.code, authCallbacks.getVerificationID())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewModelScope.launch {
                            repository.insertUser(args.name)
                        }
                        _verifyCodeEvent.update { Event(VerificationUIEvent.VerifyCodeEvent) }
                    } else {
                        _verifyCodeUIState.update { it.copy(error = "Incorrect") }
                    }
                }
        } catch (t: Throwable) {
            _verifyCodeUIState.update { it.copy(error = "Error in Code") }
        }
    }

    fun getAuthCallbacks() = authCallbacks

}