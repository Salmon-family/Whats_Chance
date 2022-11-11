package com.thechance.whatschance.ui.home

import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import com.thechance.whatschance.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getColorThemeUseCase: GetColorThemeUseCase,
) : BaseViewModel(), ChatsAdapterListener {

    private val _homeEvents = MutableStateFlow<Event<HomeUIEvents?>>(Event(null))
    val homeEvents = _homeEvents.asStateFlow()

    init {
        getColor(getColorThemeUseCase)
    }


    fun onClickAddContact() {
        _homeEvents.update { Event(HomeUIEvents.AddContactEvent) }
    }
}