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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: List<UserEntity>)

    @Query("SELECT * FROM USER_TABLE u WHERE EXISTS (SELECT 1 FROM MESSAGE_TABLE m WHERE m.senderId = u.id);")
    fun getUsers(): Flow<List<UserEntity>>

    @Query("SELECT EXISTS(SELECT * FROM USER_TABLE u WHERE u.id = :id)")
    suspend fun isUserExist(id : String) : Boolean

    @Query("SELECT * FROM USER_TABLE WHERE NAME LIKE '%' || :searchQuery || '%' ORDER BY NAME ASC")
    fun searchUsers(searchQuery: String): Flow<List<UserEntity>>
}