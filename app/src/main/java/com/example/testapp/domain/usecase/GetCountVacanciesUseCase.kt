package com.example.testapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.testapp.domain.repository.TestRepository
import javax.inject.Inject

class GetCountVacanciesUseCase @Inject constructor(private val testRepository: TestRepository) {

    fun execute(): LiveData<Int> {
        return testRepository.countVacancies
    }
}