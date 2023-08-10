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

    //    if (isByCodePeoduct && CodeProduct != null) {
//        repository.getDataProductByKodeProduct(CodeProduct)
//    }
//    else {
//        repository.getListDataProduct()
//    }
    override fun addProduct(product: ProductModel) {
        repository.addProduct(product)
    }

    override fun updateProduct(product: ProductModel) {
        repository.updateProduct(product)
    }

    override fun deleteProduct(product: ProductModel) {
        repository.deleteProduct(product)
    }

}