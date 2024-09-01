package com.example.schedule.data.db

import com.example.schedule.data.db.entities.Lesson

sealed interface LessonEvent {
    object SaveLesson: LessonEvent
    data class SetLesson(val lesson: Lesson): LessonEvent
    data class SortLessons(val sortType: SortType): LessonEvent
    data class DeleteLesson(val lesson: Lesson): LessonEvent
}