package com.example.testapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.domain.repository.TestRepository
import timber.log.Timber

class MoreVacanciesUseCase(private val testRepository: TestRepository) {

    private val _vacanciesCount = MutableLiveData<Int?>()
    private val vacanciesCount: LiveData<Int?> get() = _vacanciesCount

    fun execute(): LiveData<Int?> {
        _vacanciesCount.value = testRepository.vacancies.value?.size ?: 0
        Timber.d("vacanciesCount: ${_vacanciesCount.value}")
        return vacanciesCount
    }
}