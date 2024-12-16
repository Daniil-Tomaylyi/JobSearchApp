package com.example.testapp.presentation.head

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.domain.usecase.GetOffersUseCase
import com.example.testapp.domain.usecase.GetVacanciesUseCase
import com.example.testapp.domain.usecase.GetCountVacanciesUseCase
import com.example.testapp.domain.usecase.RefreshOffersUseCase
import com.example.testapp.domain.usecase.RefreshVacanciesUseCase
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.UpdateVacancyIsFavorite
import javax.inject.Inject

class HeadViewModelFactory @Inject constructor(
    private val application: Application,
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val refreshVacanciesUseCase: RefreshVacanciesUseCase,
    private val refreshOffersUseCase: RefreshOffersUseCase,
    private val getCountVacanciesUseCase: GetCountVacanciesUseCase,
    private val setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
    private val removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase,
    private val updateVacancyIsFavorite: UpdateVacancyIsFavorite
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeadViewModel::class.java)) {
            return HeadViewModel(
                application,
                getOffersUseCase,
                getVacanciesUseCase,
                refreshVacanciesUseCase,
                refreshOffersUseCase,
                getCountVacanciesUseCase,
                setFavoriteVacancyUseCase,
                removeFavoriteVacancyUseCase,
                updateVacancyIsFavorite
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}