package com.thechance.whatschance.ui.chat

data class ChatUiState(
    val chats: List<MessageUi> = emptyList(),
    val textMessage: String = "",
)

data class MessageUi(
    val textMessage: String = "",
    val isFromMe: Boolean = true,
    val time: String = "",
    val color: String = "#66C16F"
)

data class ChatLayout(
    val message: String,
    val stickerLayout: Int,
    val messageLayout: Int
)