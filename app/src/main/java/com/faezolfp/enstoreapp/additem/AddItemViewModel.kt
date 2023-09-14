package com.faezolfp.enstoreapp.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {
    fun saveProduct(product: ProductModel, isEdit: Boolean) = viewModelScope.launch {
        useCase.addProduct(product, isEdit)
    }

    fun deleteProduct(product: ProductModel) = useCase.deleteProduct(product)
}