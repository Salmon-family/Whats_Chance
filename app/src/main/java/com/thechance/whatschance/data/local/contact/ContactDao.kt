package com.thechance.whatschance.data.local.contact

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(items: List<ContactEntity>)

    @Query("SELECT * FROM CONTACT_TABLE")
    fun getContact(): Flow<List<ContactEntity>>
}