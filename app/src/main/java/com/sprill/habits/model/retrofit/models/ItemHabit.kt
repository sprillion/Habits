package com.sprill.habits.model.retrofit.models

import com.google.gson.annotations.SerializedName
import com.sprill.habits.model.room.entities.ItemHabitEntity

data class ItemHabit(

    @SerializedName("uid")
    val uid: String,

    @SerializedName("title")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("priority")
    val priority: Int,

    @SerializedName("type")
    val type: Int,

    @SerializedName("count")
    val countExecution: Int,

    @SerializedName("frequency")
    val period: Int,

    @SerializedName("color")
    val color: Int,

    @SerializedName("date")
    val date: Long,

    @SerializedName("done_dates")
    val doneDates: List<Long>?

){
    companion object {
        fun converter(itemHabit: ItemHabitEntity): ItemHabit {
            return ItemHabit(
                uid = itemHabit.uid,
                name = itemHabit.name,
                description = itemHabit.description,
                priority = itemHabit.priority,
                type = itemHabit.type,
                countExecution = itemHabit.countExecution,
                period = itemHabit.period,
                color = itemHabit.color,
                date = itemHabit.date,
                doneDates = itemHabit.doneDates
            )
        }
    }

}
