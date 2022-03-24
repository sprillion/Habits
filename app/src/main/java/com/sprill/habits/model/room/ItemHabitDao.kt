package com.sprill.habits.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.model.room.entities.ItemHabitEntity

@Dao
interface ItemHabitDao {

    @Query("SELECT * FROM habits")
    fun getHabitsAll(): LiveData<ArrayList<ItemHabit>>

    @Update(entity = ItemHabitEntity::class)
    fun updateItemHabit(itemHabit: ItemHabit)

    @Insert
    fun createItemHabit(itemHabit: ItemHabit)

    //fun getItemHabit(idItem: Int): Flow
}