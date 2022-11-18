package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserEntityMapper
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMyFriendsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userEntityMapper: UserEntityMapper,
) {
    suspend operator fun invoke(): Flow<List<User>>{
        return userRepository.getSavedUsers().map { it.map(userEntityMapper::map) }
    }
}