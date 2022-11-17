package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.mappers.MessageDateMapper
import com.thechance.whatschance.domain.models.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class GetUserMessagesInSameDayUseCase @Inject constructor(
    private val repository: ChatRepository,
    private val messageMapper: MessageDateMapper,
) {
    suspend operator fun invoke(userId: String): Flow<List<Message>> {
        return repository.getUserMessagesInSameDay(userId, getCalenderDateAllLastDays()).map { it.map(messageMapper::map) }
    }
    
    fun getCalenderDateAllLastDays(): List<String> {
        val calender = Calendar.getInstance()
        val dates = mutableListOf<String>()
        for (i in 0..365) {
            calender.add(Calendar.DAY_OF_YEAR, -1)
            dates.add(calender.time.toString())
        }
        return dates
    }
}
