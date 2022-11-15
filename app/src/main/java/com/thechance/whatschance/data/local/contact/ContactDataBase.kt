package com.thechance.whatschance.data.local.contact

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1)
abstract class ContactDataBase() : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}