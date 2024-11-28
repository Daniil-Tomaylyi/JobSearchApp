package com.example.testapp.data.network

import com.example.testapp.data.database.ButtonOffers
import com.example.testapp.data.database.DatabaseOffers
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ButtonContainer(
    val text: String
)

@JsonClass(generateAdapter = true)
data class OffersContainer(
    val id: String?,
    val title: String,
    val button: ButtonContainer?,
    val link: String
)

fun ButtonContainer.asDatabaseButton() = ButtonOffers(text = this.text)

fun List<OffersContainer>.asDatabaseModel(): List<DatabaseOffers> {
    return map {
        DatabaseOffers(
            offersId = it.id,
            titleOffers = it.title,
            buttonOffers = it.button?.asDatabaseButton(),
            linkOffers = it.link
        )
    }
}



