package com.thechance.whatschance.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject

class WhatsChanceRepositoryImp @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : WhatsChanceRepository {

    override suspend fun getColorTheme(key: String): String {
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                return@addOnCompleteListener
            }
        }
        return remoteConfig.getString(key)
    }
}