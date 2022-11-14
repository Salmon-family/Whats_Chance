package com.thechance.whatschance.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _brandColor = MutableStateFlow("#66C16F")
    val brandColor: StateFlow<String> = _brandColor.asStateFlow()

    fun getColor(getColorThemeUseCase: GetColorThemeUseCase) {
        viewModelScope.launch {
            try {
                val result = getColorThemeUseCase()
                if (result != "") {
                    _brandColor.tryEmit(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}