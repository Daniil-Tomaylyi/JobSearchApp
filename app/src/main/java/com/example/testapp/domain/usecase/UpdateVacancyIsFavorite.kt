package com.example.testapp.domain.usecase

import com.example.testapp.domain.repository.TestRepository

class UpdateVacancyIsFavorite(private val testRepository: TestRepository) {

    suspend fun execute(isFavorite: Boolean, id: String) {
        testRepository.updateIsFavorite(isFavorite, id)
    }
}