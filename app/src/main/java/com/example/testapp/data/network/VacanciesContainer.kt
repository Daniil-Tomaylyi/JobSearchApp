package com.example.testapp.data.network

import com.example.testapp.data.database.AddressVacancy
import com.example.testapp.data.database.DatabaseFavoriteVacancies
import com.example.testapp.data.database.DatabaseVacancies
import com.example.testapp.data.database.ExperienceVacancy
import com.example.testapp.data.database.SalaryVacancy
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VacanciesContainer(
    val id: String,
    val lookingNumber: Long?,
    val title: String,
    val address: AddressContainer,
    val company: String,
    val experience: ExperienceContainer,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: SalaryContainer,
    val schedules: List<String>,
    val appliedNumber: Long?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)

@JsonClass(generateAdapter = true)
data class SalaryContainer(
    val short: String?,
    val full: String
)

@JsonClass(generateAdapter = true)
data class ExperienceContainer(
    val previewText: String,
    val text: String
)

@JsonClass(generateAdapter = true)
data class AddressContainer(
    val town: String,
    val street: String,
    val house: String
)

fun AddressContainer.asDatabaseModel() = AddressVacancy(town = town, street = street, house = house)

fun ExperienceContainer.asDatabaseModel() = ExperienceVacancy(previewText = previewText, text = text)

fun SalaryContainer.asDatabaseModel() = SalaryVacancy(short = short, full = full)

fun List<VacanciesContainer>.asDatabaseModel(): List<DatabaseVacancies>{
    return map {  DatabaseVacancies(
        id = it.id,
        lookingNumberVacancy = it.lookingNumber,
        titleVacancy = it.title,
        addressVacancy = it.address.asDatabaseModel(),
        nameCompanyVacancy = it.company,
        experienceVacancy = it.experience.asDatabaseModel(),
        publishedDate = it.publishedDate,
        isFavorite = it.isFavorite,
        salaryVacancy = it.salary.asDatabaseModel(),
        schedulesVacancy = it.schedules,
        appliedNumber = it.appliedNumber,
        description = it.description,
        responsibilities = it.responsibilities,
        questions = it.questions
    )
    }
}

fun List<VacanciesContainer>.asDatabaseFavoriteModel(): List<DatabaseFavoriteVacancies>{
    return map {  DatabaseFavoriteVacancies(
        vacancyId = it.id,
        lookingNumberFavoriteVacancy = it.lookingNumber,
        titleFavoriteVacancy = it.title,
        townFavoriteVacancy = it.address.town,
        nameCompanyFavoriteVacancy = it.company,
        experienceFavoriteVacancy = it.experience.previewText,
        publishedDateFavoriteVacancy = it.publishedDate,
        isFavorite = it.isFavorite,
    )
    }
}


