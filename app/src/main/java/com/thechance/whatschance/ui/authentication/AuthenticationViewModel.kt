package com.thechance.whatschance.ui.authentication

import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val getColorThemeUseCase: GetColorThemeUseCase,
) : BaseViewModel() {

    init {
        getColor(getColorThemeUseCase)
    }

}