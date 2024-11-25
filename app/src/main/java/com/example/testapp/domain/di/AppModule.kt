package com.example.testapp.domain.di


import android.app.Application
import com.example.testapp.domain.usecase.GetFavoriteVacanciesUseCase
import com.example.testapp.domain.usecase.GetOffersUseCase
import com.example.testapp.domain.usecase.GetVacanciesUseCase
import com.example.testapp.domain.usecase.MoreVacanciesUseCase
import com.example.testapp.domain.usecase.RefreshOffersUseCase
import com.example.testapp.domain.usecase.RefreshVacanciesUseCase
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase
import com.example.testapp.presentation.all.AllVacanciesViewModelFactory
import com.example.testapp.presentation.favorites.FavoritesViewModelFactory
import com.example.testapp.presentation.head.HeadViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideVacanciesViewModelFactory(
        application: Application,
        getOffersUseCase: GetOffersUseCase,
        getVacanciesUseCase: GetVacanciesUseCase,
        refreshVacanciesUseCase: RefreshVacanciesUseCase,
        refreshOffersUseCase: RefreshOffersUseCase,
        moreVacanciesUseCase: MoreVacanciesUseCase,
        setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
        removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase,
    ): HeadViewModelFactory {
        return HeadViewModelFactory(
            application = application,
            getOffersUseCase = getOffersUseCase,
            getVacanciesUseCase = getVacanciesUseCase,
            refreshVacanciesUseCase = refreshVacanciesUseCase,
            refreshOffersUseCase = refreshOffersUseCase,
            moreVacanciesUseCase = moreVacanciesUseCase,
            setFavoriteVacancyUseCase = setFavoriteVacancyUseCase,
            removeFavoriteVacancyUseCase = removeFavoriteVacancyUseCase,
        )
    }

    @Provides
    fun provideAllVacanciesViewModelFactory(
        getVacanciesUseCase: GetVacanciesUseCase,
        setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
        refreshVacanciesUseCase: RefreshVacanciesUseCase,
        removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase
    ): AllVacanciesViewModelFactory {
        return AllVacanciesViewModelFactory(
            getVacanciesUseCase = getVacanciesUseCase,
            setFavoriteVacancyUseCase = setFavoriteVacancyUseCase,
            refreshVacanciesUseCase = refreshVacanciesUseCase,
            removeFavoriteVacancyUseCase = removeFavoriteVacancyUseCase
        )
    }

    @Provides
    fun provideFavoritesViewModelFactory(
        setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
        removeFavoriteVacancyUseCase: RemoveFavoriteVacancyUseCase,
        getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase
    ): FavoritesViewModelFactory {
        return FavoritesViewModelFactory(
            setFavoriteVacancyUseCase = setFavoriteVacancyUseCase,
            removeFavoriteVacancyUseCase = removeFavoriteVacancyUseCase,
            getFavoriteVacanciesUseCase = getFavoriteVacanciesUseCase
        )
    }
}