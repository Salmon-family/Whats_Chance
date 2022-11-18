package com.thechance.whatschance.ui.verification

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.PhoneAuthOptions
import com.thechance.whatschance.data.PhoneAuthCallBack
import com.thechance.whatschance.domain.usecase.validate.VerifyPhoneUseCase
import com.thechance.whatschance.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class VerificationViewModel @Inject constructor(
    state: SavedStateHandle,
    val verifyPhoneCode: VerifyPhoneUseCase,
    private val authCallbacks: PhoneAuthCallBack
) : ViewModel() {

    val args = VerificationFragmentArgs.fromSavedStateHandle(state)

    private val _verifyCodeUIState = MutableStateFlow(VerificationUIState())
    val verifyCodeUIState = _verifyCodeUIState.asStateFlow()

    private val _verifyCodeEvent = MutableStateFlow<Event<VerificationUIEvent?>>(Event(null))
    val verifyCodeEvent = _verifyCodeEvent.asStateFlow()

    fun sendSmsCode(options: PhoneAuthOptions) {
        verifyPhoneCode.sendAuthenticationCode(options)
    }

    fun startTimer(){
        object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _verifyCodeUIState.update { it.copy(time = millisUntilFinished / 1000, clickResend = false, enableResend = false) }
            }
            override fun onFinish() {
                _verifyCodeUIState.update { it.copy(enableResend = true) }
            }
        }.start()
    }

    fun resend(){
        _verifyCodeUIState.update { it.copy(clickResend = true, enableResend = false) }
    }

    fun onCodeChange(smsCode: CharSequence) {
        _verifyCodeUIState.update { it.copy(code = smsCode.toString()) }
    }

    fun onClickVerify() {
        _verifyCodeUIState.update { it.copy(loading = true) }
        try {
            verifyPhoneCode(verifyCodeUIState.value.code, authCallbacks.getVerificationID())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _verifyCodeEvent.update { Event(VerificationUIEvent.VerifyCodeEvent) }
                    } else {
                        _verifyCodeUIState.update { it.copy(error = "The Code Incorrect", loading = false) }
                    }
                }
        } catch (t: Throwable) {
            _verifyCodeUIState.update { it.copy(error = "Error in Code", loading = false) }
        }
    }

    fun getAuthCallbacks() = authCallbacks

}