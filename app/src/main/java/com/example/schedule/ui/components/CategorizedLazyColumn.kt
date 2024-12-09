package com.example.schedule.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.schedule.ui.views.Category
import com.example.schedule.viewmodels.events.ScheduleEvent
import java.time.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedLazyColumn(
    categories: List<Category>,
    onEvent: (ScheduleEvent) -> Unit
){
    LazyColumn {
        categories.forEach { category ->
            val scheduleItemModifier =
                if(category.date.isBefore(LocalDate.now()))
                    Modifier.alpha(0.5f)
                else
                    Modifier.alpha(1.0f)
            stickyHeader {
                CategoryHeader(date = category.date)
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
//                            lessonViewModel.changeLessonVisibility(
//                                visibility = lesson.visibility,
//                                subject = lesson.subject,
//                                type = lesson.type,
//                                groupId = lesson.groupId
//                            )
                            onEvent(ScheduleEvent.ChangeVisibility(
                                visibility = lesson.visibility,
                                subject = lesson.subject,
                                type = lesson.type,
                                groupId = lesson.groupId)
                            )
                        }
                )
                HorizontalDivider()
            }
        }
    }
}