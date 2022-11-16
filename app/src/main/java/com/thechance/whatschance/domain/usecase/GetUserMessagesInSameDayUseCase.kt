package com.thechance.whatschance.domain.usecase

import android.util.Log
import android.widget.TextView
import com.thechance.whatschance.data.repository.ChatRepository
import java.util.*
import javax.inject.Inject


class GetUserMessagesInSameDayUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    suspend operator fun invoke(userId: String, messageDate: String) {
        repository.getUserMessagesInSameDay(userId, messageDate)
    }

    private fun getUserMessagesInSameDay(userId: String, messageDate: String) {
        val cal = Calendar.getInstance()

        cal.add(Calendar.DAY_OF_YEAR, 6 * daysCounter.value!!)
        val startOfWeek = cal.time.time
        Log.e("TAG", "start ${cal.get(Calendar.DAY_OF_MONTH)}  ${cal.get(Calendar.MONTH)}")
        cal.add(Calendar.DAY_OF_YEAR, 6 * -1)
        val endOfWeek = cal.time.time
        Log.e("TAG", "end ${cal.get(Calendar.DAY_OF_MONTH)}  ${cal.get(Calendar.MONTH)}")
    }
}