package com.example.schedule.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.schedule.api.repository.ScheduleRepository
import com.example.schedule.data.db.LessonDatabase
import com.example.schedule.data.db.LessonEvent
import com.example.schedule.data.db.LessonState
import com.example.schedule.data.db.SortType
import com.example.schedule.data.db.dao.LessonDao
import com.example.schedule.data.db.entities.Lesson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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

    fun updateLesson(lesson: Lesson){
        viewModelScope.launch {
            repository.updateLesson(lesson)
        }
    }
//    fun onEvent(event: LessonEvent){
//        when(event){
//            is LessonEvent.DeleteLesson -> {
//                viewModelScope.launch {
//                    repository.deleteLesson(event.lesson)
//                }
//            }
//            LessonEvent.SaveLesson -> {
//                val lesson = state.value.lesson
//
//                viewModelScope.launch {
//                    repository.insertLesson(lesson)
//                }
//            }
//            is LessonEvent.SortLessons -> {
//                _sortType.value = event.sortType
//            }
//
//            is LessonEvent.SetLesson -> {
//                _state.update { it.copy(
//                    lesson = event.lesson
//                ) }
//            }
//        }
//    }
}