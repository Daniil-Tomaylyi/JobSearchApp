package com.example.testapp.data.network

import com.example.testapp.data.database.ButtonOffers
import com.example.testapp.data.database.DatabaseOffers
import com.example.testapp.domain.models.Button
import com.example.testapp.domain.models.Offers
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

fun ButtonContainer.asDomainModel() = Button(text)

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

fun List<OffersContainer>.asDomainModel(): List<Offers> {
    return map {
        Offers(
            id = it.id,
            title = it.title,
            button = it.button?.asDomainModel(),
            link = it.link
        )
    }
}


