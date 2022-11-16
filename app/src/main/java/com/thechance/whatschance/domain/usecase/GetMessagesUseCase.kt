package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageDtoToEntityMapper
import com.thechance.whatschance.domain.mappers.MessageEntityMapper
import com.thechance.whatschance.domain.mappers.MessageMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val messageDtoToEntityMapper: MessageDtoToEntityMapper,
    private val messageEntityMapper: MessageEntityMapper
) {
    suspend operator fun invoke(senderId: String): Flow<List<Message>> {
        return chatRepository.getMessages(getCurrentUser()?.uid ?: "", senderId)
            .flatMapConcat {
                chatRepository.saveMessagesLocally(it.map(messageDtoToEntityMapper::map))
                chatRepository.getLocalMessages(senderId).map { it.map(messageEntityMapper::map) }
            }
    }

}