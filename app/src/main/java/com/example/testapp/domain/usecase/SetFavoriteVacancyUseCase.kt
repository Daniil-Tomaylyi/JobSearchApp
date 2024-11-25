package com.example.testapp.domain.usecase

import com.example.testapp.data.database.DatabaseFavoriteVacancies
import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.repository.TestRepository

class SetFavoriteVacancyUseCase(private val testRepository: TestRepository) {

    suspend fun execute(favoriteVacancies: FavoriteVacancies){
        testRepository.insertFavoriteVacancies(favoriteVacancies)
    }
}