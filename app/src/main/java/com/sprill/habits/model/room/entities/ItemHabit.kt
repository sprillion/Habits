package com.sprill.habits.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "habits"
)
data class ItemHabit(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val priority: Int,
    val type: Int,
    val countExecution: String,
    val period: String,
    val color: Int
)