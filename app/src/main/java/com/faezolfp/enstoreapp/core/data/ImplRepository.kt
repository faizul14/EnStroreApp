package com.faezolfp.enstoreapp.core.data

import com.faezolfp.enstoreapp.core.data.local.RemoteDataSource
import com.faezolfp.enstoreapp.core.domain.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImplRepository @Inject constructor(private val remoteDataSource: RemoteDataSource): Repository {
    override fun example() {
        TODO("Not yet implemented")
    }
}