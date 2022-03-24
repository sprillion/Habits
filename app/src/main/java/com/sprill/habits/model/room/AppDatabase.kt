package com.sprill.habits.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sprill.habits.model.room.entities.ItemHabitEntity

@Database(
    version = 1,
    entities = [
       ItemHabitEntity::class
    ]
)

abstract class AppDatabase: RoomDatabase(){

    abstract fun getItemHabitDao(): ItemHabitDao

}