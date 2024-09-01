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
import androidx.navigation.NavController
import com.example.schedule.data.db.FavoritesState
import com.example.schedule.ui.navigation.Favorites
import com.example.schedule.ui.navigation.Groups
import com.example.schedule.ui.navigation.ScheduleDestination
import com.example.schedule.ui.viewmodels.GroupsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    currentScreenName: String,
    currentScreen: ScheduleDestination,
    groupsViewModel: GroupsViewModel,
    navController: NavController,
    favoritesState: FavoritesState
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = currentScreenName)
        },
        navigationIcon = {
            //seems somewhat broken
            IconButton(onClick = {
                if (currentScreen == Groups) {
                    if (groupsViewModel.currentGroup != null) {
                        groupsViewModel.currentGroup = null
                    } else {
                        navController.popBackStack()
                    }
                } else if (currentScreen == Favorites) {
                    if (favoritesState.currentSchedule != null) {
                        favoritesState.currentSchedule = null
                    } else {
                        navController.popBackStack()
                    }
                } else {
                    navController.popBackStack()
                }

            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Cofnij"
                )
            }
        },
        actions = {
            if (currentScreen == Favorites) {
                IconButton(onClick = {
                    /*navController.navigate(Test.route)*/
                }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Ustawienia"
                    )
                }
            } else if (currentScreen == Groups && groupsViewModel.currentGroup != null) {
                IconButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        groupsViewModel.addGroup(groupsViewModel.currentGroup!!.scheduleId)
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Dodaj zajęcia"
                    )
                }
            } else {
                null
            }
        }
    )
}