package com.thechance.whatschance.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "MESSAGE_TABLE")
data class MessageEntity(
    @PrimaryKey val id: String,
    val textMessage: String,
    val createdAt: String = "",
    val isSent: Boolean = false,
)
