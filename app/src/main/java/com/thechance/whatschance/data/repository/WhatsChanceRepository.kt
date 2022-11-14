package com.thechance.whatschance.data.repository


interface WhatsChanceRepository {
    suspend fun getColorTheme(key: String): String
}