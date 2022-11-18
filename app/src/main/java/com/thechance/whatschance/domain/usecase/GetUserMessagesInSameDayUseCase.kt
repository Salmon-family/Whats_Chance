package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageDateMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class GetUserMessagesInSameDayUseCase @Inject constructor(
    private val repository: ChatRepository,
    private val messageDateMapper: MessageDateMapper,

    ) {
    suspend operator fun invoke(userId: String): Flow<List<Message>> {
        return repository.getUserMessagesInSameDay(userId)
            .map { it.map(messageDateMapper::map) }
    }
}
