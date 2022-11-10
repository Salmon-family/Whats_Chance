package com.thechance.whatschance.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject

class WhatsChanceRepository @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
) {

    fun getColorTheme(key: String): String {
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                return@addOnCompleteListener
            }
        }
       return remoteConfig.getString(key)
    }
}