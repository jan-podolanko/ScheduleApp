package com.example.schedule.data.db

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun fromStringToLocalDate(string: String): LocalDate{
        return string.let {LocalDate.parse(it)}
    }

    @TypeConverter
    fun fromLocalDateToString(date: LocalDate): String {
        return date.toString()
    }
}