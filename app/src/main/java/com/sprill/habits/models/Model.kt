package com.sprill.habits.models

import com.sprill.habits.data.ItemHabit

class Model {

    private val habits = arrayListOf<ItemHabit>()

    fun addHabit(itemHabit: ItemHabit){
        habits.add(itemHabit)
    }

    fun changeHabit(itemHabit: ItemHabit, idItem: Int){
        habits[idItem] = itemHabit
    }

    fun getHabitsAll() = habits

    fun getItemHabit(idItem: Int) = habits[idItem]

    fun getNewId() = habits.lastIndex + 1

    fun search(content: CharSequence): ArrayList<ItemHabit>{
        val searchHabits = arrayListOf<ItemHabit>()
        habits.forEach{
            if (it.name.contains(content))
                searchHabits.add(it)
        }
        return searchHabits
    }

    fun getReversedHabits() =
        if (habits.size == 0) getHabitsAll() else habits.reversed() as ArrayList

    fun getSortedPriorityHabits(sortUp: Boolean): ArrayList<ItemHabit>{
        val sortedHabits = arrayListOf<ItemHabit>()
        val startPriority = if (sortUp) PRIORITY_LOW else PRIORITY_HIGH
        val endPriority = if (sortUp) PRIORITY_HIGH else PRIORITY_LOW
        val range = if (sortUp) startPriority downTo endPriority else startPriority .. endPriority
        for (i in range){
            sortedHabits.addAll(habits.filter { it.priority == i } as ArrayList)
        }
        return sortedHabits
    }

    companion object{
        private const val PRIORITY_HIGH = 0
        private const val PRIORITY_LOW = 2
    }
}