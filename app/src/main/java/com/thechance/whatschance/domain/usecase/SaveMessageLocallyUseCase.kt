package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageToEntityMapper
import com.thechance.whatschance.domain.models.Message
import javax.inject.Inject

//class SaveMessageLocallyUseCase @Inject constructor(
//    private val chatRepository: ChatRepository,
//    private val messageToEntityMapper: MessageToEntityMapper,
//) {
//    suspend operator fun invoke(message: Message){
//        return chatRepository.saveMessageLocally(messageToEntityMapper.map(message))
//    }
//}