package com.example.schedule.api

import com.example.schedule.data.dto.GroupsDto
import com.example.schedule.data.dto.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {
    @GET("/index.php?typ=G&xml")
    suspend fun getGroups(): GroupsDto

    @GET("/index.php?typ=N&xml")
    suspend fun getTeachers(): GroupsDto

    @GET("/index.php?typ=S&xml")
    suspend fun getClassrooms(): GroupsDto

    @GET("/index.php?typ=G&okres=2&xml")
    suspend fun getGroupSchedule(@Query("id") id: String): ScheduleDto

    @GET("/index.php?typ=N&okres=2&xml")
    suspend fun getTeacherSchedule(@Query("id") id: String): ScheduleDto

    @GET("/index.php?typ=S&okres=2&xml")
    suspend fun getClassroomSchedule(@Query("id") id: String): ScheduleDto
}