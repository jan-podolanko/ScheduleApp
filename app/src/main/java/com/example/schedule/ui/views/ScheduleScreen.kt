package com.example.schedule.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.ui.components.ScheduleItem
import com.example.schedule.ui.theme.Typography
import com.example.schedule.ui.viewmodels.LessonDbViewModel
import java.time.LocalDate

@Composable
fun ScheduleScreen(lessonViewModel: LessonDbViewModel) {
    var currentLesson by remember {
        mutableStateOf<Int?>(null)
    }
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun CategorizedLazyColumn(
        categories: List<Category>,
        modifier: Modifier = Modifier
    ){
        LazyColumn(modifier) {
            categories.forEach { category ->
                val scheduleItemModifier = if(category.date.isBefore(LocalDate.now())) Modifier.alpha(0.5f) else Modifier.alpha(1.0f)
                stickyHeader {
                    CategoryHeader(
                        modifier = scheduleItemModifier,
                        text = category.name)
                }
                items(category.items) { lesson ->
                    ScheduleItem(
                        subject = lesson.subject,
                        teacher = lesson.teacher,
                        startTime = lesson.startTime,
                        endTime = lesson.endTime,
                        type = lesson.type,
                        place = lesson.place,
                        modifier = scheduleItemModifier
                            .clickable {
                                lessonViewModel.updateLesson(lesson.copy(visibility = !lesson.visibility))
                            }

                    )
                    HorizontalDivider()
                }
            }
        }
    }
    Column {
        val lessonsList = lessonViewModel.state.collectAsState().value.lessons.filter { it.visibility }.sortedBy { it.startTime }
            .groupBy {
                it.date
            }.toSortedMap()

        val categorizedList = lessonsList.map {
            Category(
                name = it.key.toString(),
                date = it.key,
                items = it.value
            )
        }
        CategorizedLazyColumn(categories = categorizedList)
    }
}

data class Category(
    val name: String,
    val date: LocalDate,
    val items: List<Lesson>
)

@Composable
fun CategoryHeader(text: String, modifier: Modifier){
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
    ){
        Text(
            text = text,
            style = Typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
    }
}

