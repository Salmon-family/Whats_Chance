package com.thechance.whatschance.domain.usecases

import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserMapper
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {
    suspend operator fun invoke() : Flow<List<User>> {
        return userRepository.getUsers().map{ it.map(userMapper::map) }
    }
}