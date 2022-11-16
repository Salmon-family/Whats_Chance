package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageToDtoMapper
import com.thechance.whatschance.domain.models.Message
import javax.inject.Inject

class AddMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageToDtoMapper: MessageToDtoMapper,
) {
    operator fun invoke(uId: String, message: Message): Boolean {
        return chatRepository.addMessage(uId, messageToDtoMapper.map(message))
    }
}