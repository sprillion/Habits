package com.sprill.habits.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Delete(entity = ItemHabit::class)
    fun deleteItemHabit(itemHabit: ItemHabit)

    @Query("SELECT * FROM habits")
    fun getHabitsForSort(): List<ItemHabit>


}