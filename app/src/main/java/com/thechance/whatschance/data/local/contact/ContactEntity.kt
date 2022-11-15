package com.thechance.whatschance.data.local.contact

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "CONTACT_TABLE")
data class ContactEntity(
    @PrimaryKey val uId: String,
    val name: String,
    val phoneNumber: Int
)