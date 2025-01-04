package com.example.schedule.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.ui.components.CategorizedLazyColumn
import com.example.schedule.viewmodels.LessonDbViewModel
import java.time.LocalDate

@Composable
fun ScheduleScreen(lessonViewModel: LessonDbViewModel) {
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
        CategorizedLazyColumn(categories = categorizedList, onEvent = lessonViewModel::onScheduleEvent)
    }
}

data class Category(
    val date: LocalDate,
    val items: List<Lesson>
)

