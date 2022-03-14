package com.sprill.habits.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemHabit(val name: String, val description: String, val priority: Int, val type: Int, val countExecution: String, val period: String, val color: Int): Parcelable