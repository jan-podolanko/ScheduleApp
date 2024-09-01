package com.example.schedule.api

import com.example.schedule.data.dto.GroupsDto
import com.example.schedule.data.dto.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {
    @GET("/index.php?typ=G&id=237931&okres=4&xml")
    suspend fun testGetSchedule(): ScheduleDto

    @GET("/index.php?typ=G&xml")
    suspend fun getGroups(): GroupsDto

    @GET("/index.php?typ=G&okres=2&xml")
    suspend fun getSchedule(@Query("id") id: String): ScheduleDto
}