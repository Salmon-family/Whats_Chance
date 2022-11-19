package com.thechance.whatschance.domain.usecase

import android.util.Log
import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.domain.mappers.UserDtoToEntityMapper
import com.thechance.whatschance.domain.mappers.UserEntityMapper
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetFriendsUseCase @Inject constructor(
    private val repository: ChatRepository,
    private val userRepository: UserRepository,
    private val userDtoMapper: UserDtoToEntityMapper,
    private val userEntityMapper: UserEntityMapper
) {
    suspend operator fun invoke(): Flow<List<User>> {
        return userRepository.getSavedUsers().map { it.map(userEntityMapper::map) }
    }

    fun refreshUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getUsersID().collect {
                Log.e("Test", it.toString())
                if (it.isNotEmpty()) {
                    userRepository.getUser(it).collect {
                        Log.e("Test", it.toString())
                        userRepository.saveUser(userDtoMapper.map(it[0]))
                    }
                }
            }
        }
    }

}
