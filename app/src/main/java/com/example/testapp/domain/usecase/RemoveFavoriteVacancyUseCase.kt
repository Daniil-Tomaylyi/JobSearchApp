package com.example.testapp.domain.usecase


import com.example.testapp.domain.repository.TestRepository

class RemoveFavoriteVacancyUseCase(private val testRepository: TestRepository) {

    suspend fun execute(vacancyId: String){
        testRepository.delVacancy(vacancyId)
    }
}