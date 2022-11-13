package com.thechance.whatschance.ui.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.usecase.AddMessageUseCase
import com.thechance.whatschance.domain.usecase.GetCurrentUserUseCase
import com.thechance.whatschance.domain.usecase.GetUsersUseCase
import com.thechance.whatschance.ui.contact.ContactUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.w3c.dom.Text
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getUser: GetCurrentUserUseCase,
    private val addMessage: AddMessageUseCase,
) : ViewModel() {
    val args = ChatFragmentArgs.fromSavedStateHandle(state)

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()


    init {
        Log.e("TEST", "${args.userName}  \n${args.userUID}")
    }

    fun onTextMessageChange(text: CharSequence){
        _chatUiState.update { it.copy(textMessage = text.toString()) }
    }

    fun sendMessage(){
        addMessage(args.userUID, Message(textMessage = _chatUiState.value.textMessage, sender = getUser()?.uid ?: ""))
        _chatUiState.update { it.copy(textMessage = "") }
    }
}