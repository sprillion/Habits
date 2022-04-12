package com.sprill.habits.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sprill.habits.model.room.converters.ListDateConverter
import com.sprill.habits.model.room.entities.ItemHabitEntity

@Database(
    version = 1,
    entities = [
        ItemHabitEntity::class
    ]
)
@TypeConverters(ListDateConverter::class)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getItemHabitDao(): HabitsDao

}