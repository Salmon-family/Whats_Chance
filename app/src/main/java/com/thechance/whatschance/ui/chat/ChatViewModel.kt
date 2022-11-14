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
            try {
                repository.getMessage()
                    .addOnSuccessListener { documentSnapshot ->
                        val data = documentSnapshot.toObject<Message>()
                        data?.let {
                            Log.e("TESTTEST", data.content.toString())
                            _chatUiState.update { it.copy(message = data.content) }
                        }
                    }
            } catch (t: Throwable) {
            }
        }
    }

    fun send() {
        // need to move it to use-case..
        viewModelScope.launch {
            val m = Message(args.phone, listOf(content))
            repository.getDocument(args.userUID)
                .addOnSuccessListener {
                    viewModelScope.launch {  repository.insertMessage(args.userUID, m)} }
                .addOnFailureListener {
                    viewModelScope.launch {repository.insertFirstMessage(args.userUID, m)} }
        }
    }

    fun onMessageChange(message: CharSequence) {
        content = message.toString()
    }
}