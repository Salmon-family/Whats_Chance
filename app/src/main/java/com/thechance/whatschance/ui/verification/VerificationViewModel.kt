package com.thechance.whatschance.ui.verification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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
    val verifyPhoneCode: VerifyPhoneUseCase
) : ViewModel() {

    val args = VerificationFragmentArgs.fromSavedStateHandle(state)

    private val _verifyCodeUIState = MutableStateFlow(VerificationUIState())
    val verifyCodeUIState = _verifyCodeUIState.asStateFlow()

    private val _verifyCodeEvent = MutableStateFlow<Event<VerificationUIEvent?>>(Event(null))
    val verifyCodeEvent = _verifyCodeEvent.asStateFlow()

    init {
        verifyPhoneCode.sendAuthenticationCode(args.phone)
    }

    fun onCodeChange(smsCode: CharSequence) {
        _verifyCodeUIState.update { it.copy(code = smsCode.toString()) }
    }

    fun onClickVerify() {
        try {
            verifyPhoneCode(verifyCodeUIState.value.code).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _verifyCodeEvent.update { Event(VerificationUIEvent.VerifyCodeEvent) }
                } else {
                    _verifyCodeUIState.update { it.copy(error = "Incorrect") }
                }
            }
        } catch (t: Throwable) {
            _verifyCodeUIState.update { it.copy(error = "Error in Code") }
        }
    }

}