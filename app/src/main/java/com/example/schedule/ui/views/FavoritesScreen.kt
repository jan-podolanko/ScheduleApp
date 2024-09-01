package com.example.schedule.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.schedule.data.db.FavoritesEvent
import com.example.schedule.data.db.FavoritesState
import com.example.schedule.ui.components.GroupItem
import com.example.schedule.ui.components.GroupScreenSchedule

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit
) {
    Column {
        if(state.currentSchedule != null){
            GroupScreenSchedule(schedule = state.currentSchedule!!)
        } else {
            LazyColumn {
                items(state.favorites){ group ->
                    GroupItem(
                        group = group,
                        onEvent = onEvent)
                }
            }
        }
    }
}