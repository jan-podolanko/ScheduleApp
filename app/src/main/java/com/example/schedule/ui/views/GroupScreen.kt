package com.example.schedule.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.schedule.R
import com.example.schedule.ui.components.GenericGroupItem
import com.example.schedule.ui.components.GroupScreenSchedule
import com.example.schedule.viewmodels.GroupsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen(
    viewModel: GroupsViewModel,
) {
    var searchState by remember {
        mutableStateOf("")
    }
    val groupsUiState = viewModel.groupsUiState

    val groupsFiltered by remember {
        mutableStateOf(groupsUiState?.groups)
    }
    Column {
        if (viewModel.currentGroup != null){
            GroupScreenSchedule(schedule = viewModel.currentGroup!!)
        } else {
            DockedSearchBar(
                query = searchState,
                onQueryChange = { searchState = it },
                onSearch = {},
                active = false,
                onActiveChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Search,
                        stringResource(R.string.group_screen_search_icon)
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.group_screen_search_placeholder),
                        modifier = Modifier.padding(0.dp)
                    )
                }
            ) {}
            if (groupsFiltered != null) {
                LazyColumn {
                    items(groupsFiltered!!.filter {
                        it.name.contains(searchState, ignoreCase = true)
                    }) { group ->
                        GenericGroupItem(schedule = group, onEvent = viewModel::onGroupEvent)
                    }
                }
            }
        }
    }
}