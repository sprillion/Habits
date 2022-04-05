package com.sprill.habits.model
import com.sprill.habits.model.retrofit.ItemHabit;
import okhttp3.ResponseBody

import retrofit2.Response
import retrofit2.http.*

interface HabitsApi{

    @GET("/api/habit")
    suspend fun getHabits(): Response<List<ItemHabit>>

    @GET("/api/habit")
    suspend fun getItemHabit(@Query("uid") idItem: String): Response<ItemHabit>

    @PUT("/api/habit")
    suspend fun putHabit(@Body itemHabit: ItemHabit): Response<ResponseBody>

    @DELETE("/api/habit")
    suspend fun deleteHabit(itemHabit: ItemHabit)

    @POST("/api/habit")
    suspend fun postHabit(itemHabit: ItemHabit)

}