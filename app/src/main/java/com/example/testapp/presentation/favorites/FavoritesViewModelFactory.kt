package com.example.testapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.GetFavoriteVacanciesUseCase

class FavoritesViewModelFactory(
    private val setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
    private val removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase,
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(
                setFavoriteVacancyUseCase,
                removeFavoriteVacancyUseCase,
                getFavoriteVacanciesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
