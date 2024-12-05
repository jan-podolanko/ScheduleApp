package com.example.schedule.api.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.data.dto.GroupDto
import com.example.schedule.data.dto.GroupsDto
import com.example.schedule.data.dto.ScheduleDto
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    //functions for fetching from internet
    suspend fun testGetSchedule(): ScheduleDto
    suspend fun getGroups(): GroupsDto
    suspend fun getTeachers(): GroupsDto
    suspend fun getSchedule(id: String): ScheduleDto

    //db operations
    fun getLessonsByDateAsc(): Flow<List<Lesson>>
    fun getLessonsByDateDesc(): Flow<List<Lesson>>
    suspend fun insertLesson(lesson: Lesson)
    suspend fun updateLesson(lesson: Lesson)
    suspend fun deleteLesson(lesson: Lesson)
    suspend fun insertGroup(group: List<Lesson>)


    fun getFavorites(): Flow<List<Group>>
    fun getFavoriteGroup(id: String): Flow<List<Lesson>>
    suspend fun deleteFavorite(group: Group)
    suspend fun insertFavorite(group: Group)
}