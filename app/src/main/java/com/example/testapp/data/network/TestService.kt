package com.example.testapp.data.network

import retrofit2.http.GET

interface TestService {
    @GET("uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getOffersAndVacancies(): ApiResponse
}