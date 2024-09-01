/*
package com.example.schedule.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schedule.data.db.LessonEvent
import com.example.schedule.data.db.LessonState
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.data.dto.LessonDto
import com.example.schedule.data.dto.ScheduleDto
import com.example.schedule.ui.components.ScheduleDbItem
import com.example.schedule.ui.components.ScheduleItem
import com.example.schedule.ui.components.ScheduleXmlItem
import com.example.schedule.ui.viewmodels.LessonDbViewModel
import com.example.schedule.ui.viewmodels.ScheduleViewModel

@Composable
fun TestScreen(lessonState: LessonState, onEvent: (LessonEvent) -> Unit, scheduleViewModel: ScheduleViewModel) {

    val scheduleUiState = scheduleViewModel.scheduleUiState
    var error = scheduleViewModel.error
*/
/*
    val lessonViewModel: LessonDbViewModel by viewModels(LessonDbViewModelFactory((application as LessonApplication)).repository)
*//*

*/
/*    Row(modifier = Modifier.padding(30.dp)) {
        ExtraDbButton(state = lessonState, onEvent = onEvent)
        AddToDbButton(state = lessonState, onEvent = onEvent)
    }*//*

    Column(modifier = Modifier.padding(30.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            LazyColumn {
                items(lessonState.lessons) { lesson ->
                    ScheduleDbItem(
                        lesson = lesson,
                        modifier = Modifier.padding(13.dp,3.dp,0.dp,3.dp),
                        onEvent = onEvent
                    )
                }
            }
        }
        TestXmlLazyColumn(scheduleUiState, onEvent)
    }




    // some buttons
*/
/*    Column {
        Row {
            Button(onClick = { Log.d("TAG", "test")}
            ) {
                Text(text = "add to db")
            }
            Button(onClick = { *//*
*/
/*scheduleViewModel.getSchedule2() *//*
*/
/*}) {
                Text(text = "download file")
            }
        }
    }*//*

    //test xml stuff
*/
/*    Text(text = error, modifier = Modifier.padding(10.dp))
    if (scheduleUiState?.classes != null) {
        LazyColumn {
            items(scheduleUiState.classes) { lesson ->
                ScheduleItem(
                    subject = lesson.subject,
                    teacher = lesson.teacher,
                    startTime = lesson.startTime,
                    endTime = lesson.endTime,
                    type = lesson.type,
                    place = lesson.place,
                    modifier = Modifier.padding(13.dp,3.dp,0.dp,3.dp)
                )
            }
        }
    }*//*

    */
/*TestXmlLazyColumn(schedule = scheduleUiState)*//*

}

@Composable
fun AddToDbButton(
    state: LessonState,
    onEvent: (LessonEvent) -> Unit
) {
    Button(onClick = { onEvent(LessonEvent.SaveLesson) }) {
        Text(text = "add test to db")
    }
}

@Composable
fun ExtraDbButton(
    state: LessonState,
    onEvent: (LessonEvent) -> Unit
) {
    Button(onClick = {
        onEvent(LessonEvent.SetLesson(Lesson(
            subject = "Przetwarzanie języka naturalnego",
            teacher = "mgr Katarzyna Wójcik",
            startTime = "18:30",
            endTime = "19:15",
            type = "ćwiczenia",
            place = "Paw.A 121 lab. Win10, Office21",
            date = "2023-03-09",
            day = "idk",
            group = "idk",
            groupId = "id"
        )))
    }) {
        Text(text = "another to db")
    }
}

@Composable
fun TestXmlLazyColumn(schedule: ScheduleDto?, onEvent: (LessonEvent) -> Unit) {
    if (schedule?.classes != null) {
        LazyColumn {
            items(schedule.classes) { lesson ->
                ScheduleXmlItem(
                    subject = lesson.subject,
                    teacher = lesson.teacher,
                    startTime = lesson.startTime,
                    endTime = lesson.endTime,
                    type = lesson.type,
                    place = lesson.place,
                    modifier = Modifier.padding(13.dp,3.dp,0.dp,3.dp),
                    date = lesson.date,
                    day = lesson.day,
                    group = schedule.scheduleName,
                    groupId = schedule.scheduleId,
                    onEvent = onEvent
                )
            }
        }
    }
}*/
