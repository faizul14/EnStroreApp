package com.faezolfp.enstoreapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.enstoreapp.core.domain.model.ProductModel

interface UseCase {
    fun example()
    fun getListDataProduct(
        isByCodePeoduct: Boolean,
        CodeProduct: String?,
        nameProduct: String?
    ): LiveData<List<ProductModel>>

    fun addProduct(product: ProductModel, isEdit: Boolean)
    fun updateProduct(product: ProductModel)
    fun deleteProduct(product: ProductModel)
    fun getGreetingText(): LiveData<String>
    suspend fun saveGrettingText(dataGretting: String)
    fun getIsNIght(): LiveData<Boolean>
    suspend fun saveIsNigth(isNight: Boolean)
}