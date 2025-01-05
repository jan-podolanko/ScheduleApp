package com.example.schedule.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.schedule.ui.theme.Typography


@Composable
fun ScheduleItem(
    subject: String?,
    teachers: List<String>?,
    startTime: String,
    endTime: String,
    type: String,
    place: String?,
    modifier: Modifier
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(13.dp, 3.dp, 0.dp, 3.dp)
    ) {
        Text(text = subject.toString(), style = Typography.titleSmall)
        Text(text = "$startTime-$endTime", style = Typography.bodySmall)
        if(teachers != null){
            Text(text = teachers.joinToString(", "), style = Typography.bodySmall, fontWeight = FontWeight.SemiBold)
        }
        Text(text = type, style = Typography.bodySmall)
        if (place != null) {
            Text(text = place, style = Typography.bodySmall, modifier=Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp))
        }
    }
}