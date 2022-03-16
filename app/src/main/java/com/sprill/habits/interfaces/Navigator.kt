package com.sprill.habits.interfaces

import androidx.fragment.app.Fragment
import com.sprill.habits.data.ItemHabit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showCreateScreen()

    fun showEditScreen(itemHabit: ItemHabit, idItem: Int)

    fun showAboutAppScreen()

    fun goBack()

    fun goToHabitsList()
}