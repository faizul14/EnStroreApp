package com.faezolfp.enstoreapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.faezolfp.enstoreapp.core.data.Resource
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun example()

    fun getListDataProduct(): Flow<Resource<List<ProductModel>>>
    suspend fun addProduct(product: ProductModel)
    suspend fun updateProduct(product: ProductModel)
    fun deleteProduct(product: ProductModel)
    fun getDataProductByKodeProduct(kode: String): Flow<Resource<List<ProductModel>>>
    fun getDataProductByNameProduct(name: String): Flow<Resource<List<ProductModel>>>
    fun getGreetingText(): LiveData<String>
    suspend fun saveGrettingText(dataGretting: String)
    fun getIsNIght(): LiveData<Boolean>
    suspend fun saveIsNigth(isNight: Boolean)
}