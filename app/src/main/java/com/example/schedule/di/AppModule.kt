package com.example.schedule.di

import android.app.Application
import androidx.room.Room
import com.example.schedule.api.ScheduleApi
import com.example.schedule.api.repository.ScheduleRepository
import com.example.schedule.api.repository.ScheduleRepositoryImpl
import com.example.schedule.data.db.LessonDatabase
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = "https://planzajec.uek.krakow.pl/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ScheduleApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                TikXmlConverterFactory.create(
                TikXml.Builder().exceptionOnUnreadXml(false).build()
            ))
            .build()
            .create(ScheduleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): LessonDatabase {
        return Room.databaseBuilder(
            app,
            LessonDatabase::class.java,
            "lesson_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(api: ScheduleApi, app: Application, database: LessonDatabase): ScheduleRepository {
        return ScheduleRepositoryImpl(api, app, database)
    }
}