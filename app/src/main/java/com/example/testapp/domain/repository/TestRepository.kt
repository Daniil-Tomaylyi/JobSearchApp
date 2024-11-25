package com.example.testapp.domain.repository





import androidx.lifecycle.MutableLiveData
import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.models.Offers
import com.example.testapp.domain.models.Vacancies

abstract class TestRepository {

    abstract val offers: MutableLiveData<List<Offers>>

    abstract val vacancies: MutableLiveData<List<Vacancies>>

    abstract suspend fun fetchOffers()

    abstract suspend fun fetchVacancies()

    abstract suspend fun insertFavoriteVacancies(favoriteVacancies: FavoriteVacancies)

    abstract suspend fun delVacancy(vacancyId: String)

    abstract suspend fun getAllFavoriteVacancies(): List<FavoriteVacancies>

}