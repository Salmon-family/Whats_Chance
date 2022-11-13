package com.thechance.whatschance.ui.chat

import android.util.Log
import androidx.lifecycle.*
import com.thechance.whatschance.R
import com.thechance.whatschance.data.response.Message
import com.thechance.whatschance.domain.usecase.GetStickersUseCase
import com.thechance.whatschance.domain.usecase.SendStickersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    state: SavedStateHandle,
    private val sendStickersUseCase: SendStickersUseCase,
    private val getStickersUseCase: GetStickersUseCase
) : ViewModel(), ChatsAdapterListener {
    val args = ChatFragmentArgs.fromSavedStateHandle(state)

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()

    val text = MutableLiveData<String>()

    init {
        Log.e("TEST", "${args.userName}  \n${args.userUID}")
        getStickers()
    }

    fun sendStickers() {
        viewModelScope.launch {
            try {
                sendStickersUseCase(
                    Message(
                        id = args.userUID,
                        username = args.userName,
                        message = text.value.toString()
                    )
                )
                text.value = ""
            } catch (e: Exception) {


            }
        }
    }

    private fun getStickers() {
        viewModelScope.launch {
            try {
                getStickersUseCase().collect { result ->
                        _chatUiState.update {
                            it.copy(listOfDetailsChatUiState = result.map {
                                DetailsChatUiState(
                                    id = it.id,
                                    name = it.username,
                                    lastMessage = it.message
                                )
                            })
                        }
                }
            } catch (e: Exception) {
                Log.i("lllllllllllerror", e.message.toString())
            }

        }
    }
}