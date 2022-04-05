package com.sprill.habits.model.retrofit

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ItemHabit(

    @SerializedName("uid")
    val id: String,

    @SerializedName("title")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("priority")
    val priority: Int,

    @SerializedName("type")
    val type: Int,

    @SerializedName("count")
    val countExecution: String,

    @SerializedName("frequency")
    val period: String,

    @SerializedName("color")
    val color: Int,

    @SerializedName("date")
    val date: Int
)