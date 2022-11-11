package com.thechance.whatschance.data.repository

import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.models.User

interface ChatRepository {
    suspend fun insertUser(user: User)

    suspend fun sendMessage(message: Message)

    suspend fun getMessage(user1: String, user2: String)
}