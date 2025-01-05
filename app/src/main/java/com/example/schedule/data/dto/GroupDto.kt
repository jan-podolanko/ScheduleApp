package com.example.schedule.data.dto

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "zasob")
data class GroupDto(
    @Attribute(name = "id")
    val id: String,
    @Attribute(name = "nazwa")
    val name: String,
    @Attribute(name = "typ")
    val type: String
)