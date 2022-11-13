package com.thechance.whatschance.di

import com.thechance.whatschance.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @ViewModelScoped
    @Binds
    abstract fun bindAAuthenticationRepository(
        authenticationRepositoryImp: AuthenticationRepositoryImp
    ): AuthenticationRepository


    @ViewModelScoped
    @Binds
    abstract fun bindWhatsChanceRepository(
        whatsChanceRepositoryImp: WhatsChanceRepositoryImp
    ): WhatsChanceRepository

    @ViewModelScoped
    @Binds
    abstract fun bindAUserRepository(
        userRepository: UserRepositoryImp,
    ): UserRepository

    @ViewModelScoped
    @Binds
    abstract fun bindAChatRepository(
        chatRepository: ChatRepositoryImp,
    ): ChatRepository
}