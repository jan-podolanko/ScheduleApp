package com.example.schedule.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.schedule.ui.navigation.Schedule
import com.example.schedule.ui.navigation.ScheduleDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavBarViewModel @Inject constructor() : ViewModel() {

    var currentScreen: ScheduleDestination by mutableStateOf(Schedule)
    var selectedItemIndex by mutableIntStateOf(0)
}