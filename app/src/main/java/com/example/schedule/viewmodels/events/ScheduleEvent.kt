package com.example.schedule.viewmodels.events

import com.example.schedule.data.db.entities.Lesson

sealed interface ScheduleEvent {
    data class ChangeVisibility(val visibility: Boolean, val subject: String, val type: String, val groupId: String): ScheduleEvent
    data class UpdateLesson(val lesson: Lesson): ScheduleEvent
}