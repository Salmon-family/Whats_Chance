package com.thechance.whatschance.ui.home

import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.data.response.UserDto
import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.domain.usecase.GetUsersUseCase
import com.thechance.whatschance.ui.base.BaseViewModel
import com.thechance.whatschance.ui.contact.ContactInteractionListener
import com.thechance.whatschance.ui.contact.UserUi
import com.thechance.whatschance.ui.contact.UserUiMapper
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
    private val getUsers: GetUsersUseCase,
    private val userUiMapper: UserUiMapper,
) : BaseViewModel(), ChatsAdapterListener, ContactInteractionListener {

    private val _homeUIState = MutableStateFlow(ContactUiState())
    val homeUIState = _homeUIState.asStateFlow()

    private val _homeEvents = MutableStateFlow<Event<HomeUIEvents?>>(Event(null))
    val homeEvents = _homeEvents.asStateFlow()

    init {
        getColor(getColorThemeUseCase)
        getContact()
    }

    private fun getContact() {
        viewModelScope.launch {
            getUsers.getContact().collect { users ->
                val users = users.map {
                    UserUi(
                        uId = it.uId ?: "",
                        name = it.name ?: "",
                        phoneNumber = it.phoneNumber ?: ""
                    )
                }
                _homeUIState.update { it.copy(users = users) }
            }
        }
    }

    fun onClickAddContact() {
        _homeEvents.update { Event(HomeUIEvents.AddContactEvent) }
    }

    override fun onItemSelected(user: UserUi) {

    }
}