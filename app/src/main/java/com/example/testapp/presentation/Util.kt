package com.example.testapp.presentation

import android.widget.TextView
import com.example.testapp.data.network.OffersContainer
import timber.log.Timber

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

fun formatMoreVacancies(countVacancies: Int): String {
    val vacancyString = when {
        countVacancies % 10 == 1 && countVacancies % 100 != 11 -> "вакансия"
        countVacancies % 10 in 2..4 && (countVacancies % 100 < 12 || countVacancies % 100 > 14) -> "вакансии"
        else -> "вакансий"
    }
    return "Еще $countVacancies $vacancyString"
}

fun formatTitleOffers(item: OffersContainer?): String {
    item ?: return ""

    val title = item.title

    val maxLines = if (item.button == null) 3 else 2

    return truncateTitle(title, maxLines)
}

private fun truncateTitle(title: String, maxLines: Int): String {
    val words = title.split(" ")
    val truncated = StringBuilder()
    var currentLineLength = 0

    for (word in words) {
        // Примерная длина строки - 20 символов на строку (можно настроить)
        if (currentLineLength + word.length + 1 > maxLines * 20) {
            break
        }

        if (truncated.isNotEmpty()) {
            truncated.append("\n")
        }

        truncated.append(word)
        currentLineLength += word.length + 1
    }

    return truncated.toString().trimEnd()
}
