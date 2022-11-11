package com.thechance.whatschance.data.repository


interface WhatsChanceRepository {
    fun getColorTheme(key: String): String
}