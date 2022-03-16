package com.sprill.habits.interfaces

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.sprill.habits.data.HabitResult
import com.sprill.habits.data.ItemHabit

//typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showCreateScreen()

    fun showEditScreen(itemHabit: ItemHabit, idItem: Int)

    fun showAboutAppScreen()

    fun goBack()

    fun goToHabitsList()

//    fun publishResult(result: HabitResult)
//
//    fun listenResult(habitResultClass: Class<HabitResult>, owner: LifecycleOwner, listener: ResultListener<HabitResult>)
}