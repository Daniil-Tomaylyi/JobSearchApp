package com.example.testapp.domain.usecase


import com.example.testapp.domain.repository.TestRepository

class RefreshVacanciesUseCase(private val testRepository: TestRepository) {

    suspend fun execute(){
        return testRepository.fetchVacancies()
    }

}