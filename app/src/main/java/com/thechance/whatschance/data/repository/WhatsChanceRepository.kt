package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject

class WhatsChanceRepository @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) {

    fun getColorTheme(key: String): Task<Boolean> {
        return remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                remoteConfig.getString(key)
            }
        }
    }
}