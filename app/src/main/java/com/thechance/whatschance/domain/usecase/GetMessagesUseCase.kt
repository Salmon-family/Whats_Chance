package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageMapper
import com.thechance.whatschance.domain.mappers.MessageToEntityMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.util.*
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageMapper: MessageMapper,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val messageToEntityMapper: MessageToEntityMapper
) {
    suspend operator fun invoke(senderId: String): Flow<List<Message>> {
//        saveUser(senderId)
        val messageFirebase = chatRepository.getMessages(getCurrentUser()?.uid ?: "", senderId)
            .map { it.map(messageMapper::map) }

      return  messageFirebase.map {
            chatRepository.saveMessageLocally(it.map(messageToEntityMapper::map))
          it.toMutableList()
        }
    }

    private suspend fun saveUser(senderId: String) {
        val x = chatRepository.getLocalMessages(senderId)
        if (x.toList().isEmpty()) {
            //save user
        }
    }

}