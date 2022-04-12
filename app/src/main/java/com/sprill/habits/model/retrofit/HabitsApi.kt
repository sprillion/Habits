package com.sprill.habits.model.retrofit

import com.sprill.habits.model.retrofit.models.HabitDoneTuple
import com.sprill.habits.model.retrofit.models.ItemHabit
import com.sprill.habits.model.retrofit.models.UidTuple
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface HabitsApi {
    @GET("/api/habit")
    suspend fun getHabits(): Response<List<ItemHabit>>

    @PUT("/api/habit")
    suspend fun putHabit(@Body itemHabit: ItemHabit): Response<UidTuple>

    @HTTP(method = "DELETE", path = "/api/habit", hasBody = true)
    suspend fun deleteHabit(@Body uidModel: UidTuple): Response<ResponseBody>

    @POST("/api/habit_done")
    suspend fun postHabit(@Body habitDoneTuple: HabitDoneTuple): Response<ResponseBody>
}