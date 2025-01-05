package com.example.schedule.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table")
data class Group(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String
)