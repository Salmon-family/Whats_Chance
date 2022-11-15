package com.thechance.whatschance.ui.chat

data class ChatUiState(
    val chats: List<MessageUi> = emptyList(),
    val textMessage: String = "",
    val firstDateMessage: String = "",
)

data class MessageUi(
    val textMessage: String = "",
    val isFromMe: Boolean = true,
    val date: String = "",
)