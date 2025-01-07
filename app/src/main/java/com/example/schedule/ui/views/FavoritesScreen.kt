package com.example.schedule.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.ui.components.CategoryHeader
import com.example.schedule.ui.components.GroupItem
import com.example.schedule.ui.components.ScheduleItem
import com.example.schedule.viewmodels.FavoritesViewModel
import com.example.schedule.viewmodels.events.FavoritesEvent
import com.example.schedule.viewmodels.events.onSnackbarEvent
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel
) {
    val state = favoritesViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ){ _ ->
        Column {
            if (state.value.currentGroup != null) {
                Column {
                    val schedule = state.value.currentSchedule
                        .filter { it.groupId == state.value.currentGroup }
                    if (schedule != emptyList<Lesson>()) {
                        val lessonsList = schedule.sortedBy { it.startTime }
                            .groupBy {
                                it.date
                            }.toSortedMap()

                        val categorizedList = lessonsList.map {
                            FavoritesGroupCategory(
                                name = it.key.toString(),
                                items = it.value
                            )
                        }
                        CategorizedFavoritesLazyColumn(categories = categorizedList)
                    }

                }
            } else {
                LazyColumn {
                    items(state.value.favorites) { group ->
                        GroupItem(
                            group = group,
                            onEvent = favoritesViewModel::onFavEvent,
                            snackbarHostState = snackbarHostState,
                            scope = scope
                        )
                    }
                }
            }
        }
    }
}
data class FavoritesGroupCategory(
    val name: String,
    val items: List<Lesson>
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedFavoritesLazyColumn(
    categories: List<FavoritesGroupCategory>,
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
                    subject = lesson.subject.toString(),
                    teachers = lesson.teachers,
                    startTime = lesson.startTime,
                    endTime = lesson.endTime,
                    type = lesson.type,
                    place = lesson.place,
                    modifier = Modifier.padding(13.dp,3.dp,0.dp,3.dp)
                )
                HorizontalDivider()
            }
        }
    }
}