package com.thechance.whatschance.data.repository

import com.thechance.whatschance.data.response.Message
import kotlinx.coroutines.flow.Flow


interface WhatsChanceRepository {

    fun getColorTheme(key: String): String

    suspend fun sendSticker(message: Message)

    suspend fun getStickers(): Flow<List<Message>>
}