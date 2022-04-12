package com.sprill.habits.model.retrofit

import com.sprill.habits.model.retrofit.models.HabitDoneTuple
import com.sprill.habits.model.retrofit.models.ItemHabit
import com.sprill.habits.model.retrofit.models.UidTuple
import okhttp3.ResponseBody
import retrofit2.Response

interface IRetrofitHabitsRepository {

    suspend fun getHabitsAll(): Response<List<ItemHabit>>

    suspend fun sendItemHabit(itemHabit: ItemHabit) : Response<UidTuple>

    suspend fun deleteItemHabit(uidModel: UidTuple) : Response<ResponseBody>

    suspend fun habitDone(habitDoneTuple: HabitDoneTuple): Response<ResponseBody>
}