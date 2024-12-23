package com.example.testapp.domain.models


data class Vacancies(
    val id: String,
    val lookingNumber: Long?,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    var isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Long?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)

data class Salary(
    val short: String?,
    val full: String
)

data class Experience(
    val previewText: String,
    val text: String
)

data class Address(
    val town: String,
    val street: String,
    val house: String
)