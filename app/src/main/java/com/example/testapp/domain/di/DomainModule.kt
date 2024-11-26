package com.example.testapp.domain.di

import com.example.testapp.domain.repository.TestRepository
import com.example.testapp.domain.usecase.GetFavoriteVacanciesUseCase
import com.example.testapp.domain.usecase.GetOffersUseCase
import com.example.testapp.domain.usecase.GetVacanciesUseCase
import com.example.testapp.domain.usecase.GetCountVacanciesUseCase
import com.example.testapp.domain.usecase.RefreshOffersUseCase
import com.example.testapp.domain.usecase.RefreshVacanciesUseCase
import com.example.testapp.domain.usecase.RemoveFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.SetFavoriteVacancyUseCase
import com.example.testapp.domain.usecase.UpdateVacancyIsFavorite
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetOffersUseCase(testRepository: TestRepository): GetOffersUseCase {
        return GetOffersUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideGetVacanciesUseCase(testRepository: TestRepository): GetVacanciesUseCase {
        return GetVacanciesUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideMoreVacanciesUseCase(testRepository: TestRepository): GetCountVacanciesUseCase {
        return GetCountVacanciesUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideSetFavoriteVacancyUseCase(testRepository: TestRepository): SetFavoriteVacancyUseCase {
        return SetFavoriteVacancyUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideRemoveFavoriteVacancyUseCase(testRepository: TestRepository): RemoveFavoriteVacancyUseCase {
        return RemoveFavoriteVacancyUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideGetFavoriteVacanciesUseCase(testRepository: TestRepository): GetFavoriteVacanciesUseCase {
        return GetFavoriteVacanciesUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideRefreshVacanciesUseCase(testRepository: TestRepository): RefreshVacanciesUseCase {
        return RefreshVacanciesUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideRefreshOffersUseCase(testRepository: TestRepository): RefreshOffersUseCase {
        return RefreshOffersUseCase(testRepository = testRepository)
    }

    @Provides
    fun provideUpdateVacancyIsFavorite(testRepository: TestRepository): UpdateVacancyIsFavorite {
        return UpdateVacancyIsFavorite(testRepository = testRepository)
    }

}