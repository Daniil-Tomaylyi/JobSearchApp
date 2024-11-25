package com.example.testapp.presentation.favorites

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.testapp.R
import com.example.testapp.domain.models.FavoriteVacancies
import com.example.testapp.presentation.formatLookingNumber
import com.example.testapp.presentation.formatPublishedDate

@BindingAdapter("lookingNumberVacancyFavorite")
fun TextView.setLookingNumberVacancyFavorite(item: FavoriteVacancies?) {
    item?.let {
        if (item.lookingNumberVacancies != null) {
            text = formatLookingNumber(item.lookingNumberVacancies!!)
        } else {
            visibility = View.GONE
        }
    }
}

@BindingAdapter("isFavoriteVacancyFavorite")
fun ImageView.setIsFavoriteVacancyFavorite(item: FavoriteVacancies?) {
    item?.let {
        setImageResource(
            if (!item.isFavorite) {
                R.drawable.favorites_icon_default
            } else {
                R.drawable.favorites_true_icon
            }
        )
    }
}

@BindingAdapter("titleVacancyFavorite")
fun TextView.setTitleVacancyFavorite(item: FavoriteVacancies?) {
    item?.let {
        text = item.titleVacancies
    }
}

@BindingAdapter("townVacancyFavorite")
fun TextView.setTownVacancyFavorite(item: FavoriteVacancies?) {
    item?.let {
        text = item.townVacancies
    }
}

@BindingAdapter("companyVacancyFavorite")
fun TextView.setCompanyVacancyFavorite(item: FavoriteVacancies?) {
    item?.let {
        text = item.nameCompanyVacancies
    }
}

@BindingAdapter("previewTextVacancyFavorite")
fun TextView.setPreviewTextVacancyFavorite(item: FavoriteVacancies?) {
    item?.let {
        text = item.experienceVacancies
    }
}

@BindingAdapter("publishedDateVacancyFavorite")
fun TextView.setPublishedDateVacancyFavorite(item: FavoriteVacancies?) {
    item?.let {
        text = formatPublishedDate(item.publishedDate)
    }
}