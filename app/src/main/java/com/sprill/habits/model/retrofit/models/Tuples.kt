package com.sprill.habits.model.retrofit.models

import com.google.gson.annotations.SerializedName

data class UidTuple(
    @SerializedName("uid")
    val uid: String
)

data class HabitDoneTuple(
    @SerializedName("date")
    val date: Long,

    @SerializedName("habit_uid")
    val uid: String
)

data class ErrorTuple(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String
)