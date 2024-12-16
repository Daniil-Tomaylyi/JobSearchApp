package com.example.testapp.domain.usecase


import com.example.testapp.domain.repository.TestRepository
import javax.inject.Inject

class RefreshVacanciesUseCase @Inject constructor(private val testRepository: TestRepository) {

    suspend fun execute(){
        return testRepository.fetchVacancies()
    }

}