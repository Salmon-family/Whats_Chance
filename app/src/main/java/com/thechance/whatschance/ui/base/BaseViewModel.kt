package com.thechance.whatschance.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    private val _homeColorUiState = MutableStateFlow("#66C16F")
    val homeColorUiState: StateFlow<String> = _homeColorUiState.asStateFlow()

    fun getColor(getColorThemeUseCase: () -> String) {
        try {
            if (getColorThemeUseCase() != "") {
                val result = getColorThemeUseCase()
                _homeColorUiState.tryEmit(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}