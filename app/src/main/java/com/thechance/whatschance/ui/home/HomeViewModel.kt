package com.thechance.whatschance.ui.home

import androidx.lifecycle.ViewModel
import com.thechance.whatschance.usecase.GetColorThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getColorThemeUseCase: GetColorThemeUseCase
) : ViewModel(), ChatsAdapterListener {

    private val _homeColorUiState = MutableStateFlow(ColorUIState())
    val homeColorUiState = _homeColorUiState.asStateFlow()

    init {
        getColor()
    }

    private fun getColor() {
        try {
            _homeColorUiState.update { it.copy(statusBarUi = getColorThemeUseCase("whats_chance_color")) }
        } catch (e: Exception) {

        }

    }
}