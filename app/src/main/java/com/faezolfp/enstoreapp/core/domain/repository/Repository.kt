package com.faezolfp.enstoreapp.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.faezolfp.enstoreapp.core.domain.model.ProductModel

interface Repository {
    fun example()

    fun getListDataProduct(): LiveData<List<ProductModel>>
    fun addProduct(product: ProductModel)
    fun updateProduct(product: ProductModel)
    fun deleteProduct(product: ProductModel)
    fun getDataProductByKodeProduct(kode: String): LiveData<List<ProductModel>>
    fun getDataProductByNameProduct(name: String): LiveData<List<ProductModel>>
    fun getGreetingText(): LiveData<String>
    suspend fun saveGrettingText(dataGretting: String)
}