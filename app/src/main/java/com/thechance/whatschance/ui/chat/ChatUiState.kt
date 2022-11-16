package com.thechance.whatschance.ui.chat

data class ChatUiState(
    val chats: List<MessageUi> = emptyList(),
    val textMessage: String = "",
    val date: String = "",
)

data class MessageUi(
    val textMessage: String = "",
    val isFromMe: Boolean = true,
)

data class ChatLayout(
    val message: String,
//    val stickerLayout: Int,
    val messageLayout: Int,
    val dateMessageLayout: Int
)