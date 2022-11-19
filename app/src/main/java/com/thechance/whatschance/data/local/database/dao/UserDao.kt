package com.thechance.whatschance.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thechance.whatschance.data.local.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM USER_TABLE u WHERE EXISTS (SELECT 1 FROM MESSAGE_TABLE m WHERE m.senderId = u.id);")
    fun getUsers(): Flow<List<UserEntity>>

}