package com.thechance.whatschance.di

import com.thechance.whatschance.data.repository.AuthenticationRepository
import com.thechance.whatschance.data.repository.AuthenticationRepositoryImp
import com.thechance.whatschance.data.repository.UserRepository
import com.thechance.whatschance.data.repository.UserRepositoryImp
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
    abstract fun bindAUserRepository(
        userRepository: UserRepositoryImp,
    ): UserRepository
}