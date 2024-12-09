package com.example.schedule.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.schedule.data.dto.LessonDto
import com.example.schedule.data.dto.ScheduleDto
import java.time.LocalDate

@Composable
fun GroupScreenSchedule(schedule: ScheduleDto){
    Column() {
        if (schedule.classes != null) {
            val lessonsList = schedule.classes.sortedBy { it.startTime }
                .groupBy {
                    it.date
                }.toSortedMap()

            val categorizedList = lessonsList.map {
                GroupCategory(
                    name = it.key.toString(),
                    items = it.value
                )
            }
            CategorizedGroupLazyColumn(categories = categorizedList)
        }

    }
}

data class GroupCategory(
    val name: String,
    val items: List<LessonDto>
)


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedGroupLazyColumn(
    categories: List<GroupCategory>,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(
                    date = LocalDate.parse(category.name)
                )
            }
            items(category.items) { lesson ->
                ScheduleItem(
                    subject = lesson.subject,
                    teacher = lesson.teacher,
                    startTime = lesson.startTime,
                    endTime = lesson.endTime,
                    type = lesson.type,
                    place = lesson.place,
                    modifier = Modifier.padding(13.dp,3.dp,0.dp,3.dp)
                )
            }
        }
    }
}