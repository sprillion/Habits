package com.sprill.habits.model.room

import android.util.Log
import androidx.lifecycle.LiveData
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.model.HabitsRepository
import kotlinx.coroutines.delay

class RoomHabitsRepository(
    private val habitsDao: HabitsDao
): HabitsRepository {

    override fun getHabitsAll(): LiveData<List<ItemHabit>> = habitsDao.getHabitsAll()

    override suspend fun createItemHabit(itemHabit: ItemHabit) {
        habitsDao.createItemHabit(itemHabit)
    }

    override suspend fun updateItemHabit(itemHabit: ItemHabit) {
        habitsDao.updateItemHabit(itemHabit)
    }

    override suspend fun deleteItemHabit(itemHabit: ItemHabit) {
        habitsDao.deleteItemHabit(itemHabit)
    }

    override suspend fun getItemHabit(idItem: Int) = habitsDao.getItemHabit(idItem)

    override suspend fun getHabitsId(sortUp: Boolean): List<ItemHabit> =
        if (sortUp)
            habitsDao.getHabitsForSort()
        else
            habitsDao.getHabitsForSort().asReversed()


    override suspend fun getHabitsPriority(sortUp:Boolean): List<ItemHabit>{
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

    override suspend fun getSearchedHabits(content: CharSequence):ArrayList<ItemHabit>{
        val searchHabits = arrayListOf<ItemHabit>()
        habitsDao.getHabitsForSort().forEach{
            if (it.name.contains(content))
                searchHabits.add(it)
        }
        return searchHabits
    }


}