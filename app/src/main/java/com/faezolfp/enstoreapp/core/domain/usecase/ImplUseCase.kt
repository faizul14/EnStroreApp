package com.faezolfp.enstoreapp.core.domain.usecase

import com.faezolfp.enstoreapp.core.domain.repository.Repository
import javax.inject.Inject

class ImplUseCase @Inject constructor(private val repository: Repository): UseCase {
    override fun example() {
        repository.example()
    }
}