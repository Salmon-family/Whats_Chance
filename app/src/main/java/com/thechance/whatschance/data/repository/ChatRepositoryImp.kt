package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.ktx.Firebase
import com.thechance.whatschance.data.FireStoreDataSource
import com.thechance.whatschance.data.local.database.dao.MessageDao
import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.data.response.MessageDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ChatRepositoryImp @Inject constructor(
    private val fireStoreDataSource: FireStoreDataSource,
    private val messageDao: MessageDao
) : ChatRepository {

    override fun addMessage(uId: String, message: MessageDto): Task<DocumentReference> {
        return fireStoreDataSource.addMessage(uId, message)
    }

    override suspend fun refreshMessages(uId: String): Flow<List<MessageDto>> {
        return fireStoreDataSource.getMessages(uId)
    }

    override suspend fun getLocalMessages(senderId: String): Flow<List<MessageEntity>> {
        return messageDao.getUserMessages(senderId)
    }

    override suspend fun saveMessagesLocally(message: List<MessageEntity>) {
        return messageDao.insertMessage(message)
    }

    override suspend fun deleteMessages() {
        fireStoreDataSource.deleteMessages()
    }

    override suspend fun getUserMessagesInSameDay(
        senderId: String,
    ): Flow<List<MessageEntity>> {
        return messageDao.getUserMessagesInSameDay(senderId)
    }
}