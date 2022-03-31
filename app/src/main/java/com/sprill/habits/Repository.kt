package com.sprill.habits

import android.content.Context
import androidx.room.Room
import com.sprill.habits.model.HabitsRepository
import com.sprill.habits.model.room.AppDatabase
import com.sprill.habits.model.room.RoomHabitsRepository

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
            //.allowMainThreadQueries()
            .build()
    }

    val habitsRepository: HabitsRepository by lazy {
        RoomHabitsRepository(database.getItemHabitDao())
    }
}