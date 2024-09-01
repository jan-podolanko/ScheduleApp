package com.example.schedule.data.dto

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "plan-zajec")
data class GroupsDto(
    @Element
    val groups: List<GroupDto>? = null,
)