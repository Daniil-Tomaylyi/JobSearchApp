package com.example.testapp.domain.di

import android.app.Application
import com.example.testapp.presentation.all.AllVacanciesFragment
import com.example.testapp.presentation.favorites.FavoritesFragment
import com.example.testapp.presentation.head.HeadFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
@Singleton
interface AppComponent {

    fun inject(fragment: HeadFragment)

    fun inject(allVacanciesFragment: AllVacanciesFragment)

    fun inject(favoritesFragment: FavoritesFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}