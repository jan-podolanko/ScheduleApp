package com.example.schedule.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schedule.ui.theme.Typography
import java.sql.Time


@Composable
fun ScheduleItem(
    subject: String,
    teacher: String,
    startTime: String,
    endTime: String,
    type: String,
    place: String,
    modifier: Modifier
) {
    val secColor = MaterialTheme.colorScheme.secondary

    Column(modifier = modifier) {
        Text(text = subject, style = Typography.titleSmall)
        Text(text = "$startTime-$endTime", style = Typography.bodySmall)
        Text(text = teacher, style = Typography.bodySmall, fontWeight = FontWeight.SemiBold)
        Text(text = type, style = Typography.bodySmall)
        Text(text = place, style = Typography.bodySmall, modifier=Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp))
        HorizontalDivider()
    }
}