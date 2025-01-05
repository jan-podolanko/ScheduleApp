package com.example.schedule.api.repository

import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.data.dto.GroupsDto
import com.example.schedule.data.dto.ScheduleDto
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    //functions for fetching from internet
    suspend fun getGroups(): GroupsDto
    suspend fun getTeachers(): GroupsDto
    suspend fun getClassrooms(): GroupsDto
    suspend fun getGroupSchedule(id: String): ScheduleDto
    suspend fun getTeacherSchedule(id: String): ScheduleDto
    suspend fun getClassroomSchedule(id: String): ScheduleDto

    //db operations
    fun getLessonsByDateAsc(): Flow<List<Lesson>>
    fun getLessonsByDateDesc(): Flow<List<Lesson>>
    suspend fun insertLesson(lesson: Lesson)
    suspend fun updateLesson(lesson: Lesson)
    suspend fun deleteLesson(lesson: Lesson)
    suspend fun insertGroup(group: List<Lesson>)
    suspend fun changeLessonVisibility(visibility: Boolean, subject: String, type: String, groupId: String)

    fun getFavorites(): Flow<List<Group>>
    fun getFavoriteGroup(id: String): Flow<List<Lesson>>
    suspend fun deleteFavorite(group: Group)
    suspend fun insertFavorite(group: Group)
}