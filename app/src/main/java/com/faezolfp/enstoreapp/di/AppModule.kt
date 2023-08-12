package com.faezolfp.enstoreapp.di

import com.faezolfp.enstoreapp.core.domain.usecase.ImplUseCase
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

abstract class AppModule {
    @Binds
    abstract fun provideUseCase(useCase: ImplUseCase): UseCase
}