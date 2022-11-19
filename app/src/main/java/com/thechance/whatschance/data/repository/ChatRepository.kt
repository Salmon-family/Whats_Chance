package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.data.response.MessageDto
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun addMessage(uId: String, message: MessageDto): Task<DocumentReference>

    suspend fun refreshMessages(uId: String): Flow<List<MessageDto>>

    suspend fun getLocalMessages(senderId: String): Flow<List<MessageEntity>>

    suspend fun saveMessagesLocally(message: List<MessageEntity>)

    suspend fun deleteMessages()

    suspend fun getUsersID(): Flow<List<String>>
}

