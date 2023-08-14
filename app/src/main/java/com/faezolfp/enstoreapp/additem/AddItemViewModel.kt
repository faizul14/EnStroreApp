package com.faezolfp.enstoreapp.additem

import androidx.lifecycle.ViewModel
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    fun saveProduct(product: ProductModel, isEdit: Boolean) = useCase.addProduct(product, isEdit)
}