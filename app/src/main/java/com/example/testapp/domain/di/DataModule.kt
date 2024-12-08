package com.example.testapp.domain.di

import android.app.Application

import androidx.room.Room
import com.example.testapp.data.database.TestDatabase
import com.example.testapp.data.database.TestDatabaseDao
import com.example.testapp.data.network.TestService
import com.example.testapp.data.repository.TestRepositoryImpl
import com.example.testapp.domain.repository.TestRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideNewsService(moshi: Moshi): TestService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .build()
        return retrofit.create()
    }

    @Provides
    fun provideMoshi(): Moshi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return moshi
    }

    @Provides
    @Singleton
        fun provideDatabase(application: Application): TestDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            TestDatabase::class.java,
            "test"
        )
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideTestDatabaseDao(database: TestDatabase): TestDatabaseDao {
        return database.testDatabaseDao
    }

    @Provides
    fun provideTestRepository(
        testService: TestService,
        testDatabaseDao: TestDatabaseDao
    ): TestRepository {
        return  TestRepositoryImpl(testService = testService, testDatabaseDao = testDatabaseDao)
    }
}