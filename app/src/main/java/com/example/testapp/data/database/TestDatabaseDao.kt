package com.example.testapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteVacancies(favoriteVacancies: DatabaseFavoriteVacancies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOffers(vararg offers: DatabaseOffers)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVacancies(vararg vacancies: DatabaseVacancies)

    @Query("Select * from favorite_vacancies_table order by vacancyId DESC")
    fun getAllFavoriteVacancies(): List<DatabaseFavoriteVacancies>

    @Query("DELETE from favorite_vacancies_table where vacancyId == :key")
    fun deleteVacancy(key: String)

    @Query("Select * from vacancies_table order by id DESC")
    fun getAllVacancies(): LiveData<List<DatabaseVacancies>>

    @Query("Select * from offers_table order by id ASC")
    fun getAllOffers(): LiveData<List<DatabaseOffers>>

    @Query("SELECT COUNT(*) FROM vacancies_table")
    fun getCountVacancies(): LiveData<Int>
}