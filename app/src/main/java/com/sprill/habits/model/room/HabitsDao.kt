package com.sprill.habits.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sprill.habits.model.room.entities.ItemHabit

@Dao
interface HabitsDao {

    @Query("SELECT * FROM habits")
    fun getHabitsAll(): LiveData<List<ItemHabit>>

    @Query("SELECT * FROM habits WHERE id = :idItem")
    fun getItemHabit(idItem: Int): ItemHabit

    @Update(entity = ItemHabit::class)
    fun updateItemHabit(itemHabit: ItemHabit)

    @Insert(entity = ItemHabit::class)
    fun createItemHabit(itemHabitEntity: ItemHabit)

    @Query("SELECT * FROM habits")
    fun getHabitsForSort(): List<ItemHabit>


}