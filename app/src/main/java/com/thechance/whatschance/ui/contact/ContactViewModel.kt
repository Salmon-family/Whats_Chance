package com.thechance.whatschance.ui.contact

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getUsers: GetUsersUseCase,
    private val userUiMapper: UserUiMapper,
): ViewModel() , ContactInteractionListener{

    private val _contactUiState = MutableStateFlow(ContactUiState())
     val contactUiState = _contactUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getUsers().collect{ users ->
                val users = users.map(userUiMapper::map)
                _contactUiState.update { it.copy(users = users) }
            }
        }
    }
}