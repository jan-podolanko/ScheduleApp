package com.example.schedule.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.schedule.R
import com.example.schedule.viewmodels.state.FavoritesState
import com.example.schedule.ui.navigation.Favorites
import com.example.schedule.ui.navigation.Groups
import com.example.schedule.ui.navigation.ScheduleDestination
import com.example.schedule.viewmodels.GroupsViewModel
import com.example.schedule.viewmodels.NavBarViewModel
import com.example.schedule.viewmodels.events.GroupEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navBarViewModel: NavBarViewModel,
    groupsViewModel: GroupsViewModel,
    navController: NavController,
    favoritesState: FavoritesState,
    onGroupEvent: (GroupEvent) -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            Text(text = when(navBackStackEntry?.destination?.route){
                "schedule" -> stringResource(R.string.nav_schedule)
                "favorites" -> stringResource(R.string.nav_favorites)
                "groups" -> stringResource(R.string.nav_groups)
                else -> {
                    stringResource(R.string.app_name)
                }
            })
        },
        navigationIcon = {
            //seems somewhat broken
            IconButton(onClick = {
//                if (navBarViewModel.currentScreen == Groups) {
//                    if (groupsViewModel.currentGroup != null) {
//                        groupsViewModel.currentGroup = null
//                    } else {
//                        navController.popBackStack()
//                    }
//                } else if (navBarViewModel.currentScreen == Favorites) {
//                    if (favoritesState.currentSchedule != null) {
//                        favoritesState.currentSchedule = null
//                    } else {
//                        navController.popBackStack()
//                    }
//                } else {
//                    navController.popBackStack()
//                }

            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.app_bar_back_button)
                )
            }
        },
        actions = {
            if (navBarViewModel.currentScreen == Favorites) {
                IconButton(onClick = {
                    /*navController.navigate(Test.route)*/
                }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.app_bar_settings_button)
                    )
                }
            } else if (navBarViewModel.currentScreen == Groups && groupsViewModel.currentGroup != null) {
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        onGroupEvent(GroupEvent.AddGroup(groupsViewModel.currentGroup!!.scheduleId))
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.app_bar_add_group_button)
                    )
                }
            } else {
                null
            }
        }
    )
}