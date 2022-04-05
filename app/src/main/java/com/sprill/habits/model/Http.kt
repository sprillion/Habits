package com.sprill.habits.model


import com.sprill.habits.model.retrofit.RetrofitHabitsRepository
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Http {

    private const val TOKEN = "5c2f56cf-9c42-42db-bbd4-c1e0a002bedc"
    private const val BASE_URL = "https://droid-test-server.doubletapp.ru/"

    private lateinit var habitsApi: HabitsApi

    val habitsRepository: HabitsRepository by lazy {
        RetrofitHabitsRepository(habitsApi)
    }

    fun configureRetrofit() {
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

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        habitsApi = retrofit.create(HabitsApi::class.java)
    }
}
