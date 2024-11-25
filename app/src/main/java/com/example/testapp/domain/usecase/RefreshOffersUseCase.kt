package com.example.testapp.domain.usecase


import com.example.testapp.domain.repository.TestRepository

class RefreshOffersUseCase(private val testRepository: TestRepository) {

    suspend fun execute(){
        return testRepository.fetchOffers()
    }
}