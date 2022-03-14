package com.sprill.habits.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HabitResult(val itemHabit: ItemHabit?, val idItem: Int?) : Parcelable
