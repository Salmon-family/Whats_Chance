package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.WhatsChanceRepository
import com.thechance.whatschance.data.response.Message
import javax.inject.Inject

class SendStickersUseCase @Inject constructor(
    private val whatsChanceRepository: WhatsChanceRepository,
) {
    suspend operator fun invoke(message: Message) {
        whatsChanceRepository.sendSticker(message)
    }
}