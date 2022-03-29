package com.sprill.habits.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sprill.habits.model.room.entities.ItemHabit

@Database(
    version = 1,
    entities = [
        ItemHabit::class
    ]
)

abstract class AppDatabase: RoomDatabase(){

    abstract fun getItemHabitDao(): HabitsDao

}