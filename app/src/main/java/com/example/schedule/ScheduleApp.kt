package com.example.schedule

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.schedule.ui.components.NavBar
import com.example.schedule.ui.components.TopAppBar
import com.example.schedule.ui.navigation.Favorites
import com.example.schedule.ui.navigation.Groups
import com.example.schedule.ui.navigation.Schedule
import com.example.schedule.ui.views.FavoritesScreen
import com.example.schedule.ui.views.GroupScreen
import com.example.schedule.ui.views.ScheduleScreen
import com.example.schedule.viewmodels.GroupsViewModel
import com.example.schedule.viewmodels.LessonDbViewModel
import com.example.schedule.viewmodels.events.FavoritesEvent
import com.example.schedule.viewmodels.state.FavoritesState

@Composable
fun ScheduleApp(
    lessonViewModel: LessonDbViewModel,
    groupsViewModel: GroupsViewModel,
    favoritesState: FavoritesState,
    onFavEvent: (FavoritesEvent) -> Unit,
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    if(navBackStackEntry?.destination?.route == "groups" && groupsViewModel.currentGroup != null){
        BackHandler { groupsViewModel.currentGroup = null }
    }else if(navBackStackEntry?.destination?.route == "favorites" && favoritesState.currentSchedule != null) {
        BackHandler { favoritesState.currentSchedule = null }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    groupsViewModel = groupsViewModel,
                    navController = navController,
                    favoritesState = favoritesState,
                    onGroupEvent = groupsViewModel::onGroupEvent,
                    navBackStackEntry = navBackStackEntry
                )
            },
            bottomBar = {
                NavBar(
                    navController = navController,
                    navBackStackEntry = navBackStackEntry
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Schedule.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = Schedule.route) {
                    ScheduleScreen(lessonViewModel = lessonViewModel)
                }
                composable(route = Groups.route) {
                    GroupScreen(viewModel = groupsViewModel)
                }
                composable(route = Favorites.route) {
                    FavoritesScreen(
                        state = favoritesState,
                        onEvent = onFavEvent
                    )
                }
            }
        }
    }
}



