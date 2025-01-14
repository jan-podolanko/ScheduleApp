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
import com.example.schedule.viewmodels.FavoritesViewModel
import com.example.schedule.viewmodels.GroupsViewModel
import com.example.schedule.viewmodels.LessonDbViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleTheme {
                val dbViewModel = hiltViewModel<LessonDbViewModel>()
                val groupsViewModel = hiltViewModel<GroupsViewModel>()
                val favDbViewModel = hiltViewModel<FavoritesViewModel>()
                Surface {
                    ScheduleApp(
                        lessonViewModel = dbViewModel,
                        groupsViewModel = groupsViewModel,
                        favoritesViewModel = favDbViewModel,
                    )
                }
            }
        }
    }
}