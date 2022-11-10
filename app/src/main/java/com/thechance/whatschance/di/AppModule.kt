package com.thechance.whatschance.di

import com.thechance.whatschance.domain.DataEncryptor
import com.thechance.whatschance.domain.DataEncryptorImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindDataEncryptor(
        dataEncryptor: DataEncryptorImp
    ): DataEncryptor
}