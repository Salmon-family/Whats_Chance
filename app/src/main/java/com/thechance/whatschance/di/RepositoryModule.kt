package com.karrar.movieapp.di

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.data.repository.ChatRepositoryImp
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
    abstract fun bindChatRepository(
        movieRepositoryImp: ChatRepositoryImp
    ): ChatRepository
}