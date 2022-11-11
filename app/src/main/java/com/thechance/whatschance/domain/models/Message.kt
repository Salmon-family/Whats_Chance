package com.thechance.whatschance.domain.models

data class Message(
    val from: String = "",
    val to: String = "",
    val content: String = "",
    val time: String = ""
)