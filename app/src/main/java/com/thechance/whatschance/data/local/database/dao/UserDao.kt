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

    @Query("SELECT * FROM USER_TABLE ORDER BY NAME ASC")
    fun getUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("SELECT * FROM USER_TABLE WHERE NAME LIKE '%' || :searchQuery || '%' ORDER BY NAME ASC")
    fun searchUsers(searchQuery: String): Flow<List<UserEntity>>
}