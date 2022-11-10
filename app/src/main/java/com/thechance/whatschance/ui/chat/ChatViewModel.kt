package com.thechance.whatschance.ui.chat

import android.util.Log
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
        saveMessage("name")
        saveMessage("mahmoud")
        getMessage()
    }

    private fun saveUser(id: String, name: String, mobile: String){
        viewModelScope.launch { saveUserUseCase(id, name, mobile) }
    }

    private fun saveMessage(text: String){
        viewModelScope.launch { saveMessageUseCase("", text) }
    }

    private fun getMessage(){
        viewModelScope.launch {getMessageUseCase()}
    }
}