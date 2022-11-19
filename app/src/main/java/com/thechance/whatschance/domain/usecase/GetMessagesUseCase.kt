package com.thechance.whatschance.domain.usecase

import android.util.Log
import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageDtoToEntityMapper
import com.thechance.whatschance.domain.mappers.MessageEntityMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val chatRepository: ChatRepository,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val messageDtoToEntityMapper: MessageDtoToEntityMapper,
    private val messageEntityMapper: MessageEntityMapper,
    private val decryptText: DecryptTextUseCase,
) {
    suspend operator fun invoke(senderId: String): Flow<List<Message>> {
        return chatRepository.getLocalMessages(senderId).map { it.map(messageEntityMapper::map) }
    }

    fun refreshMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            chatRepository.refreshMessages(getCurrentUser()?.uid ?: "").collect {
                chatRepository.deleteMessages()
                chatRepository.saveMessagesLocally(it.map{ message ->
                    messageDtoToEntityMapper.map(message.copy(textMessage = decryptText(message.textMessage)))
                })
            }
        }

    }

}