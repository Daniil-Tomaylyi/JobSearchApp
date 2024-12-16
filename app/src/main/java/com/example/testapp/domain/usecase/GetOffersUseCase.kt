package com.example.testapp.domain.usecase



import androidx.lifecycle.MutableLiveData
import com.example.testapp.domain.models.Offers
import com.example.testapp.domain.repository.TestRepository
import javax.inject.Inject


class GetOffersUseCase @Inject constructor(private val testRepository: TestRepository) {

    fun execute(): MutableLiveData<List<Offers>>
    {
        return testRepository.offers
    }
}