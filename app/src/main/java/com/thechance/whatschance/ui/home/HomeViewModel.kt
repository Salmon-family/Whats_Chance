package com.thechance.whatschance.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.thechance.whatschance.usecase.GetColorThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getColorThemeUseCase: GetColorThemeUseCase,
    private val remoteConfig: FirebaseRemoteConfig
) : ViewModel(), ChatsAdapterListener {

    private val _homeColorUiState = MutableStateFlow(ColorUIState())
    val homeColorUiState:StateFlow<ColorUIState> = _homeColorUiState.asStateFlow()

    init {
        getColor()
    }

    private fun getColor() {
        try {
            remoteConfig.fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    val result = getColorThemeUseCase("whats_chance_colors")
                    _homeColorUiState.update { it.copy(colorValueUi = result) }

                    Log.i("lllllllllll2", result)
                    Log.i("lllllllllll3", result)
                }
            }
        } catch (e: Exception) {

        }

    }
}