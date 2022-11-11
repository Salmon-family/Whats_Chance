package com.thechance.whatschance.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.usecases.GetMessageUseCase
import com.thechance.whatschance.domain.usecases.SaveMessageUseCase
import com.thechance.whatschance.domain.usecases.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageUIStateMapper: MessageUIStateMapper,
    private val saveUserUseCase: SaveUserUseCase,
    private val saveMessageUseCase: SaveMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(MessageUIState())
    val uiState = _uiState.asStateFlow()

    init {
        saveUser("User1", "Mahmoud", "+201098765432")
        saveUser("User2", "X", "+201055566610")
        sendMessage("new", "+201098765432", "+2010555666")
        sendMessage("way", "+201098765432", "+2010555666")
        getMessage("+201098765432","+2010555666")
    }

    private fun saveUser(id: String, name: String, mobile: String){
        viewModelScope.launch { saveUserUseCase(id, name, mobile) }
    }

    private fun sendMessage(text: String, user1: String, user2: String){
        viewModelScope.launch { saveMessageUseCase("", text, user1, user2) }
    }

    private fun getMessage(user1: String, user2: String){
        viewModelScope.launch {getMessageUseCase(user1, user2)}
    }
}