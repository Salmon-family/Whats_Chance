package com.thechance.whatschance.ui.chat

data class ChatUiState(
    val listOfDetailsChatUiState: List<DetailsChatUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
