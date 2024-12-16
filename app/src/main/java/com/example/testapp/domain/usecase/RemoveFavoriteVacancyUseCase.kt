package com.example.testapp.domain.usecase


import com.example.testapp.domain.repository.TestRepository
import javax.inject.Inject

class RemoveFavoriteVacancyUseCase @Inject constructor(private val testRepository: TestRepository) {

    suspend fun execute(vacancyId: String){
        testRepository.delVacancy(vacancyId)
    }
}