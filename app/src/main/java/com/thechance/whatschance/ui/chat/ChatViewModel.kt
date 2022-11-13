package com.thechance.whatschance.ui.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.usecase.AddMessageUseCase
import com.thechance.whatschance.domain.usecase.GetCurrentUserUseCase
import com.thechance.whatschance.domain.usecase.GetMessagesUseCase
import com.thechance.whatschance.ui.base.BaseInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getUser: GetCurrentUserUseCase,
    private val addMessage: AddMessageUseCase,
    private val getMessages: GetMessagesUseCase,
) : ViewModel(), BaseInteractionListener {
    val args = ChatFragmentArgs.fromSavedStateHandle(state)

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()


    init {
        viewModelScope.launch {
            getMessages(args.userUID).collect { list ->
                if (list.isNotEmpty()) {
                    Log.e("DEVFALAH", list.first().toString())
                    _chatUiState.update {
                        it.copy(chats = list.map {
                            MessageUi(it.textMessage,
                                it.sender == (getUser()?.uid ?: ""))
                        })
                    }

                }
            }
        }
        Log.e("TEST", "${args.userName}  \n${args.userUID}")
    }

    fun onTextMessageChange(text: CharSequence) {
        _chatUiState.update { it.copy(textMessage = text.toString()) }
    }

    fun sendMessage() {
        addMessage(args.userUID,
            Message(textMessage = _chatUiState.value.textMessage, sender = getUser()?.uid ?: ""))
        val chats = _chatUiState.value.chats.toMutableList()
        chats.add(MessageUi(textMessage = _chatUiState.value.textMessage, isFromMe = true))
        _chatUiState.update { it.copy(textMessage = "", chats = chats) }
    }
}