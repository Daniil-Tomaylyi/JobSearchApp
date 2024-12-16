package com.example.testapp.domain.usecase


import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.repository.TestRepository
import timber.log.Timber
import javax.inject.Inject

class GetFavoriteVacanciesUseCase @Inject constructor(private val testRepository: TestRepository) {

    suspend fun execute(): List<FavoriteVacancies> {
        Timber.d("Избранные вакансии ${testRepository.getAllFavoriteVacancies()}")
       return testRepository.getAllFavoriteVacancies()
    }
}