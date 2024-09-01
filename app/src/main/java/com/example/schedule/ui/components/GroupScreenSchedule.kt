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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schedule.data.dto.LessonDto
import com.example.schedule.data.dto.ScheduleDto
import com.example.schedule.ui.theme.Typography

@Composable
fun GroupScreenSchedule(schedule: ScheduleDto){
    Column() {
        if (schedule.classes != null) {
            val lessonsList = schedule.classes.sortedBy { it.startTime }
                .groupBy {
                    it.date
                }.toSortedMap()

            val categorizedList = lessonsList.map {
                Category(
                    name = it.key.toString(),
                    items = it.value
                )
            }
            CategorizedLazyColumn(categories = categorizedList)
        }

    }
}

data class Category(
    val name: String,
    val items: List<LessonDto>
)

@Composable
fun CategoryHeader(text: String){
    Row(
        modifier = Modifier
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedLazyColumn(
    categories: List<Category>,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(text = category.name)
            }
            items(category.items) { lesson ->
                ScheduleItem(
                    subject = "${lesson.subject}",
                    teacher = "${lesson.teacher}",
                    startTime = "${lesson.startTime}",
                    endTime = "${lesson.endTime}",
                    type = "${lesson.type}",
                    place = "${lesson.place}",
                    modifier = Modifier.padding(13.dp,3.dp,0.dp,3.dp)
                )
            }
        }
    }
}