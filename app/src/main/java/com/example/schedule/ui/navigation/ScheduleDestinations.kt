package com.example.schedule.ui.navigation

interface ScheduleDestination {
    val name: String
    val route: String
}

object Favorites: ScheduleDestination {
    override val name = "Ulubione"
    override val route = "favorites"
}
object Groups: ScheduleDestination {
    override val name = "Grupy"
    override val route = "groups"
}
object Schedule: ScheduleDestination {
    override val name = "Plan Zajęć"
    override val route = "schedule"
}