package com.example.schedule.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import com.example.schedule.data.db.entities.Lesson
import java.time.LocalDate
@RequiresApi(Build.VERSION_CODES.O)
data class LessonState(
    val lessons: List<Lesson> = emptyList(),
    val lesson: Lesson = Lesson(
        subject = "subject",
        teacher = "teacher",
        startTime = "start time",
        endTime = "end time",
        type = "type",
        place = "place",
        date = LocalDate.now(),
        day = "day",
        group = "group",
        groupId = "groupId"
    ),
    val sortType: SortType = SortType.DATE_ASC
)