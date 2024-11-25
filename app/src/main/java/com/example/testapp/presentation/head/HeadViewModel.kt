package com.example.testapp.presentation.head

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.models.APIStatus
import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.domain.models.Offers
import com.example.testapp.domain.models.Vacancies
import com.example.testapp.domain.usecase.GetOffersUseCase
import com.example.testapp.domain.usecase.GetVacanciesUseCase
import com.example.testapp.domain.usecase.MoreVacanciesUseCase
import com.example.testapp.domain.usecase.RefreshOffersUseCase
import com.example.testapp.domain.usecase.RefreshVacanciesUseCase
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase
import kotlinx.coroutines.launch
import timber.log.Timber


class HeadViewModel(
    private val application: Application,
    getOffersUseCase: GetOffersUseCase,
    getVacanciesUseCase: GetVacanciesUseCase,
    private val refreshVacanciesUseCase: RefreshVacanciesUseCase,
    private val refreshOffersUseCase: RefreshOffersUseCase,
    moreVacanciesUseCase: MoreVacanciesUseCase,
    private val setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
    private val removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase,
) : ViewModel() {

    private val _offers = getOffersUseCase.execute()
    val offers: LiveData<List<Offers>> get() = _offers
    private val _vacancies = getVacanciesUseCase.execute()
    val vacancies: LiveData<List<Vacancies>> get() = _vacancies
    val vacanciesCount = moreVacanciesUseCase.execute()
    private val _navigateToLink = MutableLiveData<String?>()
    val navigateToLink get() = _navigateToLink
    private val _navigateToVacancy = MutableLiveData<String?>()
    val navigateToVacancy get() = _navigateToVacancy
    private val _statusVacancies = MutableLiveData<APIStatus>()
    val statusVacancies: LiveData<APIStatus> get() = _statusVacancies
    private val _statusOffers = MutableLiveData<APIStatus>()
    val statusOffers: LiveData<APIStatus> get() = _statusOffers

    init {
        refreshOffers()
        refreshVacancies()
        Timber.d("apiStatusOffers в viewmodel после получения данных: ${statusOffers.value}")
    }

    private fun refreshVacancies() {
        if (_vacancies.value == null) {
            _statusVacancies.value = APIStatus.LOADING
        }
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

    private fun refreshOffers() {
        if (_offers.value == null){
            _statusOffers.value = APIStatus.LOADING
        }
        viewModelScope.launch {
            try {
                refreshOffersUseCase.execute()
                _statusOffers.value = APIStatus.DONE
            }
            catch (e:Exception){
                _statusOffers.value = APIStatus.ERROR
            }
        }
    }


    private fun setFavoriteVacancy(vacancy: Vacancies) {
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

    private fun removeVacancyFavorite(vacancy: Vacancies) {
        viewModelScope.launch {
            removeFavoriteVacancyUseCase.execute(vacancy.id)
        }
    }

    fun onOffersClicked(link: String) {
        _navigateToLink.value = link
    }

    fun onLinkNavigated() {
        _navigateToLink.value = null
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