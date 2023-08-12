package com.faezolfp.enstoreapp.home

import androidx.lifecycle.ViewModel
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){
    val getGrettingText = useCase.getGreetingText()
    val getIsNight = useCase.getIsNIght()
}