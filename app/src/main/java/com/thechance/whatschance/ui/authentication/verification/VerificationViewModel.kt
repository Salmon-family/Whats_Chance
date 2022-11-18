package com.thechance.whatschance.ui.authentication.verification

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    state: SavedStateHandle,
    getColorThemeUseCase: GetColorThemeUseCase,
) : BaseViewModel() {

    val args =
        VerificationFragmentArgs.fromSavedStateHandle(state)

    private val _verifyCodeUIState = MutableStateFlow(VerificationUIState())
    val verifyCodeUIState = _verifyCodeUIState.asStateFlow()

    init {
        getColor(getColorThemeUseCase)
    }

    fun startTimer(){
        object: CountDownTimer(60000, 1000) {
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

    fun loading(){
        _verifyCodeUIState.update { it.copy(loading = true) }
    }

    fun error(toString: String) {
        _verifyCodeUIState.update { it.copy(error = toString, loading = false) }
    }

}