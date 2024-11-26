package com.example.testapp.presentation


fun formatLookingNumber(lookingNumber: Long): String {
    val personForm = when {
        lookingNumber % 10 == 1L && lookingNumber % 100 != 11L -> "человек"
        lookingNumber % 10 in 2..4 && (lookingNumber % 100 < 12 || lookingNumber % 100 > 14) -> "человека"
        else -> "человек"
    }

    return "Сейчас просматривает $lookingNumber $personForm"
}

fun formatPublishedDate(publishedDate: String): String {
    val (year, month, day) = publishedDate.split("-").map { it.toInt() }

    val months = arrayOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )

    val monthName = months[month - 1]
    return "Опубликовано $day $monthName"
}

fun formatCountVacancies(countVacancies: Int): String {
    val vacancyString = when {
        countVacancies % 10 == 1 && countVacancies % 100 != 11 -> "вакансия"
        countVacancies % 10 in 2..4 && (countVacancies % 100 < 12 || countVacancies % 100 > 14) -> "вакансии"
        else -> "вакансий"
    }
    return "$countVacancies $vacancyString"
}


