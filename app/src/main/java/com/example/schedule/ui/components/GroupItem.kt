package com.example.schedule.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schedule.R
import com.example.schedule.viewmodels.events.FavoritesEvent
import com.example.schedule.data.db.entities.Group
import com.example.schedule.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GroupItem(
    group: Group,
    onEvent: (FavoritesEvent) -> Unit,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {

    Column(modifier = Modifier.clickable { onEvent(FavoritesEvent.SetSchedule(group)) }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = group.name,
                style = Typography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(15.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                onEvent(FavoritesEvent.DeleteGroup(group))
                scope.launch{ snackbarHostState.showSnackbar("Usunięto ${group.name} z ulubionych!") }
            }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(R.string.group_screen_delete_button))
            }
        }
        HorizontalDivider()
    }
}