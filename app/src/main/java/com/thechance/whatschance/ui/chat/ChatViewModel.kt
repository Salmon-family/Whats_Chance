package com.thechance.whatschance.ui.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.usecase.AddMessageUseCase
import com.thechance.whatschance.domain.usecase.GetColorThemeUseCase
import com.thechance.whatschance.domain.usecase.GetCurrentUserUseCase
import com.thechance.whatschance.domain.usecase.GetMessagesUseCase
import com.thechance.whatschance.ui.base.BaseInteractionListener
import com.thechance.whatschance.ui.base.BaseViewModel
import com.thechance.whatschance.utilities.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val addMessageUseCase: AddMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val timeConverter: Converter,
    private val getColorThemeUseCase: GetColorThemeUseCase,
) : BaseViewModel(), BaseInteractionListener {

    val args = ChatFragmentArgs.fromSavedStateHandle(state)

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()


    init {
        getColor(getColorThemeUseCase)
        viewModelScope.launch {
            getMessagesUseCase(args.userUID).collect { list ->
                if (list.isNotEmpty()) {
                    _chatUiState.update {
                        it.copy(chats = list.map { message ->
                            MessageUi(
                                message.textMessage,
                                isFromMe = message.fromMe,
                                color = brandColor.value,
                                time = timeConverter.convertLongToTime(message.time)
                            )
                        })
                    }

                }
            }
        }
    }

    fun onTextMessageChange(text: CharSequence) {
        _chatUiState.update { it.copy(textMessage = text.toString(), isEnabled = text.isNotBlank()) }
    }

    fun sendMessage() {
        val message = Message(
            textMessage = _chatUiState.value.textMessage.trim(),
            sender = getCurrentUserUseCase()?.uid ?: "",
            time = Date().time,
        )
        viewModelScope.launch {
            if(message.textMessage.isNotBlank()){
                addMessageUseCase(args.userUID, message)
            }
        }

        _chatUiState.update { it.copy(textMessage = "") }
    }
}