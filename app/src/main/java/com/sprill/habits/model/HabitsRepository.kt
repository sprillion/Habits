package com.sprill.habits.model

import androidx.lifecycle.LiveData

import com.sprill.habits.model.room.entities.ItemHabit

interface HabitsRepository {

    fun getHabitsAll(): LiveData<List<ItemHabit>>

    fun updateItemHabit(itemHabit: ItemHabit)

    fun createItemHabit(itemHabit: ItemHabit)

    fun deleteItemHabit(itemHabit: ItemHabit)

    fun getItemHabit(idItem: Int): ItemHabit

    fun getHabitsId(sortUp: Boolean): List<ItemHabit>

    fun getHabitsPriority(sortUp: Boolean): List<ItemHabit>

    fun getSearchedHabits(content: CharSequence):ArrayList<ItemHabit>
}