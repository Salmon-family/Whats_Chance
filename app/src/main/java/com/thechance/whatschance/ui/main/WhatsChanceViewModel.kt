package com.thechance.whatschance.ui.main

import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.domain.usecase.GetUserLoginStatusUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WhatsChanceViewModel @Inject constructor(
    private val getColorThemeUseCase: GetColorThemeUseCase,
    private val getUserLogin: GetUserLoginStatusUseCase
) : BaseViewModel() {

    init {
        getColor(getColorThemeUseCase)
    }

    fun isUserLogin() = getUserLogin()

}