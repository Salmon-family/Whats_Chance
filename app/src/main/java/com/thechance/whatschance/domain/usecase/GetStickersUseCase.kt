package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.WhatsChanceRepository
import com.thechance.whatschance.data.response.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStickersUseCase @Inject constructor(
    private val whatsChanceRepository: WhatsChanceRepository,
) {
    suspend operator fun invoke(): Flow<List<Message>> {
        return whatsChanceRepository.getStickers().map { it }
    }
}