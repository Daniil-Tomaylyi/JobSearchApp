package com.example.testapp.domain.models


data class Offers(
    val id: String?,
    val title: String,
    val button: Button?,
    val link: String
)

data class Button(
    val text: String
)