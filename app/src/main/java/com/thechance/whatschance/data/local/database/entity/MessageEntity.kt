package com.thechance.whatschance.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MESSAGE_TABLE")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val senderId: String,
    val textMessage: String,
    val isFromMe: Boolean,
    val time: Long
)
