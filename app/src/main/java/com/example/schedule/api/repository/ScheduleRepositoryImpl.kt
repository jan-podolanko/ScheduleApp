package com.example.schedule.api.repository

import android.app.Application
import com.example.schedule.api.ScheduleApi
import com.example.schedule.data.db.LessonDatabase
import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.data.dto.GroupsDto
import com.example.schedule.data.dto.ScheduleDto
import kotlinx.coroutines.flow.Flow


class ScheduleRepositoryImpl(
    private val api: ScheduleApi,
    private val appContext: Application,
    private val database: LessonDatabase
): ScheduleRepository {

    override suspend fun getGroups(): GroupsDto {
        return api.getGroups()
    }

    override suspend fun getTeachers(): GroupsDto {
        return api.getTeachers()
    }

    override suspend fun getClassrooms(): GroupsDto {
        return api.getClassrooms()
    }

    override suspend fun getGroupSchedule(id: String): ScheduleDto {
        return api.getGroupSchedule(id)
    }

    override suspend fun getTeacherSchedule(id: String): ScheduleDto {
        return api.getTeacherSchedule(id)
    }

    override suspend fun getClassroomSchedule(id: String): ScheduleDto {
        return api.getClassroomSchedule(id)
    }

    override fun getLessonsByDateAsc(): Flow<List<Lesson>> {
        return database.lessonDao.getLessonsByDateAsc()
    }

    override fun getLessonsByDateDesc(): Flow<List<Lesson>> {
        return database.lessonDao.getLessonsByDateDesc()
    }

    override suspend fun insertLesson(lesson: Lesson) {
        return database.lessonDao.insertLesson(lesson)
    }

    override suspend fun updateLesson(lesson: Lesson) {
        return database.lessonDao.updateLesson(lesson)
    }

    override suspend fun deleteLesson(lesson: Lesson) {
        return database.lessonDao.deleteLesson(lesson)
    }

    override suspend fun insertGroup(group: List<Lesson>) {
        return database.lessonDao.insertGroup(group)
    }

    override suspend fun changeLessonVisibility(
        visibility: Boolean,
        subject: String,
        type: String,
        groupId: String
    ) {
        return database.lessonDao.changeLessonVisibility(visibility,subject,type,groupId)
    }

    override fun getFavorites(): Flow<List<Group>> {
        return database.lessonDao.getFavorites()
    }

    override fun getFavoriteGroup(id: String): Flow<List<Lesson>> {
        return database.lessonDao.getFavoriteGroup(id)
    }

    override suspend fun deleteFavorite(group: Group) {
        return database.lessonDao.deleteFavorite(group)
    }

    override suspend fun insertFavorite(group: Group) {
        return database.lessonDao.insertFavorite(group)
    }
}