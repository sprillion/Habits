package com.sprill.habits.model

import androidx.lifecycle.LiveData

import com.sprill.habits.model.room.entities.ItemHabit

interface HabitsRepository {

    fun getHabitsAll(): LiveData<List<ItemHabit>>

    suspend fun sendItemHabit(itemHabit: ItemHabit)

    suspend fun deleteItemHabit(itemHabit: ItemHabit)

    suspend fun getItemHabit(idItem: Int): ItemHabit

    suspend fun getHabitsId(sortUp: Boolean): List<ItemHabit>

    suspend fun getHabitsPriority(sortUp: Boolean): List<ItemHabit>

    suspend fun getSearchedHabits(content: CharSequence):ArrayList<ItemHabit>
}