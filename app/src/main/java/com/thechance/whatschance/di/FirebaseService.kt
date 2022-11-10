package com.thechance.whatschance.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseService {

    @Singleton
    @Provides
    fun providerRemoteConfigFirebase(): FirebaseRemoteConfig {
        return Firebase.remoteConfig
    }
}