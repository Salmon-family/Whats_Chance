package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageEntityMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSavedMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageEntityMapper: MessageEntityMapper,
) {
    suspend operator fun invoke(userId: String): Flow<List<Message>>{
        return chatRepository.getSavedUserMessages(userId).map { it.map(messageEntityMapper::map) }
    }
}