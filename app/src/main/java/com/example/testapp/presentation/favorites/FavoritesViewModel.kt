package com.example.testapp.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.GetFavoriteVacanciesUseCase
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
    private val removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase,
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase
) : ViewModel() {

    private val _favoriteVacancies = MutableLiveData<List<FavoriteVacancies>>()
    val favoriteVacancies: LiveData<List<FavoriteVacancies>> get() = _favoriteVacancies
    private val _navigateToVacancy = MutableLiveData<String?>()
    val navigateToVacancy get() = _navigateToVacancy
    private val _favoriteVacancy = MutableLiveData<FavoriteVacancies?>()
    val favoriteVacancy get() = _favoriteVacancy

    init {
        getFavoritesVacancies()
    }

    private fun getFavoritesVacancies() {
        viewModelScope.launch {
            _favoriteVacancies.value = getFavoriteVacanciesUseCase.execute()
        }

    }

    private fun setFavoriteVacancy(vacancy: FavoriteVacancies) {
        viewModelScope.launch {
            setFavoriteVacancyUseCase.execute(
                FavoriteVacancies(
                    vacancy.vacancyId,
                    vacancy.isFavorite,
                    vacancy.lookingNumberVacancies,
                    vacancy.titleVacancies,
                    vacancy.townVacancies,
                    vacancy.nameCompanyVacancies,
                    vacancy.experienceVacancies,
                    vacancy.publishedDate
                )
            )
        }
    }

    private fun removeVacancyFavorite(vacancy: FavoriteVacancies) {
        viewModelScope.launch {
            removeFavoriteVacancyUseCase.execute(vacancy.vacancyId)
        }
    }

    fun onVacancyClicked(id: String) {
        _navigateToVacancy.value = id
    }

    fun onVacancyNavigated() {
        _navigateToVacancy.value = null
    }

    fun isFavoriteClicked(clickVacancy: FavoriteVacancies) {
        val vacancy = clickVacancy.copy(isFavorite = !clickVacancy.isFavorite)
        _favoriteVacancies.value = _favoriteVacancies.value?.map {
            if (it.vacancyId == clickVacancy.vacancyId) {
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