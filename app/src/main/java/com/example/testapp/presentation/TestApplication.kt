package com.example.testapp.presentation

import android.app.Application
import com.example.testapp.domain.di.AppComponent
import com.example.testapp.domain.di.DaggerAppComponent
import timber.log.Timber

class   TestApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
            .application(application = this)
            .build()
    }
}