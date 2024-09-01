package com.example.schedule.data.db

import androidx.room.ColumnInfo
import com.example.schedule.data.db.entities.Lesson

data class LessonState(
    val lessons: List<Lesson> = emptyList(),
    val lesson: Lesson = Lesson(
        subject = "subject",
        teacher = "teacher",
        startTime = "start time",
        endTime = "end time",
        type = "type",
        place = "place",
        date = "date",
        day = "day",
        group = "group",
        groupId = "groupId"
    ),
    val sortType: SortType = SortType.DATE_ASC
)