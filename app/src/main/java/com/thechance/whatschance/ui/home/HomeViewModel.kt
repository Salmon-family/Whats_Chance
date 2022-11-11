package com.thechance.whatschance.ui.home

import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getColorThemeUseCase: GetColorThemeUseCase,
) : BaseViewModel(), ChatsAdapterListener {

    init {
        getColor { getColorThemeUseCase() }
    }

}