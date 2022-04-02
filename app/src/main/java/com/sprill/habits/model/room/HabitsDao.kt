package com.sprill.habits.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sprill.habits.model.room.entities.ItemHabit

@Dao
interface HabitsDao {

    @Query("SELECT * FROM habits")
    fun getHabitsAll(): LiveData<List<ItemHabit>>

    @Query("SELECT * FROM habits WHERE id = :idItem")
    suspend fun getItemHabit(idItem: Int): ItemHabit

    @Update(entity = ItemHabit::class)
    suspend fun updateItemHabit(itemHabit: ItemHabit): Int

    @Insert(entity = ItemHabit::class)
    suspend fun createItemHabit(itemHabitEntity: ItemHabit)

    @Delete(entity = ItemHabit::class)
    suspend fun deleteItemHabit(itemHabit: ItemHabit)

    @Query("SELECT * FROM habits")
    suspend fun getHabitsForSort(): List<ItemHabit>


}