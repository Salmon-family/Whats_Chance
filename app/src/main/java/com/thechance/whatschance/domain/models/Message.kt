package com.thechance.whatschance.domain.models

data class Message(
    val id: Int = 0,
    val textMessage: String = "",
    val sender: String = "",
    val time: Long
)