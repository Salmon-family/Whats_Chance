package com.thechance.whatschance.data.repository

import com.thechance.whatschance.data.FireStoreDataSource
import com.thechance.whatschance.data.response.MessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ChatRepository {

    fun addMessage(uId : String,message: MessageDto) : Boolean

    suspend fun getMessages(uId: String,senderId:String) :  Flow<List<MessageDto>>
}


class ChatRepositoryImp @Inject constructor(
    private val fireStoreDataSource: FireStoreDataSource,
) :ChatRepository{
    override fun addMessage(uId: String, message: MessageDto): Boolean {
        return fireStoreDataSource.addMessage(uId,message)
    }

    override suspend fun getMessages(uId: String,senderId:String): Flow<List<MessageDto>> {
        return fireStoreDataSource.getMessages(uId,senderId).map { it.toObjects(MessageDto::class.java) }
    }
}