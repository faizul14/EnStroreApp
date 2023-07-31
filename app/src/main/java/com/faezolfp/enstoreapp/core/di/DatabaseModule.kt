package com.faezolfp.enstoreapp.core.di

import android.content.Context
import androidx.room.Room
import com.faezolfp.enstoreapp.core.data.local.room.EnstoreDao
import com.faezolfp.enstoreapp.core.data.local.room.EnstoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): EnstoreDatabase = Room.databaseBuilder(
        context,
        EnstoreDatabase::class.java, "Enstore.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideEnstoreDao(database: EnstoreDatabase): EnstoreDao = database.enstoreDao()
}