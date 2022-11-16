package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageMapper
import com.thechance.whatschance.domain.mappers.MessageToEntityMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageMapper: MessageMapper,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val messageToEntityMapper: MessageToEntityMapper
) {
    suspend operator fun invoke(senderId: String): Flow<List<Message>> {
        val messageFirebase = chatRepository.getMessages(getCurrentUser()?.uid ?: "", senderId)
            .map { it.map(messageMapper::map) }

        val x = messageFirebase.map {
            chatRepository.saveMessageLocally(it.map(messageToEntityMapper::map))
            it.toMutableList()
        }
        return x
    }

}