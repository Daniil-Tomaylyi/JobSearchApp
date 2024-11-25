package com.example.testapp.data.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        return value.split(",")
    }
}