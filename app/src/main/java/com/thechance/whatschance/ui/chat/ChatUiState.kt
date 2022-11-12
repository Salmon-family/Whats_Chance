package com.thechance.whatschance.ui.chat

data class ChatUiState(
    val name: String = "",
    val message: List<String> = emptyList(),
    val lastMessageTime: String = "",
)