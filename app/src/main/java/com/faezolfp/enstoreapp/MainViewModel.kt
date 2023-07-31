package com.faezolfp.enstoreapp

import androidx.lifecycle.ViewModel
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: UseCase): ViewModel() {
    val data = useCase.getListDataProduct()
}