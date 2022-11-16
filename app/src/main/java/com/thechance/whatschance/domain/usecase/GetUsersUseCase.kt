package com.thechance.whatschance.domain.usecase

import android.util.Log
import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserDtoMapper
import com.thechance.whatschance.domain.mappers.UserEntityMapper
import com.thechance.whatschance.domain.mappers.UserMapper
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userDtoMapper: UserDtoMapper,
    private val userMapper: UserMapper,
    private val userEntityMapper: UserEntityMapper,
    private val getCurrentUser: GetCurrentUserUseCase,
) {
    suspend operator fun invoke(): Flow<List<User>> {
        val users = userRepository
            .getUsers(getCurrentUser()?.uid ?: "")
            .map { it.map(userDtoMapper::map) }
        saveUsers(users)
        return users
    }

    private fun saveUsers(users: Flow<List<User>>) {
        users.map { userRepository.saveUsers(it.map(userMapper::map)) }
    }


    private suspend fun getUsersFromLocalData(): Flow<List<User>> {
        return userRepository.getSavedUsers().map { it.map(userEntityMapper::map) }
    }

}