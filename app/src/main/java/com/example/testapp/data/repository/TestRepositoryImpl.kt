package com.example.testapp.data.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.testapp.data.database.TestDatabaseDao
import com.example.testapp.data.database.asDatabaseModel
import com.example.testapp.data.database.asDomainModel
import com.example.testapp.data.network.TestService
import com.example.testapp.data.network.asDatabaseModel
import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.models.Offers
import com.example.testapp.domain.models.Vacancies
import com.example.testapp.domain.repository.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class TestRepositoryImpl(
    private val testService: TestService, private val testDatabaseDao: TestDatabaseDao,
) : TestRepository() {

    override val offers: MutableLiveData<List<Offers>> = testDatabaseDao.getAllOffers().map {
        it.asDomainModel()
    } as MutableLiveData<List<Offers>>
    override val vacancies: MutableLiveData<List<Vacancies>> =
        testDatabaseDao.getAllVacancies().map {
            it.asDomainModel()
        } as MutableLiveData<List<Vacancies>>

    override suspend fun fetchOffers() {

        try {
            withContext(Dispatchers.IO) {
                val response = testService.getOffersAndVacancies()
                testDatabaseDao.insertOffers(*response.offers.asDatabaseModel().toTypedArray())
            }
        } catch (e: Exception) {
            Timber.tag("TestRepository").e(e, "Error fetching offers and vacancies")
        }
    }

    override suspend fun fetchVacancies() {
        try {
            withContext(Dispatchers.IO) {
                val response = testService.getOffersAndVacancies()
                testDatabaseDao.insertVacancies(
                    *response.vacancies.asDatabaseModel().toTypedArray()
                )
            }
        } catch (e: Exception) {
            Timber.tag("TestRepository").e(e, "Error fetching offers and vacancies")
        }
    }

    override suspend fun insertFavoriteVacancies(favoriteVacancies: FavoriteVacancies) {
        Timber.d("insert bd: $favoriteVacancies")
        withContext(Dispatchers.IO) {
            testDatabaseDao.insertFavoriteVacancies(favoriteVacancies.asDatabaseModel())
        }
    }

    override suspend fun delVacancy(vacancyId: String) {
        Timber.d("delete in bd vacancyId: $vacancyId")
        withContext(Dispatchers.IO) {
            testDatabaseDao.deleteVacancy(vacancyId)
        }
    }

    override suspend fun getAllFavoriteVacancies(): List<FavoriteVacancies> {
        return withContext(Dispatchers.IO) {
            testDatabaseDao.getAllFavoriteVacancies().asDomainModel()
        }
    }

}
