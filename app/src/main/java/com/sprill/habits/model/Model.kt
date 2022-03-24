package com.sprill.habits.model

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
        if (habits.size < 2) getHabitsAll() else habits.reversed() as ArrayList

    fun getSortedPriorityHabits(sortUp: Boolean): ArrayList<ItemHabit>{
        val sortedHabits = arrayListOf<ItemHabit>()
        sortedHabits.addAll(habits)
        sortedHabits.apply {
            if (sortUp)
                sortByDescending {
                    it.priority
                }
            else
                sortBy{
                    it.priority
                }
        }
        return sortedHabits
    }
}