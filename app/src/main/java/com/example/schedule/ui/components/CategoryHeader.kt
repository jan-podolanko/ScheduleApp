package com.example.schedule.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.schedule.ui.theme.Typography
import java.time.LocalDate

@Composable
fun CategoryHeader(date: LocalDate){
    val scheduleItemModifier =
        if(date.isBefore(LocalDate.now()))
            Modifier.alpha(0.5f)
        else
            Modifier.alpha(1.0f)

    Row(
        modifier = scheduleItemModifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
    ){
        Text(
            text = date.toString(),
            style = Typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
    }
}