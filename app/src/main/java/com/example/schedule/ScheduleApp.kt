package com.example.schedule

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schedule.viewmodels.events.FavoritesEvent
import com.example.schedule.viewmodels.state.FavoritesState
import com.example.schedule.ui.components.TopAppBar
import com.example.schedule.ui.navigation.BottomNavigationItem
import com.example.schedule.ui.navigation.Favorites
import com.example.schedule.ui.navigation.Groups
import com.example.schedule.ui.navigation.Schedule
import com.example.schedule.ui.navigation.ScheduleDestination
import com.example.schedule.viewmodels.GroupsViewModel
import com.example.schedule.viewmodels.LessonDbViewModel
import com.example.schedule.ui.views.FavoritesScreen
import com.example.schedule.ui.views.GroupScreen
import com.example.schedule.ui.views.ScheduleScreen

@Composable
fun ScheduleApp(
    lessonViewModel: LessonDbViewModel,
    groupsViewModel: GroupsViewModel,
    favoritesState: FavoritesState,
    onFavEvent: (FavoritesEvent) -> Unit
) {
    var currentScreen: ScheduleDestination by remember {
        mutableStateOf(Schedule)
    }
    var currentScreenName by remember {
        mutableStateOf(currentScreen.name)
    }
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val items = listOf(
        BottomNavigationItem(
            title = stringResource(R.string.schedule_tab),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Schedule.route,
            destination = Schedule
        ),
        BottomNavigationItem(
            title = stringResource(R.string.favorites_tab),
            selectedIcon = Icons.Filled.Star,
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_star_outline_24),
            route = Favorites.route,
            destination = Favorites
        ),
        BottomNavigationItem(
            title = stringResource(R.string.groups_tab),
            selectedIcon = Icons.AutoMirrored.Filled.List,
            unselectedIcon = Icons.AutoMirrored.Outlined.List,
            route = Groups.route,
            destination = Groups
        )
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    currentScreenName = currentScreenName,
                    currentScreen = currentScreen,
                    groupsViewModel = groupsViewModel,
                    navController = navController,
                    favoritesState = favoritesState,
                    onGroupEvent = groupsViewModel::onGroupEvent
                )
            },
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.destination.route)
                                currentScreen = item.destination
                                currentScreenName = currentScreen.name
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(text = item.title)
                            }
                        )
                    }
                }
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
                        onEvent = onFavEvent)
                }
            }
        }
    }
}



