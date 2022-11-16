package com.thechance.whatschance.ui.home

import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.domain.usecase.GetMyFriendsUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import com.thechance.whatschance.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getColorThemeUseCase: GetColorThemeUseCase,
    private val getMyFriends: GetMyFriendsUseCase,
    private val friendUIMapper: FriendUIMapper
) : BaseViewModel(), ChatsAdapterListener {

    private val _homeUIState = MutableStateFlow(HomeUIState())
    val homeUIState = _homeUIState.asStateFlow()

    private val _homeEvents = MutableStateFlow<Event<HomeUIEvents?>>(Event(null))
    val homeEvents = _homeEvents.asStateFlow()

    init {
        getColor(getColorThemeUseCase)
        viewModelScope.launch {
            getMyFriends().collect { friends ->
                _homeUIState.update { it.copy(friends = friends.map { friendUIMapper.map(it) }) }
            }
        }
    }


    fun onClickAddContact() {
        _homeEvents.update { Event(HomeUIEvents.AddContactEvent) }
    }

    override fun onFriendSelected(friend: FriendsUIState) {

    }
}