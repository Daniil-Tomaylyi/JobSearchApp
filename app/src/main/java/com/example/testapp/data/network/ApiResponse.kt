package com.example.testapp.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "offers") val offers: List<OffersContainer>,
    @Json(name = "vacancies") val vacancies: List<VacanciesContainer>
)
