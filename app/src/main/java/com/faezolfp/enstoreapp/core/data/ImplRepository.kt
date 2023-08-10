package com.faezolfp.enstoreapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.faezolfp.enstoreapp.core.data.local.LocalDataSource
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.domain.repository.Repository
import com.faezolfp.enstoreapp.core.utils.DataMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImplRepository @Inject constructor(private val localDataSource: LocalDataSource) :
    Repository {
    override fun example() {
        TODO("Not yet implemented")
    }

    override fun getListDataProduct(): LiveData<List<ProductModel>> {
        return Transformations.map(localDataSource.getListDataProduct()) {
            DataMapper.mapperListFromEntityToModel(it)
        }
    }

    override fun addProduct(product: ProductModel) {
        localDataSource.addProduct(DataMapper.mapperFromModelToEntity(product))
    }

    override fun updateProduct(product: ProductModel) {
        localDataSource.updateProduct(DataMapper.mapperFromModelToEntity(product))
    }

    override fun deleteProduct(product: ProductModel) {
        localDataSource.deleteProduct(DataMapper.mapperFromModelToEntity(product))
    }

    override fun getDataProductByKodeProduct(kode: String): LiveData<List<ProductModel>> {
        return Transformations.map(localDataSource.getDataProductByKodeProduct(kode)) {
            DataMapper.mapperListFromEntityToModel(it)
        }
    }

}