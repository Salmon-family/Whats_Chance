package com.thechance.whatschance.di

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Room
import com.thechance.whatschance.data.local.contact.ContactDao
import com.thechance.whatschance.data.local.contact.ContactDataBase
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
    fun providesRoomDatabase(
        @ApplicationContext context: Context,
    ): ContactDataBase =
        Room.databaseBuilder(context, ContactDataBase::class.java, "CONTACT_DATABASE")
            .build()

    @Singleton
    @Provides
    fun provideContactDao(contactDataBase: ContactDataBase): ContactDao {
        return contactDataBase.contactDao()
    }
}