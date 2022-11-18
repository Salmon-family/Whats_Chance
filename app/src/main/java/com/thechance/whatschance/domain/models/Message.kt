package com.thechance.whatschance.domain.models

data class Message(
    val textMessage: String = "",
    val sender: String = "",
    val fromMe: Boolean = false,
    val time: Long
)