package com.example.testapp.domain.models


data class FavoriteVacancies(
    val vacancyId: String,
    var isFavorite: Boolean,
    var lookingNumberVacancies: Long?,
    val titleVacancies: String,
    var townVacancies: String,
    val nameCompanyVacancies: String,
    var experienceVacancies: String,
    var publishedDate: String
)