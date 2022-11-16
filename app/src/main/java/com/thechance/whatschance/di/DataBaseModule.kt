package com.thechance.whatschance.di

import android.content.Context
import androidx.room.Room
import com.thechance.whatschance.data.local.database.ChatDataBase
import com.thechance.whatschance.data.local.database.dao.MessageDao
import com.thechance.whatschance.data.local.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providesRoomDataBase(
        @ApplicationContext context: Context,
    ): ChatDataBase =
        Room.databaseBuilder(context, ChatDataBase::class.java, "ChatDataBase")
            .build()

    @Singleton
    @Provides
    fun provideMessageDao(chatDataBase: ChatDataBase): MessageDao{
        return chatDataBase.messageDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(chatDataBase: ChatDataBase): UserDao{
        return chatDataBase.userDao()
    }

}