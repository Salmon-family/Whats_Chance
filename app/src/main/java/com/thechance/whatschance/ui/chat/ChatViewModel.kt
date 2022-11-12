package com.thechance.whatschance.ui.chat

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    state: SavedStateHandle,
) : ViewModel() {
    val args = ChatFragmentArgs.fromSavedStateHandle(state)

    init {
        Log.e("TEST", "${args.userName}  \n${args.userUID}")
    }
}