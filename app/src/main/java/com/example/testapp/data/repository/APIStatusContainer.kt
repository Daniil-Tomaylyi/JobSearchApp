package com.example.testapp.data.repository

import com.example.testapp.domain.models.APIStatus

enum class APIStatusContainer { LOADING, ERROR, DONE }

fun APIStatusContainer.asDomainModel(): APIStatus{
    return when (this) {
        APIStatusContainer.LOADING -> APIStatus.LOADING
        APIStatusContainer.ERROR -> APIStatus.ERROR
        APIStatusContainer.DONE -> APIStatus.DONE
    }
}