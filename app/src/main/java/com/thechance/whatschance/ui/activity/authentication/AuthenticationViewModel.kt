package com.thechance.whatschance.ui.activity.authentication

import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    getColorThemeUseCase: GetColorThemeUseCase,
) : BaseViewModel() {

    init {
        getColor(getColorThemeUseCase)
    }

}