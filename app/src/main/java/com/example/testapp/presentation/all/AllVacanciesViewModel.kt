package com.example.testapp.presentation.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.models.APIStatus
import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.models.Vacancies
import com.example.testapp.domain.usecase.GetCountVacanciesUseCase
import com.example.testapp.domain.usecase.GetVacanciesUseCase
import com.example.testapp.domain.usecase.RefreshVacanciesUseCase
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase
import kotlinx.coroutines.launch

class AllVacanciesViewModel(
    getVacanciesUseCase: GetVacanciesUseCase,
    private val setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
    private val refreshVacanciesUseCase: RefreshVacanciesUseCase,
    private val removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase,
    getCountVacanciesUseCase: GetCountVacanciesUseCase,
) : ViewModel() {

    private val _vacancies = getVacanciesUseCase.execute()
    val vacancies: LiveData<List<Vacancies>> get() = _vacancies
    private val _navigateToVacancy = MutableLiveData<String?>()
    val navigateToVacancy get() = _navigateToVacancy
    private val _favoriteVacancy = MutableLiveData<Vacancies?>()
    val favoriteVacancy get() = _favoriteVacancy
    private val _statusVacancies = MutableLiveData<APIStatus>()
    val statusVacancies: LiveData<APIStatus> get() = _statusVacancies
    val vacanciesCount = getCountVacanciesUseCase.execute()
    init {
        getVacancies()
    }

    private fun getVacancies() {
        viewModelScope.launch {
            try {
                refreshVacanciesUseCase.execute()
                _statusVacancies.value = APIStatus.DONE
            }
            catch (e:Exception){
                _statusVacancies.value = APIStatus.ERROR
            }
        }
    }

    fun setFavoriteVacancy(vacancy: Vacancies) {
        viewModelScope.launch {
            setFavoriteVacancyUseCase.execute(
                FavoriteVacancies(
                    vacancy.id,
                    vacancy.isFavorite,
                    vacancy.lookingNumber,
                    vacancy.title,
                    vacancy.address.town,
                    vacancy.company,
                    vacancy.experience.previewText,
                    vacancy.publishedDate
                )
            )
        }
    }

    fun removeVacancyFavorite(vacancy: Vacancies) {
        viewModelScope.launch {
            removeFavoriteVacancyUseCase.execute(vacancy.id)
        }
    }

    fun onVacancyClicked(id: String) {
        _navigateToVacancy.value = id
    }

    fun onVacancyNavigated() {
        _navigateToVacancy.value = null
    }

    fun isFavoriteClicked(clickVacancy: Vacancies) {
        val vacancy = clickVacancy.copy(isFavorite = !clickVacancy.isFavorite)
        _vacancies.value = _vacancies.value?.map {
            if (it.id == clickVacancy.id) {
                it.copy(isFavorite = vacancy.isFavorite)
            } else {
                it
            }
        }
        if (vacancy.isFavorite) {
            setFavoriteVacancy(vacancy)
        } else {
            removeVacancyFavorite(vacancy)
        }
    }

}