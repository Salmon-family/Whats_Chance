package com.thechance.whatschance.ui.chat

data class ChatUiState(
    val chats: List<ChatUi> = emptyList(),
    val textMessage: String = "",
)