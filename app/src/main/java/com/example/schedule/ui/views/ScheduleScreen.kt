package com.example.schedule.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.ui.components.CategorizedLazyColumn
import com.example.schedule.viewmodels.LessonDbViewModel
import java.time.LocalDate

@Composable
fun ScheduleScreen(lessonViewModel: LessonDbViewModel) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { _ ->
        Column {
            val lessonsList = lessonViewModel.state.collectAsState().value.lessons
                .filter { it.visibility }
                .sortedBy { it.startTime }
                .groupBy { it.date }
                .toSortedMap()

            val categorizedList = lessonsList.map {
                Category(
                    date = it.key,
                    items = it.value
                )
            }
            CategorizedLazyColumn(
                categories = categorizedList,
                onEvent = lessonViewModel::onScheduleEvent,
                scope = scope,
                snackbarHostState = snackbarHostState
            )
        }
    }
}

data class Category(
    val date: LocalDate,
    val items: List<Lesson>
)

