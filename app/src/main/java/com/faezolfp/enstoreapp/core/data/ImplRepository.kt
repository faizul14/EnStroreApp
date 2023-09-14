package com.faezolfp.enstoreapp.core.data

import androidx.lifecycle.LiveData
import com.faezolfp.enstoreapp.core.data.local.LocalDataSource
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.domain.repository.Repository
import com.faezolfp.enstoreapp.core.utils.DataMapper
import com.faezolfp.enstoreapp.core.utils.GetDataBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImplRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {
    override fun example() {
        TODO("Not yet implemented")
    }

    private fun loadFromDb(
        getDataBy: GetDataBy, kode: String = "0", name: String = "name"
    ): Flow<List<ProductModel>> {
        return when (getDataBy) {
            GetDataBy.BYKODE -> {
                localDataSource.getDataProductByKodeProduct(kode).map {
                    DataMapper.mapperListFromEntityToModel(it)
                }
            }

            GetDataBy.BYNAME -> {
                localDataSource.getDataProductByNameProduct(name).map {
                    DataMapper.mapperListFromEntityToModel(it)
                }
            }

            else -> {
                localDataSource.getListDataProduct().map {
                    DataMapper.mapperListFromEntityToModel(it)
                }
            }
        }
    }

    override fun getListDataProduct(): Flow<Resource<List<ProductModel>>> {
        val result: Flow<Resource<List<ProductModel>>> = flow {
            emit(Resource.Loading())
            val dataFromDb = loadFromDb(GetDataBy.BYNONE)
            emitAll(dataFromDb.map { Resource.Success(it) })
        }
        return result
    }

    override suspend fun addProduct(product: ProductModel) {
        localDataSource.addProduct(DataMapper.mapperFromModelToEntity(product))
    }

    override suspend fun updateProduct(product: ProductModel) {
        localDataSource.updateProduct(DataMapper.mapperFromModelToEntity(product))
    }

    override fun deleteProduct(product: ProductModel) {
        localDataSource.deleteProduct(DataMapper.mapperFromModelToEntity(product))
    }

    override fun getDataProductByKodeProduct(kode: String): Flow<Resource<List<ProductModel>>> =
        flow {
            emit(Resource.Loading())
            emitAll(loadFromDb(GetDataBy.BYKODE, kode = kode).map {
                Resource.Success(it)
            })
        }

    override fun getDataProductByNameProduct(name: String): Flow<Resource<List<ProductModel>>> {
        return flow {
            emit(Resource.Loading())
            emitAll(loadFromDb(GetDataBy.BYNAME, name = name).map {
                Resource.Success(it)
            })
        }
    }


    override fun getGreetingText(): LiveData<String> {
        return localDataSource.getGreetingText()
    }

    override suspend fun saveGrettingText(dataGretting: String) {
        localDataSource.saveGrettingText(dataGretting)
    }

    override fun getIsNIght(): LiveData<Boolean> {
        return localDataSource.getIsNIght()
    }

    override suspend fun saveIsNigth(isNight: Boolean) {
        localDataSource.saveIsNigth(isNight)
    }

}