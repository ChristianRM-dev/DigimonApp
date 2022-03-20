package com.example.digimonapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.digimonapp.core.config.di.Constants
import com.example.digimonapp.data.database.DigimonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME = Constants.DATABASE_NAME

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DigimonDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db: DigimonDatabase) = db.getDigimonDao()
}