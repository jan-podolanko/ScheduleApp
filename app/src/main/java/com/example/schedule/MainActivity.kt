package com.example.schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.schedule.ui.theme.ScheduleTheme
import com.example.schedule.ui.viewmodels.FavoritesViewModel
import com.example.schedule.ui.viewmodels.GroupsViewModel
import com.example.schedule.ui.viewmodels.LessonDbViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleTheme {
                val dbViewModel = hiltViewModel<LessonDbViewModel>()
                val state by dbViewModel.state.collectAsState()
                val groupsViewModel = hiltViewModel<GroupsViewModel>()
                val favDbViewModel = hiltViewModel<FavoritesViewModel>()
                val favoritesState by favDbViewModel.state.collectAsState()
                Surface {
                    ScheduleApp(
                        lessonState = state,
                        groupsViewModel = groupsViewModel,
                        favoritesState = favoritesState,
                        onFavEvent = favDbViewModel::onFavEvent
                    )
                }
            }
        }
    }
}