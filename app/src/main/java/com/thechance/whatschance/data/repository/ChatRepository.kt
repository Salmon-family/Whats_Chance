package com.thechance.whatschance.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.thechance.whatschance.data.FireStoreDataSource
import com.thechance.whatschance.data.local.database.dao.MessageDao
import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.data.response.MessageDto
import com.thechance.whatschance.domain.mappers.MessageDtoToEntityMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ChatRepository {

    fun addMessage(uId: String, message: MessageDto): Task<DocumentReference>

    suspend fun getMessages(uId: String, senderId: String)

    suspend fun getLocalMessages(senderId: String): Flow<List<MessageEntity>>

    suspend fun saveMessagesLocally(message: List<MessageEntity>)

    suspend fun getSavedUserMessages(userId: String): Flow<List<MessageEntity>>

    suspend fun deleteMessages(uId: String, senderId: String)
}


class ChatRepositoryImp @Inject constructor(
    private val fireStoreDataSource: FireStoreDataSource,
    private val messageDao: MessageDao,
    private val messageDtoToEntityMapper: MessageDtoToEntityMapper
) : ChatRepository {

    override fun addMessage(uId: String, message: MessageDto): Task<DocumentReference> {
        return fireStoreDataSource.addMessage(uId, message)
    }

    override suspend fun getMessages(uId: String, senderId: String) {
        fireStoreDataSource.getMessages(uId, senderId).collect {
            Log.e("TESTTEST", it.toString())
            messageDao.insertMessage(it.map { messageDtoToEntityMapper.map(it) })
        }
    }

    override suspend fun getLocalMessages(senderId: String): Flow<List<MessageEntity>> {
        return messageDao.getUserMessages(senderId)
    }

    override suspend fun saveMessagesLocally(message: List<MessageEntity>) {
        return messageDao.insertMessage(message)
    }

    override suspend fun getSavedUserMessages(userId: String): Flow<List<MessageEntity>> {
        return messageDao.getUserMessages(userId)
    }

    override suspend fun deleteMessages(uId: String, senderId: String) {
        fireStoreDataSource.deleteMessages(uId, senderId)
    }


}