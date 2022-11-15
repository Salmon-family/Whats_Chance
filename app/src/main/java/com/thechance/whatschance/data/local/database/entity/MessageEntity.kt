package com.thechance.whatschance.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MESSAGE_TABLE")
data class MessageEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val textMessage: String,
    val messageDate: String = "",
    val isFromMe: Boolean,
)
