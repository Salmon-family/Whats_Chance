package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import java.util.*
import javax.inject.Inject

class GetUserMessagesInSameDayUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(userId: String, messageDate: String) {
        repository.getUserMessagesInSameDay(userId, messageDate)
    }
}
