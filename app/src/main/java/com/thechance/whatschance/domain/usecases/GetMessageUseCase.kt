package com.thechance.whatschance.domain.usecases

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.models.Message
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(): Message{
        return chatRepository.getMessage()
    }
}