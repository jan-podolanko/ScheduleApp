package com.example.schedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.api.repository.ScheduleRepository
import com.example.schedule.viewmodels.state.LessonState
import com.example.schedule.data.db.SortType
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.viewmodels.events.FavoritesEvent
import com.example.schedule.viewmodels.events.ScheduleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonDbViewModel @Inject constructor(
    private val repository: ScheduleRepository
) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.DATE_ASC)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _lessons = _sortType
        .flatMapLatest { sortType ->
            when(sortType){
                SortType.DATE_ASC -> repository.getLessonsByDateAsc()
                SortType.DATE_DESC -> repository.getLessonsByDateDesc()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(LessonState())

    val state = combine(_state, _sortType, _lessons) { state, sortType, lessons ->
        state.copy(
            lessons = lessons,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LessonState())

    fun onScheduleEvent(event: ScheduleEvent){
        when(event){
            is ScheduleEvent.ChangeVisibility -> {
                viewModelScope.launch {
                    repository.changeLessonVisibility(
                        visibility = event.visibility,
                        subject = event.subject,
                        type = event.type,
                        groupId = event.groupId,
                    )
                }
            }
            is ScheduleEvent.UpdateLesson -> {
                viewModelScope.launch {
                    repository.updateLesson(event.lesson)
                }
            }
        }
    }
}