package com.thechance.whatschance.domain.usecases

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.models.Message
import javax.inject.Inject

class SaveMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(id: String, text: String, user1: String, user2: String){
        chatRepository.sendMessage(
            Message(
                id = id,
                textMessage = text,
                chatUsers = listOf(user1,user2)
            )
        )
    }
}