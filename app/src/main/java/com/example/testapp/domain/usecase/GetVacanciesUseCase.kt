package com.example.testapp.domain.usecase


import androidx.lifecycle.MutableLiveData
import com.example.testapp.domain.models.Vacancies
import com.example.testapp.domain.repository.TestRepository

class GetVacanciesUseCase(private val testRepository: TestRepository) {

     fun execute(): MutableLiveData<List<Vacancies>> {
        return testRepository.vacancies
    }
}