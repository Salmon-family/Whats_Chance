package com.thechance.whatschance.data.response

import java.util.Date

data class MessageDto(
    val id: String = "",
    val textMessage: String = "",
    val sender: String = "",
    val date: Date = Date()
)
