package com.example.testapp.domain.usecase

import com.example.testapp.domain.repository.TestRepository
import javax.inject.Inject

class UpdateVacancyIsFavorite @Inject constructor(private val testRepository: TestRepository) {

    suspend fun execute(isFavorite: Boolean, id: String) {
        testRepository.updateIsFavorite(isFavorite, id)
    }
}