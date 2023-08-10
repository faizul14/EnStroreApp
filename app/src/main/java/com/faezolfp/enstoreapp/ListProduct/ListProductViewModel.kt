package com.faezolfp.enstoreapp.ListProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

@HiltViewModel
class ListProductViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    val queryChannel = MutableStateFlow("")

    val trackTextChange2 = queryChannel
        .debounce(300)
//        .distinctUntilChanged()
//        .filter {
//            it.trim().isNotEmpty()
//        }
        .mapLatest {
            it
        }.asLiveData()

    fun listProduct(isByCodePeoduct: Boolean, CodeProduct: String?, nameProduct: String? = null) =
        useCase.getListDataProduct(isByCodePeoduct, CodeProduct, nameProduct)
}