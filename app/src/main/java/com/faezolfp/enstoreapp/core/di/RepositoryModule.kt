package com.faezolfp.enstoreapp.core.di

import com.faezolfp.enstoreapp.core.data.ImplRepository
import com.faezolfp.enstoreapp.core.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun provideRepository(repository: Repository): ImplRepository
}
