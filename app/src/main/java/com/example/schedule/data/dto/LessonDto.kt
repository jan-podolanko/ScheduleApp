package com.example.schedule.data.dto

import com.example.schedule.data.db.entities.Lesson
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "zajecia")
data class LessonDto(

    @PropertyElement(name = "przedmiot")
    var subject: String,

    @PropertyElement(name = "nauczyciel")
    var teacher: String,

    @PropertyElement(name = "od-godz")
    var startTime: String,

    @PropertyElement(name = "do-godz")
    var endTime: String,

    @PropertyElement(name = "typ")
    var type: String,

    @PropertyElement(name = "sala")
    var place: String = "Brak podanej sali",

    @PropertyElement(name = "termin")
    var date: String,

    @PropertyElement(name = "dzien")
    var day: String,

    @PropertyElement(name = "uwagi")
    var comments: String?
)