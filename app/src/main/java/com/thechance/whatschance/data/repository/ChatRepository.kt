package com.thechance.whatschance.data.repository

import com.thechance.whatschance.data.FireStoreDataSource
import com.thechance.whatschance.data.local.database.dao.MessageDao
import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.data.response.MessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ChatRepository {

    fun addMessage(uId : String,message: MessageDto) : Boolean

    suspend fun getMessages(uId: String,senderId:String) :  Flow<List<MessageDto>>

    suspend fun saveMessageLocally(message: MessageEntity)

    suspend fun getSavedUserMessages(userId: String): Flow<List<MessageEntity>>

    suspend fun getUserMessagesInSameDay(userId: String, messageDate: List<String>) : Flow<List<MessageEntity>>

}


class ChatRepositoryImp @Inject constructor(
    private val fireStoreDataSource: FireStoreDataSource,
    private val messageDao: MessageDao,
) :ChatRepository{
    override fun addMessage(uId: String, message: MessageDto): Boolean {
        return fireStoreDataSource.addMessage(uId,message)
    }

    override suspend fun getMessages(uId: String,senderId:String): Flow<List<MessageDto>> {
        return fireStoreDataSource.getMessages(uId,senderId)
    }

    override suspend fun saveMessageLocally(message: MessageEntity) {
        return messageDao.insertMessage(message)
    }

    override suspend fun getSavedUserMessages(userId: String): Flow<List<MessageEntity>> {
        return messageDao.getUserMessages(userId)
    }

    override suspend fun getUserMessagesInSameDay(
        userId: String,
        messageDate: List<String>,
    ): Flow<List<MessageEntity>> {
        return messageDao.getUserMessagesInSameDay(userId,messageDate)
    }
}