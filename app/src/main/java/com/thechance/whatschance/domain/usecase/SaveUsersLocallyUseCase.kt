package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserMapper
import com.thechance.whatschance.domain.models.User
import javax.inject.Inject

class SaveUsersLocallyUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userToEntityMapper: UserMapper,
) {
    suspend operator fun invoke(user: User) {
        return userRepository.saveUsersLocally(userToEntityMapper.map(user))
    }
}