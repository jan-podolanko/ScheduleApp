package com.example.schedule.viewmodels

import android.util.Log
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
import com.example.schedule.viewmodels.events.GroupEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val repository: ScheduleRepository
): ViewModel() {

    // initial ui state
    var groupsUiState: List<GroupDto>? by mutableStateOf(null)
        private set

    private val _groups = MutableStateFlow(GroupsDto())
    val groups = _groups

    var currentGroup: ScheduleDto? by mutableStateOf(null)

    init {
        getAllSchedules()
    }

//    private fun getGroups(){
//        viewModelScope.launch {
//            try {
//                val listResult = repository.getGroups()
//                groupsUiState = listResult
//                _groups.value = listResult
//            } catch (e: IOException){
//                Log.d("ERR", e.toString())
//            }
//        }
//    }
//    fun getTeachers(){
//        viewModelScope.launch {
//            try {
//                val listResult = repository.getTeachers()
//                groupsUiState = listResult
//                _groups.value = listResult
//            } catch (e: IOException){
//
//            }
//        }
//    }

    private fun getAllSchedules(){
        viewModelScope.launch {
            try {
                val groupSchedules = repository.getGroups().groups
                val teacherSchedules = repository.getTeachers().groups
                val classroomSchedules = repository.getClassrooms().groups
                val allGroups: List<GroupDto>? = groupSchedules?.plus(teacherSchedules as List<GroupDto>)?.plus(classroomSchedules as List<GroupDto>)
                groupsUiState = allGroups
            } catch (e: IOException){
                Log.d("ERR", e.toString())
            }
        }
    }

    fun onGroupEvent(event: GroupEvent){
        when(event){
            is GroupEvent.AddGroup -> {
                viewModelScope.launch{
                    try {
                        val group: ScheduleDto = when(event.type){
                            "G" -> repository.getGroupSchedule(event.id)
                            "N" -> repository.getTeacherSchedule(event.id)
                            "S" -> repository.getClassroomSchedule(event.id)
                            else -> throw IllegalStateException("Invalid schedule type")
                        }

                        if (group.classes != null) {
                            repository.insertFavorite(
                                Group(
                                    id = group.scheduleId,
                                    name = group.scheduleName,
                                    type = group.type
                                )
                            )
                            repository.insertGroup(
                                group.classes.map {
                                    Lesson(
                                        subject = it.subject,
                                        date = LocalDate.parse(it.date),
                                        startTime = it.startTime,
                                        endTime = it.endTime,
                                        type = it.type,
                                        place = it.place,
                                        day = it.day,
                                        teachers = if(group.type == "N"){
                                            listOf(group.scheduleName)
                                        } else {
                                            it.teachers?.map { x -> x.teacher }
                                               },
                                        groupId = group.scheduleId,
                                        group = group.scheduleName,
                                    )
                                }
                            )
                        }
                    } catch (e: IOException) {
                        Log.d("ERR", e.toString())
                    }
                }
            }
            is GroupEvent.GetGroup -> {
                viewModelScope.launch {
                    try {
                        val groupResult: ScheduleDto = when(event.type){
                            "G" -> repository.getGroupSchedule(event.id)
                            "N" -> repository.getTeacherSchedule(event.id)
                            "S" -> repository.getClassroomSchedule(event.id)
                            else ->  throw IllegalStateException("Invalid schedule type")
                        }

                        currentGroup = groupResult
                    } catch (e: IOException){
                        Log.d("ERR", e.toString())
                    }
                }
            }

//            GroupEvent.GetTeachers -> {
//                viewModelScope.launch {
//                    try {
//                        val listResult = repository.getTeachers()
//                        groupsUiState = listResult
//                        _groups.value = listResult
//                    } catch (e: IOException) {
//                        Log.d("ERR", e.toString())
//                    }
//                }
//            }
//
//            GroupEvent.GetGroups -> {
//                viewModelScope.launch {
//                    try {
//                        val listResult = repository.getGroups()
//                        groupsUiState = listResult
//                        _groups.value = listResult
//                    } catch (e: IOException){
//                        Log.d("ERR", e.toString())
//                    }
//                }
//            }
        }
    }
}