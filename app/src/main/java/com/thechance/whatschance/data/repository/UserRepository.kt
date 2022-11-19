package com.thechance.whatschance.data.repository

import com.thechance.whatschance.data.FireStoreDataSource
import com.thechance.whatschance.data.local.database.dao.UserDao
import com.thechance.whatschance.data.local.database.entity.UserEntity
import com.thechance.whatschance.data.response.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    suspend fun getUsers(uId: String): Flow<List<UserDto>>

    suspend fun saveUsersLocally(user: UserEntity)

    suspend fun getSavedUsers(): Flow<List<UserEntity>>

    suspend fun saveUser(user: UserEntity)

    suspend fun isUserExist(id: String): Boolean

    suspend fun getUser(users: List<String>): Flow<List<UserDto>>

    suspend fun searchUsers(searchQuery: String): Flow<List<UserEntity>>

}

class UserRepositoryImp @Inject constructor(
    private val fireStoreDataSource: FireStoreDataSource,
    private val userDao: UserDao,
) : UserRepository {
    override suspend fun getUsers(uId: String): Flow<List<UserDto>> {
        return fireStoreDataSource.getUsers(uId)
    }

    override suspend fun saveUsersLocally(user: UserEntity) {
        return userDao.insertUser(user)
    }

    override suspend fun getSavedUsers(): Flow<List<UserEntity>> {
        return userDao.getUsers()
    }

    override suspend fun saveUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun isUserExist(id: String): Boolean {
        return userDao.isUserExist(id)
    }

    override suspend fun getUser(users: List<String>): Flow<List<UserDto>> {
        return fireStoreDataSource.getUser(users)
    }


    override suspend fun searchUsers(searchQuery: String): Flow<List<UserEntity>> {
        return userDao.searchUsers(searchQuery)
    }
}