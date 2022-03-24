package com.sprill.habits.model

import android.content.Context
import androidx.room.Room
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.model.room.AppDatabase

class AppModel(context: Context) {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "Database"
    ).build()

    fun getHabitsAll() {
        val habits = db.getItemHabitDao().getHabitsAll()
       // habits.observe(lifec)
    }

    fun createItemHabit(itemHabit: ItemHabit){
        db.getItemHabitDao().createItemHabit(itemHabit)
    }

    fun updateItemHabit(itemHabit: ItemHabit){
        db.getItemHabitDao().updateItemHabit(itemHabit)
    }

}