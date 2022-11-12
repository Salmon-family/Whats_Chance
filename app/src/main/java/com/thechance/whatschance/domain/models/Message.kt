package com.thechance.whatschance.domain.models

data class Message(
    val from: String = "",
    val content: List<String> = emptyList(),
    val time: String = ""
)