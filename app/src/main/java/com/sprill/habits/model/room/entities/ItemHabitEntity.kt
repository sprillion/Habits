package com.sprill.habits.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sprill.habits.data.ItemHabit

@Entity(
    tableName = "habits"
)
data class ItemHabitEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val priority: Int,
    val type: Int,
    val countExecution: String,
    val period: String,
    val color: Int
) {

    fun toItemHabit() = ItemHabit(
        id = id,
        name = name,
        description = description,
        priority = priority,
        type = type,
        countExecution = countExecution,
        period = period,
        color = color
    )

    companion object{
        fun fromItemHabit(itemHabit: ItemHabit) = ItemHabitEntity(
            id = itemHabit.id,
            name =  itemHabit.name,
            description = itemHabit.description,
            priority = itemHabit.priority,
            type = itemHabit.type,
            countExecution = itemHabit.countExecution,
            period = itemHabit.period,
            color = itemHabit.color
        )
    }
}