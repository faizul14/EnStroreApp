package com.faezolfp.enstoreapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.domain.repository.Repository
import javax.inject.Inject

class ImplUseCase @Inject constructor(private val repository: Repository) : UseCase {
    override fun example() {
        repository.example()
    }

    override fun getListDataProduct(
        isByCodePeoduct: Boolean,
        CodeProduct: String?,
        nameProduct: String?
    ): LiveData<List<ProductModel>> = when {
        isByCodePeoduct && CodeProduct != null -> {
            repository.getDataProductByKodeProduct(CodeProduct)
        }
        nameProduct != null -> {
            repository.getDataProductByNameProduct(nameProduct)
        }
        else -> {
            repository.getListDataProduct()
        }
    }
    override fun addProduct(product: ProductModel, isEdit: Boolean) {
        if (isEdit){
            repository.updateProduct(product)
        }else{
            repository.addProduct(product)
        }
    }

    override fun updateProduct(product: ProductModel) {
        repository.updateProduct(product)
    }

    override fun deleteProduct(product: ProductModel) {
        repository.deleteProduct(product)
    }

    override fun getGreetingText(): LiveData<String> {
        return repository.getGreetingText()
    }

    override suspend fun saveGrettingText(dataGretting: String) {
        repository.saveGrettingText(dataGretting)
    }

    override fun getIsNIght(): LiveData<Boolean> {
        return repository.getIsNIght()
    }

    override suspend fun saveIsNigth(isNight: Boolean) {
        repository.saveIsNigth(isNight)
    }

}