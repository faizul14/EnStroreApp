package com.faezolfp.enstoreapp.ListProduct

import androidx.lifecycle.ViewModel
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListProductViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    val listProduct = useCase.getListDataProduct()
}