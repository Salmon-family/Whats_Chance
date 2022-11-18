package com.thechance.whatschance.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.usecase.GetUsersUseCase
import com.thechance.whatschance.domain.usecase.SaveUsersLocallyUseCase
import com.thechance.whatschance.domain.usecase.SearchUsersUseCase
import com.thechance.whatschance.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase,
    private val userUiMapper: UserUiMapper,
    private val userMapper: UserMapper,
    private val saveUserUseCase: SaveUsersLocallyUseCase,
    private val searchUsersUseCase: SearchUsersUseCase,
) : ViewModel(), ContactInteractionListener {

    private val _contactUiState = MutableStateFlow(ContactUiState())
    val contactUiState = _contactUiState.asStateFlow()

    private val _contactEvents = MutableStateFlow<Event<ContactUIEvents?>>(Event(null))
    val contactEvents = _contactEvents.asStateFlow()

    init {
        viewModelScope.launch {
            getUsers().collect { users ->
                val users = users.map(userUiMapper::map)
                _contactUiState.update { it.copy(users = users) }
            }
        }
    }

    override fun onItemSelected(user: UserUi) {
        viewModelScope.launch {
            saveUserUseCase(userMapper.map(user))
        }
        _contactEvents.update { Event(ContactUIEvents.SelectedUser(user)) }
    }

    fun onSearchInputChanged(searchQuery: CharSequence) {
        onSearch(searchQuery.toString())
    }

    private fun onSearch(searchQuery: String) {
        viewModelScope.launch {
            searchUsersUseCase(searchQuery = searchQuery).collect { users ->
                _contactUiState.update { uiState ->
                    uiState.copy(users = users.map {
                        userUiMapper.map(it)
                    })
                }
            }
        }
    }
}