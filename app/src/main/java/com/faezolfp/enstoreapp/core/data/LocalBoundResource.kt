package com.faezolfp.enstoreapp.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class LocalBoundResource<ResultType>{
    private val result : Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        emitAll(loadFromDb().map {
            Resource.Success(it)
        })
    }
    protected abstract fun loadFromDb(): Flow<ResultType>
    fun asFlow(): Flow<Resource<ResultType>> = result
}