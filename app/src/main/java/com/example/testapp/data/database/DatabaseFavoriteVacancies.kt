package com.example.testapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testapp.domain.models.FavoriteVacancies

@Entity(tableName = "favorite_vacancies_table")
data class DatabaseFavoriteVacancies(
    @PrimaryKey
    val vacancyId: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @ColumnInfo(name = "looking_number")
    var lookingNumberFavoriteVacancy: Long?,
    @ColumnInfo(name = "title_favorite")
    val titleFavoriteVacancy: String,
    @ColumnInfo(name = "town_favorite")
    var townFavoriteVacancy: String,
    @ColumnInfo(name = "name_company")
    val nameCompanyFavoriteVacancy: String,
    @ColumnInfo(name = "experience")
    var experienceFavoriteVacancy: String,
    @ColumnInfo(name = "published_date")
    var publishedDateFavoriteVacancy: String
)

fun List<DatabaseFavoriteVacancies>.asDomainModel(): List<FavoriteVacancies> {
    return map {
        FavoriteVacancies(
            vacancyId = it.vacancyId,
            isFavorite = it.isFavorite,
            lookingNumberVacancies = it.lookingNumberFavoriteVacancy,
            titleVacancies = it.titleFavoriteVacancy,
            townVacancies = it.townFavoriteVacancy,
            nameCompanyVacancies = it.nameCompanyFavoriteVacancy,
            experienceVacancies = it.experienceFavoriteVacancy,
            publishedDate = it.publishedDateFavoriteVacancy
        )
    }
}

fun FavoriteVacancies.asDatabaseModel(): DatabaseFavoriteVacancies {
    return DatabaseFavoriteVacancies(
        vacancyId = this.vacancyId,
        isFavorite = this.isFavorite,
        lookingNumberFavoriteVacancy = this.lookingNumberVacancies,
        titleFavoriteVacancy = this.titleVacancies,
        townFavoriteVacancy = this.townVacancies,
        nameCompanyFavoriteVacancy = this.nameCompanyVacancies,
        experienceFavoriteVacancy = this.experienceVacancies,
        publishedDateFavoriteVacancy = this.publishedDate
    )
}