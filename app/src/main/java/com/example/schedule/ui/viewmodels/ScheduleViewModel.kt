package com.example.schedule.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.api.repository.ScheduleRepository
import com.example.schedule.data.dto.ScheduleDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val repository: ScheduleRepository
): ViewModel() {
    var scheduleUiState: ScheduleDto? by mutableStateOf(null)
        private set
    var error: String by mutableStateOf("error")
    init {
        getSchedule()
    }

    fun getSchedule(){
        viewModelScope.launch {
            try {
                val listResult = repository.testGetSchedule()
                scheduleUiState = listResult
            } catch (e: IOException){
                error = e.toString()
            }
        }
    }
}