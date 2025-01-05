package com.example.schedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.api.repository.ScheduleRepository
import com.example.schedule.viewmodels.events.FavoritesEvent
import com.example.schedule.viewmodels.state.FavoritesState
import com.example.schedule.data.dto.ScheduleDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: ScheduleRepository
): ViewModel() {

    private val _favorites = repository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _currentGroup: MutableStateFlow<ScheduleDto?> = MutableStateFlow(null)

    private val _state = MutableStateFlow(FavoritesState())

    val state = combine(_state, _favorites, _currentGroup) { state, favorites, currentGroup ->
        state.copy(
            favorites = favorites,
            currentSchedule = currentGroup
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FavoritesState())

    fun onFavEvent(event: FavoritesEvent){
        when(event){
            is FavoritesEvent.DeleteGroup -> {
                viewModelScope.launch {
                    repository.deleteFavorite(event.group)
                }
            }
            is FavoritesEvent.SetSchedule -> {
                viewModelScope.launch {
                    when(event.group.type){
                        "G" -> _currentGroup.value = repository.getGroupSchedule(event.group.id)
                        "N" -> _currentGroup.value = repository.getTeacherSchedule(event.group.id)
                        "S" -> _currentGroup.value = repository.getClassroomSchedule(event.group.id)
                    }

                }
            }
            is FavoritesEvent.GetGroup -> TODO()
        }
    }

}