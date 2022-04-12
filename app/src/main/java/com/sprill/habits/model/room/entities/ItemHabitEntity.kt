package com.sprill.habits.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sprill.habits.model.retrofit.models.ItemHabit
import com.sprill.habits.model.room.converters.ListDateConverter

@Entity(
    tableName = "habits"
)
data class ItemHabitEntity(
    //@PrimaryKey(autoGenerate = true) val id: Int? = null,
    @PrimaryKey val uid: String,
    val name: String,
    val description: String,
    val priority: Int,
    val type: Int,
    val countExecution: Int = 0,
    val period: Int = 0,
    val color: Int,
    val date: Long,
    val doneDates: List<Long>?
){

    companion object {
        fun converter(itemHabit: ItemHabit): ItemHabitEntity{
            return ItemHabitEntity(
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

        fun newItem(itemHabit: ItemHabitEntity, uid: String): ItemHabitEntity{
            return ItemHabitEntity(
                uid = uid,
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