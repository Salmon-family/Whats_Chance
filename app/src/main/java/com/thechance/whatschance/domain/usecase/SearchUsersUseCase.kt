package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserEntityMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserEntityMapper,
) {
    suspend operator fun invoke(searchQuery: String) =
        userRepository.searchUsers(searchQuery).map { it -> it.map { userMapper.map(it) } }
}