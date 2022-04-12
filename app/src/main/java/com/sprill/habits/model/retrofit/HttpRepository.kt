package com.sprill.habits.model.retrofit

import com.google.gson.GsonBuilder
import com.sprill.habits.model.retrofit.json.ItemHabitJsonSerializer
import com.sprill.habits.model.retrofit.models.ItemHabit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpRepository {
    private const val TOKEN = "5c2f56cf-9c42-42db-bbd4-c1e0a002bedc"
    private const val BASE_URL = "https://droid-test-server.doubletapp.ru/"

    private lateinit var habitsApi: HabitsApi

    val habitsRepository: IRetrofitHabitsRepository by lazy {
        RetrofitHabitsRepository(habitsApi)
    }

    fun configureRetrofit() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        habitsApi = retrofit.create(HabitsApi::class.java)
    }

    private fun okHttpClient(): OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", TOKEN)
                    .addHeader("Content-Type", "application/json")

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
        return okHttpClient
    }

    private fun gson() = GsonBuilder()
        .registerTypeAdapter(ItemHabit::class.java, ItemHabitJsonSerializer())
        .create()
}