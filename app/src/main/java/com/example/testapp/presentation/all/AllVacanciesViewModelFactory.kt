package com.example.testapp.presentation.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.domain.usecase.GetVacanciesUseCase
import com.example.testapp.domain.usecase.RefreshOffersUseCase
import com.example.testapp.domain.usecase.RefreshVacanciesUseCase
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase


class AllVacanciesViewModelFactory(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
    private val refreshVacanciesUseCase: RefreshVacanciesUseCase,
    private val removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllVacanciesViewModel::class.java)) {
            return AllVacanciesViewModel(
                getVacanciesUseCase,
                setFavoriteVacancyUseCase,
                refreshVacanciesUseCase,
                removeFavoriteVacancyUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}