package com.example.schedule.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "lesson_table",
    foreignKeys = [ForeignKey(
        entity = Group::class,
        parentColumns = ["id"],
        childColumns = ["groupId"],
        onDelete = CASCADE
    )],
    indices = [Index("groupId")]
)
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val subject: String?,
    val teachers: List<String>? = null,
    @ColumnInfo(name = "start_time")
    val startTime: String,
    @ColumnInfo(name = "end_time")
    val endTime: String,
    val type: String,
    val place: String?,
    val date: LocalDate,
    val day: String,
    val group: String,
    val groupId: String,
    val comments: String? = null,
    val visibility: Boolean = true,
    val important: Boolean = false
)