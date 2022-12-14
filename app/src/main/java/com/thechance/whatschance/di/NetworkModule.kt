package com.thechance.whatschance.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
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
object NetworkModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun providerRemoteConfigFirebase(): FirebaseRemoteConfig {
        return Firebase.remoteConfig
    }

    @Singleton
    @Provides
    fun provideFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}