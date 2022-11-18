package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageDtoToEntityMapper
import com.thechance.whatschance.domain.mappers.MessageEntityMapper
import com.thechance.whatschance.domain.mappers.MessageMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val messageDtoToEntityMapper: MessageDtoToEntityMapper,
    private val messageEntityMapper: MessageEntityMapper,
    private val messageMapper: MessageMapper,
) {
    suspend operator fun invoke(senderId: String): Flow<List<Message>> {
        CoroutineScope(Dispatchers.IO).launch {
            refreshMessages(senderId)
        }
        return chatRepository.getLocalMessages(senderId).map { it.map(messageEntityMapper::map)}
    }




    private suspend fun refreshMessages(senderId: String){
               chatRepository.refreshMessages(getCurrentUser()?.uid ?: "",senderId).collect {
                   chatRepository.saveMessagesLocally(it.map(messageDtoToEntityMapper::map))
               }
    }

}