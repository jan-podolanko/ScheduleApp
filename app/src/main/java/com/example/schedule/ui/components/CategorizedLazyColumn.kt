package com.example.schedule.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.schedule.ui.views.Category
import com.example.schedule.viewmodels.events.ScheduleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedLazyColumn(
    categories: List<Category>,
    onEvent: (ScheduleEvent) -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
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
                    subject = lesson.subject.toString(),
                    teachers = lesson.teachers,
                    startTime = lesson.startTime,
                    endTime = lesson.endTime,
                    type = lesson.type,
                    place = lesson.place,
                    modifier = scheduleItemModifier
                        .clickable {
                            onEvent(ScheduleEvent.ChangeVisibility(
                                visibility = lesson.visibility,
                                subject = lesson.subject.toString(),
                                type = lesson.type,
                                groupId = lesson.groupId)
                            )
                            scope.launch {
                                snackbarHostState.showSnackbar("Schowano ${lesson.subject}!")
                            }
                        }
                )
                HorizontalDivider()
            }
        }
    }
}