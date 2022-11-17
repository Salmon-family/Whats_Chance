package com.thechance.whatschance.ui.authentication.verification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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

    val args = VerificationFragmentArgs.fromSavedStateHandle(state)

    private val _verifyCodeUIState = MutableStateFlow(VerificationUIState())
    val verifyCodeUIState = _verifyCodeUIState.asStateFlow()

    init {
        getColor(getColorThemeUseCase)
    }

    fun onCodeChange(smsCode: CharSequence) {
        _verifyCodeUIState.update { it.copy(code = smsCode.toString()) }
    }
}