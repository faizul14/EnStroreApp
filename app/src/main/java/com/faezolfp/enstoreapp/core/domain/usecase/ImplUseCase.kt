package com.faezolfp.enstoreapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.domain.repository.Repository
import javax.inject.Inject

class ImplUseCase @Inject constructor(private val repository: Repository): UseCase {
    override fun example() {
        repository.example()
    }

    override fun getListDataProduct(): LiveData<List<ProductModel>> {
        return repository.getListDataProduct()
    }

    override fun addProduct(product: ProductModel) {
        repository.addProduct(product)
    }

    override fun updateProduct(product: ProductModel) {
        repository.updateProduct(product)
    }

    override fun deleteProduct(product: ProductModel) {
        repository.deleteProduct(product)
    }

    override fun getDataProductByKodeProduct(kode: String): ProductModel {
        return repository.getDataProductByKodeProduct(kode)
    }
}