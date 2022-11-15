package com.thechance.whatschance.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.thechance.whatschance.data.local.database.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert
    suspend fun insertMessage(message: MessageEntity)

    @Query("SELECT * FROM MESSAGE_TABLE WHERE id IN (:userId)")
    fun getUserMessages(userId: String): Flow<List<MessageEntity>>

}