package com.faezolfp.enstoreapp.core.data

import androidx.lifecycle.LiveData
import com.faezolfp.enstoreapp.core.data.local.LocalDataSource
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.domain.repository.Repository
import com.faezolfp.enstoreapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImplRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {
    override fun example() {
        TODO("Not yet implemented")
    }

    override fun getListDataProduct(): Flow<Resource<List<ProductModel>>> {
        return object : LocalBoundResource<List<ProductModel>>() {
            override fun loadFromDb(): Flow<List<ProductModel>> {
                return localDataSource.getListDataProduct().map {
                    DataMapper.mapperListFromEntityToModel(it)
                }
            }
        }.asFlow()
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
        object : LocalBoundResource<List<ProductModel>>() {
            override fun loadFromDb(): Flow<List<ProductModel>> {
                return localDataSource.getDataProductByKodeProduct(kode).map {
                    DataMapper.mapperListFromEntityToModel(it)
                }
            }
        }.asFlow()

    override fun getDataProductByNameProduct(name: String): Flow<Resource<List<ProductModel>>> {
        return object : LocalBoundResource<List<ProductModel>>() {
            override fun loadFromDb(): Flow<List<ProductModel>> {
                return localDataSource.getDataProductByNameProduct(name).map {
                    DataMapper.mapperListFromEntityToModel(it)
                }
            }
        }.asFlow()
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