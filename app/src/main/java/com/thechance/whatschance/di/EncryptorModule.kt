package com.thechance.whatschance.di

import com.thechance.whatschance.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EncryptorModule {

    @Singleton
    @Provides
    fun provideCipher() : Cipher{
        return Cipher.getInstance(TRANSFORMATION)
    }

    @Singleton
    @Provides
    fun provideIvParameterSpec(keys: Keys) : IvParameterSpec {
        return IvParameterSpec(keys.secretIv().toByteArray())
    }

    @Singleton
    @Provides
    fun provideSecretKeySpec(keys: Keys) : SecretKeySpec {
        return SecretKeySpec(keys.secretKey().toByteArray(), ALGORITHM)
    }


    private const val ALGORITHM = "AES"
    private const val BLOCK_MODE = "CBC"
    private const val PADDING = "PKCS5PADDING"
    private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
}