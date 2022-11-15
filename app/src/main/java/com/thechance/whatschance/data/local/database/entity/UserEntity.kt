package com.thechance.whatschance.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_TABLE")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val phoneNumber: String,
    val image: String = "",
)
