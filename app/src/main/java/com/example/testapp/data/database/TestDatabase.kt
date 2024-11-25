package com.example.testapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [DatabaseFavoriteVacancies::class, DatabaseOffers::class, ButtonOffers::class,
        DatabaseVacancies::class, SalaryVacancy::class, ExperienceVacancy::class,
        AddressVacancy::class],
    version = 4
)
@TypeConverters(Converters::class)
abstract class TestDatabase : RoomDatabase() {
    abstract val testDatabaseDao: TestDatabaseDao
}