package com.example.schedule.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson
import com.example.schedule.data.dto.ScheduleDto
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Query("SELECT * FROM lesson_table ORDER BY date ASC")
    fun getLessonsByDateAsc(): Flow<List<Lesson>>

    @Query("SELECT * FROM lesson_table ORDER BY date DESC")
    fun getLessonsByDateDesc(): Flow<List<Lesson>>

    @Upsert
    suspend fun insertLesson(lesson: Lesson)

    @Insert
    suspend fun insertGroup(group: List<Lesson>)

    @Delete
    suspend fun deleteLesson(lesson: Lesson)

    @Query("DELETE FROM lesson_table")
    suspend fun deleteAllLessons()

    @Query("SELECT * FROM group_table ORDER BY name")
    fun getFavorites(): Flow<List<Group>>

    @Insert
    suspend fun insertFavorite(group: Group)

    @Delete
    suspend fun deleteFavorite(group: Group)

    @Query("DELETE FROM group_table")
    suspend fun deleteAllGroups()

    @Query("SELECT * FROM lesson_table WHERE groupId LIKE :id ORDER BY date")
    fun getFavoriteGroup(id: String): Flow<List<Lesson>>
}