package com.thechance.whatschance.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thechance.whatschance.data.local.database.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Query("SELECT * FROM MESSAGE_TABLE WHERE userId IN (:userId)")
    fun getUserMessages(userId: String): Flow<List<MessageEntity>>

    @Query("SELECT * FROM MESSAGE_TABLE WHERE userId IN (:userId) AND messageDate IN (:messageDate)")
    fun getUserMessagesInSameDay(userId: String, messageDate: List<String>): Flow<List<MessageEntity>>
}
