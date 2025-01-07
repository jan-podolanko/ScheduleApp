package com.example.schedule.viewmodels.state

import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson

data class FavoritesState(
    val favorites: List<Group> = emptyList(),
    var currentGroup: String? = null,
    var currentGroupName: String? = null,
    var currentSchedule: List<Lesson> = emptyList()
)