package com.sprill.habits.model.room

import android.content.Context
import androidx.room.Room

object Repository {

    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val database: AppDatabase by lazy{
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "Database"
        )
            .build()
    }

    val habitsRepository: IRoomHabitsRepository by lazy {
        RoomHabitsRepository(database.getItemHabitDao())
    }
}