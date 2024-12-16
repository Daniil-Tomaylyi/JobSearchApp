package com.example.testapp.domain.usecase


import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.repository.TestRepository
import javax.inject.Inject

class SetFavoriteVacancyUseCase @Inject constructor(private val testRepository: TestRepository) {

    suspend fun execute(favoriteVacancies: FavoriteVacancies){
        testRepository.insertFavoriteVacancies(favoriteVacancies)
    }
}