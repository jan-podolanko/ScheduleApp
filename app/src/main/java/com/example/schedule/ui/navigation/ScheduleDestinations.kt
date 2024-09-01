package com.example.schedule.ui.navigation

import androidx.compose.runtime.Composable
import com.example.schedule.ui.views.FavoritesScreen
import com.example.schedule.ui.views.GroupScreen
import com.example.schedule.ui.views.ScheduleScreen

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
object Settings: ScheduleDestination {
    override val name = "Ustawienia"
    override val route = "settings"
}