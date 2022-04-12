package com.sprill.habits.model.retrofit

import com.sprill.habits.model.retrofit.models.HabitDoneTuple
import com.sprill.habits.model.retrofit.models.ItemHabit
import com.sprill.habits.model.retrofit.models.UidTuple
import okhttp3.ResponseBody
import retrofit2.Response

class RetrofitHabitsRepository(
    private val habitsApi: HabitsApi
): IRetrofitHabitsRepository {
    override suspend fun getHabitsAll() = habitsApi.getHabits()

    override suspend fun sendItemHabit(itemHabit: ItemHabit) = habitsApi.putHabit(itemHabit)

    override suspend fun deleteItemHabit(uidModel: UidTuple) = habitsApi.deleteHabit(uidModel)

    override suspend fun habitDone(habitDoneTuple: HabitDoneTuple): Response<ResponseBody> =
        habitsApi.postHabit(habitDoneTuple)
}