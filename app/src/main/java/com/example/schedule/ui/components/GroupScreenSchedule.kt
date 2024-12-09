package com.example.schedule.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schedule.data.dto.LessonDto
import com.example.schedule.data.dto.ScheduleDto
import com.example.schedule.ui.theme.Typography
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
            val scheduleItemModifier =
                if(LocalDate.parse(category.name).isBefore(LocalDate.now()))
                    Modifier.alpha(0.5f)
                else
                    Modifier.alpha(1.0f)

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