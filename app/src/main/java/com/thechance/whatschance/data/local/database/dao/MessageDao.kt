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

    //    @Query("SELECT DISTINCT senderId FROM MESSAGE_TABLE " )
    @Query("SELECT DISTINCT senderId FROM MESSAGE_TABLE  EXCEPT SELECT id FROM USER_TABLE")
    fun getUsers(): Flow<List<String>>

}