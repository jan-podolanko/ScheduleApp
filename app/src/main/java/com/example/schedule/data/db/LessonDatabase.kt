package com.example.schedule.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.schedule.data.db.dao.LessonDao
import com.example.schedule.data.db.entities.Group
import com.example.schedule.data.db.entities.Lesson

@Database(
    entities = [Lesson::class, Group::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LessonDatabase : RoomDatabase() {
    abstract val lessonDao: LessonDao
}
