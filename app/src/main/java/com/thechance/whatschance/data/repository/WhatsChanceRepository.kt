package com.thechance.whatschance.data.repository

import com.thechance.whatschance.data.response.UserDto
import kotlinx.coroutines.flow.Flow


interface WhatsChanceRepository {
    suspend fun getColorTheme(key: String): String

    // database
    suspend fun getUsers(uId: String): Flow<List<UserDto>>
}