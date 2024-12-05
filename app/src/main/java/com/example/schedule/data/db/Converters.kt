package com.example.schedule.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromStringToLocalDate(string: String): LocalDate{
        return string.let {LocalDate.parse(it)}
    }

    @TypeConverter
    fun fromLocalDateToString(date: LocalDate): String {
        return date.toString()
    }
}