package com.example.schedule.viewmodels.events

sealed interface GroupEvent {
    data class GetGroup(val id: String, val type: String): GroupEvent
    data class AddGroup(val id: String, val type: String): GroupEvent
//    data object GetTeachers: GroupEvent
//    data object GetGroups: GroupEvent
}