package com.thechance.whatschance.domain.usecases

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.models.Message
import javax.inject.Inject

class SaveMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(id: String, text: String){
        chatRepository.insertMessage(
            Message(
                id = id,
                textMessage = text
            )
        )
    }
}