package com.sprill.habits

import java.io.Serializable

data class ItemHabit(val name: String, val description: String, val priority: Int, val type: Int, val countExecution: String, val period: String, val color: Int): Serializable
