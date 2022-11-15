package com.thechance.whatschance.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thechance.whatschance.data.local.database.dao.MessageDao
import com.thechance.whatschance.data.local.database.dao.UserDao
import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.data.local.database.entity.UserEntity

@Database(
    entities = [MessageEntity::class, UserEntity::class],
    version = 1
)
abstract class ChatDataBase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao
}