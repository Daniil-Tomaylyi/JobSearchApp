package com.example.testapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testapp.domain.models.Address
import com.example.testapp.domain.models.Experience
import com.example.testapp.domain.models.Salary
import com.example.testapp.domain.models.Vacancies


@Entity(tableName = "vacancies_table")
data class DatabaseVacancies(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "looking_number")
    val lookingNumberVacancy: Long?,
    @ColumnInfo(name = "title_vacancy")
    val titleVacancy: String,
    @Embedded val addressVacancy: AddressVacancy,
    @ColumnInfo(name = "name_company")
    val nameCompanyVacancy: String,
    @Embedded val experienceVacancy: ExperienceVacancy,
    @ColumnInfo(name = "published_date")
    val publishedDate: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,
    @Embedded val salaryVacancy: SalaryVacancy,
    @ColumnInfo(name = "schedules_vacancy")
    val schedulesVacancy: List<String>,
    @ColumnInfo(name = "applied_number")
    val appliedNumber: Long?,
    @ColumnInfo(name = "description_vacancy")
    val description: String?,
    @ColumnInfo(name = "responsibilities_vacancy")
    val responsibilities: String,
    @ColumnInfo(name = "questions_vacancy")
    val questions: List<String>
)

@Entity(tableName = "salary_vacancy")
data class SalaryVacancy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "salary_id")
    val id: Long = 0L,
    @ColumnInfo(name = "short_salary_vacancy")
    val short: String?,
    @ColumnInfo(name = "full_salary_vacancy")
    val full: String
)


@Entity(tableName = "experience_vacancy")
data class ExperienceVacancy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "experience_id")
    val id: Long = 0L,
    @ColumnInfo(name = "preview_text_experience_vacancy")
    val previewText: String,
    @ColumnInfo(name = "text_experience_vacancy")
    val text: String
)


@Entity(tableName = "address_vacancy")
data class AddressVacancy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "address_id")
    val id: Long = 0L,
    @ColumnInfo(name = "town_vacancy")
    val town: String,
    @ColumnInfo(name = "street_vacancy")
    val street: String,
    @ColumnInfo(name = "house_vacancy")
    val house: String
)

fun List<DatabaseVacancies>.asDomainModel(): List<Vacancies> {
    return map {
        Vacancies(
            id = it.id,
            lookingNumber = it.lookingNumberVacancy,
            title = it.titleVacancy,
            address = it.addressVacancy.asDomainModel(),
            company = it.nameCompanyVacancy,
            experience = it.experienceVacancy.asDomainModel(),
            publishedDate = it.publishedDate,
            isFavorite = it.isFavorite,
            salary = it.salaryVacancy.asDomainModel(),
            schedules = it.schedulesVacancy,
            appliedNumber = it.appliedNumber,
            description = it.description,
            responsibilities = it.responsibilities,
            questions = it.questions
        )
    }
}

fun AddressVacancy.asDomainModel() = Address(town, street, house)

fun ExperienceVacancy.asDomainModel() = Experience(previewText, text)

fun SalaryVacancy.asDomainModel() = Salary(short, full)
