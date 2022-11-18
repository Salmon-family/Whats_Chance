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
    suspend fun insertMessage(message: List<MessageEntity>)

    @Query("SELECT * FROM MESSAGE_TABLE WHERE senderId IN (:userId) ORDER BY time DESC")
    fun getUserMessages(userId: String): Flow<List<MessageEntity>>

    @Query("SELECT * FROM MESSAGE_TABLE WHERE senderId == :senderId GROUP BY date(time / 1000, 'unixepoch')")
    fun getUserMessagesInSameDay(senderId: String): Flow<List<MessageEntity>>
}
