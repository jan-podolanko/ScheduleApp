package com.example.schedule.ui.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.schedule.R
import com.example.schedule.ui.navigation.BottomNavigationItem
import com.example.schedule.ui.navigation.Favorites
import com.example.schedule.ui.navigation.Groups
import com.example.schedule.ui.navigation.Schedule

@SuppressLint("RestrictedApi")
@Composable
fun NavBar(
    navController: NavController,
    navBackStackEntry: NavBackStackEntry?
) {
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
    NavigationBar {
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(
                        route = item.route,
                        arguments = null
                    )
                } == true,
                onClick = {
                    navController.navigate(item.destination.route){
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (
                            currentDestination?.hierarchy?.any {
                                it.hasRoute(
                                    route = item.route,
                                    arguments = null
                                )
                            } == true) {
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