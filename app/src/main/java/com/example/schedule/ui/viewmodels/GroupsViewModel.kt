package com.example.schedule.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.api.repository.ScheduleRepository
import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.data.dto.GroupDto
import com.example.schedule.data.dto.GroupsDto
import com.example.schedule.data.dto.ScheduleDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val repository: ScheduleRepository
): ViewModel() {

    // initial ui state
    var groupsUiState: GroupsDto? by mutableStateOf(null)
        private set

    private val _groups = MutableStateFlow(GroupsDto())
    val groups = _groups

    var currentGroup: ScheduleDto? by mutableStateOf(null)

    init {
        getGroups()
    }

    private fun getGroups(){
        viewModelScope.launch {
            try {
                val listResult = repository.getGroups()
                groupsUiState = listResult
                _groups.value = listResult
            } catch (e: IOException){

            }
        }
    }
    fun getGroup(id: String){
        viewModelScope.launch {
            try {
                val groupResult = repository.getSchedule(id)
                currentGroup = groupResult
            } catch (e: IOException){

            }
        }
    }
    suspend fun addGroup(id: String){
        viewModelScope.launch{
            try {
                val group = repository.getSchedule(id)
                if (group.classes != null) {
                    repository.insertFavorite(
                        Group(
                            id = group.scheduleId,
                            name = group.scheduleName
                        )
                    )
                    repository.insertGroup(
                        group.classes.map {
                            Lesson(
                                subject = it.subject,
                                date = it.date,
                                startTime = it.startTime,
                                endTime = it.endTime,
                                type = it.type,
                                place = it.place,
                                day = it.day,
                                teacher = it.teacher,
                                groupId = group.scheduleId,
                                group = group.scheduleName
                            )
                        }
                    )
                }
            } catch (e: IOException) {

            }
        }
    }



}