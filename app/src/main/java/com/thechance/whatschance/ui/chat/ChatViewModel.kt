package com.thechance.whatschance.ui.chat

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.usecase.AddMessageUseCase
import com.thechance.whatschance.domain.usecase.GetCurrentUserUseCase
import com.thechance.whatschance.domain.usecase.GetMessagesUseCase
import com.thechance.whatschance.ui.base.BaseInteractionListener
import com.thechance.whatschance.utilities.formatDate
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
) : ViewModel(), BaseInteractionListener {

    private val args = ChatFragmentArgs.fromSavedStateHandle(state)

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()


    init {
        viewModelScope.launch {
            getMessagesUseCase(args.userUID).collect { list ->
                if (list.isNotEmpty()) {
                    _chatUiState.update {
                        it.copy(chats = list.map { message ->
                            MessageUi(
                                message.textMessage,
                                message.sender == (getCurrentUserUseCase()?.uid ?: "")
                            )
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
        val message = Message(
            textMessage = _chatUiState.value.textMessage,
            sender = getCurrentUserUseCase()?.uid ?: ""
        )

        addMessageUseCase(args.userUID, message)

        val chats = _chatUiState.value.chats.toMutableList()
        chats.add(MessageUi(textMessage = _chatUiState.value.textMessage, isFromMe = true))

        _chatUiState.update { it.copy(textMessage = "", chats = chats) }
    }

    private fun setTimeText(ts1: Long, ts2: Long) {
        if (ts2 == 0L) {
            _chatUiState.update { it.copy(date = ts1.formatDate()) }
        } else {
            val cal1 = Calendar.getInstance()
            val cal2 = Calendar.getInstance()
            cal1.timeInMillis = ts1
            cal2.timeInMillis = ts2
            val sameMonth = cal1[Calendar.YEAR] === cal2[Calendar.YEAR] &&
                    cal1[Calendar.MONTH] === cal2[Calendar.MONTH]
            if (sameMonth) {
                _chatUiState.update { it.copy(date = "") }
            } else {
                _chatUiState.update { it.copy(date = ts2.formatDate()) }
            }
        }
    }
}