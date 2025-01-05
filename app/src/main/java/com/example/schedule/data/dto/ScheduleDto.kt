package com.example.schedule.data.dto

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "plan-zajec")
data class ScheduleDto(

    @Attribute(name = "id")
    val scheduleId: String,

    @Attribute(name = "nazwa")
    val scheduleName: String,

    @Attribute(name = "typ")
    val type: String,

    @Element
    val classes: List<LessonDto>? = null,
)