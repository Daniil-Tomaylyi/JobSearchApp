package com.example.testapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testapp.domain.models.Button
import com.example.testapp.domain.models.Offers

@Entity(tableName = "offers_table")
data class DatabaseOffers(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "offers_Id")
    val offersId: String?,
    @ColumnInfo(name = "title_offers")
    val titleOffers: String,
    @Embedded val buttonOffers: ButtonOffers?,
    @ColumnInfo(name = "link_offers")
    val linkOffers: String
)

@Entity(tableName = "button_table")
data class ButtonOffers(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "button_id")
    val button_id: Long = 0L,
    @ColumnInfo(name = "text_button")
    val text: String
)

fun List<DatabaseOffers>.asDomainModel(): List<Offers> {
    return map {
        Offers(
            id = it.offersId,
            title = it.titleOffers,
            button = it.buttonOffers?.asDomainModel(),
            link = it.linkOffers
        )
    }
}

fun ButtonOffers.asDomainModel() = Button(text)
