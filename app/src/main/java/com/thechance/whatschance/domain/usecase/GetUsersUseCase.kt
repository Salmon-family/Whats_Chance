package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.data.repository.WhatsChanceRepository
import com.thechance.whatschance.data.response.UserDto
import com.thechance.whatschance.domain.mappers.UserMapper
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val whatsChanceRepository: WhatsChanceRepository
) {
    suspend operator fun invoke(): Flow<List<User>> {
        return userRepository.getUsers(getCurrentUser()?.uid ?: "").map { it.map(userMapper::map) }
    }

    suspend fun getContact(): Flow<List<UserDto>> {
        return whatsChanceRepository.getUsers(getCurrentUser()?.uid ?: "").map {
            it.map {
                UserDto(
                    uId = it.uId,
                    name = it.name,
                    phoneNumber = it.phoneNumber
                )
            }
        }
    }
}