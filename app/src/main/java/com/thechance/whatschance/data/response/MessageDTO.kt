package com.thechance.whatschance.data.response

data class MessageDTO(
    val from: String = "",
    val content: List<String> = emptyList(),
)