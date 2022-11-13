package com.thechance.whatschance.data.repository

import com.thechance.whatschance.data.FireStoreDataSource
import com.thechance.whatschance.data.response.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserRepository {
    suspend fun getUsers(uId: String): Flow<List<UserDto>>
}

class UserRepositoryImp @Inject constructor(
    private val fireStoreDataSource: FireStoreDataSource,
) : UserRepository {
    override suspend fun getUsers(uId:String): Flow<List<UserDto>> {
        return fireStoreDataSource.getUsers(uId)
    }
}