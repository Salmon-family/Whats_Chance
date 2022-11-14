package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserMapper
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {

    // check if I can make the filter in firebase??
    suspend operator fun invoke(): Flow<List<User>> {
        return userRepository.getUsers().map {
            it.mapNotNull {
               return@mapNotNull if (it.uId != userRepository.getMyAccount()) {
                    userMapper.map(it)
                } else {
                    null
                }
            }
        }
    }
}