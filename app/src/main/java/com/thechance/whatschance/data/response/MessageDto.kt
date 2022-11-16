package com.thechance.whatschance.data.response


data class MessageDto(
    val id: String = "",
    val textMessage: String = "",
    val sender: String = "",
    val time: Long = 0L
)