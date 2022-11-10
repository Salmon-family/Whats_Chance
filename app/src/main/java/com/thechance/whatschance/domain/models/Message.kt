package com.thechance.whatschance.domain.models

data class Message(
    val id: String = "",
    val textMessage: String = "",
    val chatUsers: List<String> = emptyList(),
)
