package com.faezolfp.enstoreapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.faezolfp.enstoreapp.core.domain.model.ProductModel

interface Repository {
    fun example()

    fun getListDataProduct(): LiveData<List<ProductModel>>
    fun addProduct(product: ProductModel)
    fun updateProduct(product: ProductModel)
    fun deleteProduct(product: ProductModel)
    fun getDataProductByKodeProduct(kode: String): LiveData<List<ProductModel>>
}