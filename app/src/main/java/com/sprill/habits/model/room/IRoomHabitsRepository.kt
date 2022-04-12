package com.sprill.habits.model.room

import androidx.lifecycle.LiveData

import com.sprill.habits.model.room.entities.ItemHabitEntity
import kotlinx.coroutines.flow.Flow

interface IRoomHabitsRepository {

    fun getHabitsAll(): Flow<List<ItemHabitEntity>>

    suspend fun sendItemHabit(itemHabitEntity: ItemHabitEntity)

    suspend fun fillHabits(habitEntities: List<ItemHabitEntity>)

    suspend fun deleteItemHabit(itemHabitEntity: ItemHabitEntity)

    suspend fun getItemHabit(idItem: String): ItemHabitEntity

    suspend fun getSortedHabits(typeSort: TypeSort, sortUp: Boolean): List<ItemHabitEntity>

//    suspend fun getHabitsDate(sortUp: Boolean): List<ItemHabitEntity>
//
//    suspend fun getHabitsPriority(sortUp: Boolean): List<ItemHabitEntity>

    suspend fun getSearchedHabits(content: CharSequence):List<ItemHabitEntity>

    suspend fun setDoneDate(doneDates: String, uid: String)
}