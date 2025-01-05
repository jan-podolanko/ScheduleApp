package com.example.schedule.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schedule.R
import com.example.schedule.data.dto.GroupDto
import com.example.schedule.ui.theme.Typography
import com.example.schedule.viewmodels.events.GroupEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GenericGroupItem(
    schedule: GroupDto,
    onEvent: (GroupEvent) -> Unit
) {
    Column(modifier = Modifier.clickable { onEvent(GroupEvent.GetGroup(schedule.id, schedule.type)) }) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = schedule.name,
                style = Typography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(15.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    onEvent(GroupEvent.AddGroup(schedule.id, schedule.type))
                }
            }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_star_outline_24), contentDescription = "Favorite")
            }
        }
        HorizontalDivider()
    }

}