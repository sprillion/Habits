package com.sprill.habits.model.room

import androidx.lifecycle.LiveData
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.model.HabitsRepository

class RoomHabitsRepository(
    private val habitsDao: HabitsDao
): HabitsRepository {

    override fun getHabitsAll(): LiveData<List<ItemHabit>> = habitsDao.getHabitsAll()

    override fun createItemHabit(itemHabit: ItemHabit) {
        habitsDao.createItemHabit(itemHabit)
    }

    override fun updateItemHabit(itemHabit: ItemHabit) {
        habitsDao.updateItemHabit(itemHabit)
    }

    override fun deleteItemHabit(itemHabit: ItemHabit) {
        habitsDao.deleteItemHabit(itemHabit)
    }

    override fun getItemHabit(idItem: Int) = habitsDao.getItemHabit(idItem)

    override fun getHabitsId(sortUp: Boolean): List<ItemHabit> =
        if (sortUp)
            habitsDao.getHabitsForSort()
        else
            habitsDao.getHabitsForSort().asReversed()


    override fun getHabitsPriority(sortUp:Boolean): List<ItemHabit>{
        val sortedHabits = habitsDao.getHabitsForSort() as MutableList

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

    override fun getSearchedHabits(content: CharSequence):ArrayList<ItemHabit>{
        val searchHabits = arrayListOf<ItemHabit>()
        habitsDao.getHabitsForSort().forEach{
            if (it.name.contains(content))
                searchHabits.add(it)
        }
        return searchHabits
    }


}