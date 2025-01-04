package com.example.schedule.data.dto

import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "nauczyciel")
data class TeacherDto (
    @TextContent
    var teacher: String
)