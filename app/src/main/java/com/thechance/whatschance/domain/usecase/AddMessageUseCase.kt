package com.thechance.whatschance.domain.usecase

import android.util.Log
import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageToDtoMapper
import com.thechance.whatschance.domain.mappers.MessageToEntityMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AddMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageToDtoMapper: MessageToDtoMapper,
    private val messageToEntityMapper: MessageToEntityMapper,
    private val encryptText: EncryptTextUseCase,
) {
    suspend operator fun invoke(uId: String, message: Message) {
        chatRepository.addMessage(uId, messageToDtoMapper.map(message.copy(textMessage =encryptText(message.textMessage))))
            .addOnSuccessListener {
                runBlocking {
                    chatRepository.saveMessagesLocally(listOf(messageToEntityMapper.map(message, uId)))
                }
            }
            .addOnFailureListener {
                throw Throwable("error")
            }

    }
}