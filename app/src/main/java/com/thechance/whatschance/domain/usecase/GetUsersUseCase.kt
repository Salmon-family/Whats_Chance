package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserDtoMapper
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userDtoMapper: UserDtoMapper,
    private val getCurrentUser: GetCurrentUserUseCase,
) {
    suspend operator fun invoke(): Flow<List<User>> {
        return userRepository
            .getUsers(getCurrentUser()?.uid ?: "")
            .map { it.map(userDtoMapper::map) }
    }
}