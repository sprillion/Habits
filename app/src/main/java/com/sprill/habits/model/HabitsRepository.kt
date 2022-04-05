package com.sprill.habits.model

import com.sprill.habits.model.retrofit.ItemHabit
import okhttp3.ResponseBody
import retrofit2.Response

interface HabitsRepository {

    suspend fun getHabitsAll(): Response<List<ItemHabit>>

    suspend fun getItemHabits(idItem: String): Response<ItemHabit>

    suspend fun sendItemHabit(itemHabit: ItemHabit) : Response<ResponseBody>
//
//    suspend fun deleteItemHabit(itemHabit: ItemHabit)
//
//    suspend fun getItemHabit(idItem: Int): ItemHabit
//
//    suspend fun getHabitsId(sortUp: Boolean): List<ItemHabit>
//
//    suspend fun getHabitsPriority(sortUp: Boolean): List<ItemHabit>
//
//    suspend fun getSearchedHabits(content: CharSequence):ArrayList<ItemHabit>
}