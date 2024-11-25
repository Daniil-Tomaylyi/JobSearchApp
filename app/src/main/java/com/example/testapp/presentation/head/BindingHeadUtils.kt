package com.example.testapp.presentation.head

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.testapp.R
import com.example.testapp.domain.models.APIStatus
import com.example.testapp.domain.models.Offers
import com.example.testapp.domain.models.Vacancies
import com.example.testapp.presentation.formatLookingNumber
import com.example.testapp.presentation.formatMoreVacancies
import com.example.testapp.presentation.formatPublishedDate
import timber.log.Timber

@BindingAdapter("lookingNumberVacancy")
fun TextView.setLookingNumberVacancy(item: Vacancies?) {
    item?.let {
        if (item.lookingNumber != null) {
            text = formatLookingNumber(item.lookingNumber)
        } else {
            visibility = View.GONE
        }
    }
}

@BindingAdapter("isFavoriteVacancy")
fun ImageView.setIsFavoriteVacancy(item: Vacancies?) {
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

@BindingAdapter("titleVacancy")
fun TextView.setTitleVacancy(item: Vacancies?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("townVacancy")
fun TextView.setTownVacancy(item: Vacancies?) {
    item?.let {
        text = item.address.town
    }
}

@BindingAdapter("companyVacancy")
fun TextView.setCompanyVacancy(item: Vacancies?) {
    item?.let {
        text = item.company
    }
}

@BindingAdapter("previewTextVacancy")
fun TextView.setPreviewTextVacancy(item: Vacancies?) {
    item?.let {
        text = item.experience.previewText
    }
}

@BindingAdapter("publishedDateVacancy")
fun TextView.setPublishedDateVacancy(item: Vacancies?) {
    item?.let {
        text = formatPublishedDate(item.publishedDate)
    }
}

@BindingAdapter("iconOffers")
fun ImageView.setIconOffers(item: Offers?) {
    item?.let {
        if (item.id != null) {
            setImageResource(
                when (item.id) {
                    "near_vacancies" -> R.drawable.near_vacancies_icon
                    "level_up_resume" -> R.drawable.level_up_resume_icon
                    else -> R.drawable.temporary_job_icon
                }
            )
        } else {
            visibility = View.INVISIBLE
        }
    }
}

@BindingAdapter("titleOffers")
fun TextView.setTitleOffers(item: Offers?) {
    item?.let {
        text = item.title.trim()
        maxLines = if (item.button == null) 3 else 2
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            bottomMargin = if (it.button == null) 16 else 0
        }?.let { updatedParams ->
            layoutParams = updatedParams
        }
    }
}

@BindingAdapter("buttonTextOffers")
fun TextView.setButtonTextOffers(item: Offers?) {
    item?.let {
        if (item.button != null) {
            text = item.button.text
        } else {
            visibility = View.GONE
        }
    }
}

@BindingAdapter("MoreVacancies")
fun Button.setMoreVacancies(count: Int) {
    count.let {
        if (count == 0) {
            visibility = View.INVISIBLE
        }
        else {
            Timber.d("formatMoreVacancies: ${formatMoreVacancies(count)}")
            visibility = View.VISIBLE
            text = formatMoreVacancies(count)
        }
    }
}

@BindingAdapter("statusAPI")
fun ImageView.setStatus(status: APIStatus?){
    Timber.d("apiStatusOffers в viewmodel: $status")
    when (status){
        APIStatus.LOADING -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.loading_animation)
        }
        APIStatus.ERROR ->{
            visibility = View.VISIBLE
            setImageResource(R.drawable.ic_broken_image)
        }
        else ->{
            visibility = View.GONE
        }
    }
}