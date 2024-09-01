package com.example.schedule.data.db

import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.data.dto.ScheduleDto

data class FavoritesState(
    val favorites: List<Group> = emptyList(),
    var currentSchedule: ScheduleDto? = null
)