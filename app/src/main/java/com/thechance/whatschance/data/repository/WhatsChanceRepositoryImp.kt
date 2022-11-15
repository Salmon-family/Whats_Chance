package com.thechance.whatschance.data.repository

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.thechance.whatschance.data.response.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WhatsChanceRepositoryImp @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val userRepository: UserRepository,
) : WhatsChanceRepository {

    override suspend fun getColorTheme(key: String): String {
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                return@addOnCompleteListener
            }
        }
        return remoteConfig.getString(key)
    }

    override suspend fun getUsers(uId: String): Flow<List<UserDto>> {
        return userRepository.getUsers(uId)
    }
}