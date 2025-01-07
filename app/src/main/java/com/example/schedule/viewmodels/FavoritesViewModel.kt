package com.example.schedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.api.repository.ScheduleRepository
import com.example.schedule.viewmodels.events.FavoritesEvent
import com.example.schedule.viewmodels.state.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: ScheduleRepository
): ViewModel() {

    private val _favorites = repository.getFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _currentGroup: MutableStateFlow<String?> = MutableStateFlow(null)
    private val _currentGroupName: MutableStateFlow<String?> = MutableStateFlow(null)

    private val _currentSchedule = repository.getLessonsByDateAsc()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(FavoritesState())

    val state = combine(_state, _favorites, _currentGroup, _currentGroupName, _currentSchedule) { state, favorites, currentGroup, currentGroupName, currentSchedule ->
        state.copy(
            favorites = favorites,
            currentGroup = currentGroup,
            currentGroupName = currentGroupName,
            currentSchedule = currentSchedule
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
                    _currentGroup.update { event.group.id }
                    _currentGroupName.update { event.group.name }
                }
            }
            is FavoritesEvent.ResetState -> {
                viewModelScope.launch {
                    _currentGroup.update { null }
                    _currentGroupName.update { null }
                }
            }
        }
    }

}