package com.example.schedule.viewmodels.events

import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson

sealed interface FavoritesEvent {
    data class SetSchedule(val group: Group): FavoritesEvent
    data class DeleteGroup(val group: Group): FavoritesEvent
    data object ResetState : FavoritesEvent
}