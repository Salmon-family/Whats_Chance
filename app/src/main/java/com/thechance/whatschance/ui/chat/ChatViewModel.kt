package com.thechance.whatschance.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thechance.whatschance.data.repository.AuthenticationRepository
import com.thechance.whatschance.domain.models.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : ViewModel() {

    init {
//         if user not already there add
        viewModelScope.launch {
            repository.insertUser()

//            repository.insertMessage(
//                Message(
//                    "+11234567891",
//                    "+17654097584",
//                    "message message " + Math.random()
//                )
//            )

            repository.getMessage()
        }


    }
}