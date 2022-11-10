package com.thechance.whatschance.ui.main

import androidx.lifecycle.ViewModel
import com.thechance.whatschance.ui.colorUI.ColorUIState
import com.thechance.whatschance.model.usecase.GetColorThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WhatsChanceViewModel @Inject constructor(
    private val getColorThemeUseCase: GetColorThemeUseCase,
) : ViewModel() {

    private val _homeColorUiState = MutableStateFlow(ColorUIState())
    val homeColorUiState: StateFlow<ColorUIState> = _homeColorUiState.asStateFlow()

    init {
        getColor()
    }

    private fun getColor() {
        try {
            if (getColorThemeUseCase() != ""){
                val result = getColorThemeUseCase()
                _homeColorUiState.update { it.copy(colorValueUi = result) }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}