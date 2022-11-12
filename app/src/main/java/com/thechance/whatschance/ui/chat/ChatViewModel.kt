package com.thechance.whatschance.ui.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.toObject
import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.models.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    state: SavedStateHandle,
    private val repository: UserRepository
) : ViewModel(), ChatInteractionListener {
    val args = ChatFragmentArgs.fromSavedStateHandle(state)

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()

    var content: String = ""

    init {
        viewModelScope.launch {
//            repository.insertUser(args.userName)
            repository.getMessage(args.userUID)
                .addOnSuccessListener { documentSnapshot ->
                    val data = documentSnapshot.toObject<Message>()!!
                    Log.e("TESTTEST", data.content.toString())
                    _chatUiState.update { it.copy(message = data.content) }
                }
//                .collect {
//                _chatUiState.update { it.copy(message = it.message) }
//            }
        }
    }


    fun send() {
        viewModelScope.launch {
            val m = Message("+11234567891", listOf(content))
            try {
                repository.insertMessage(args.userUID, m)
            } catch (t: Throwable) {
                repository.insertFirstMessage(args.userUID, m)
            }
        }
    }

    fun onMessageChange(message: CharSequence) {
        content = message.toString()
    }
}