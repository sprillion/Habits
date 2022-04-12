package com.sprill.habits.model.room

import android.util.Log
import androidx.lifecycle.LiveData
import com.sprill.habits.model.room.entities.ItemHabitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class RoomHabitsRepository(
    private val habitsDao: HabitsDao
): IRoomHabitsRepository {

    override fun getHabitsAll(): Flow<List<ItemHabitEntity>> = habitsDao.getHabitsAll()

    override suspend fun sendItemHabit(itemHabitEntity: ItemHabitEntity) {
            habitsDao.sendItemHabit(itemHabitEntity)
    }

    override suspend fun fillHabits(habitEntities: List<ItemHabitEntity>) {
        habitsDao.fillHabits(habitEntities)
    }

    override suspend fun deleteItemHabit(itemHabitEntity: ItemHabitEntity) {
        habitsDao.deleteItemHabit(itemHabitEntity)
    }

    override suspend fun getItemHabit(idItem: String) = habitsDao.getItemHabit(idItem)

//    override suspend fun getHabitsDate(sortUp: Boolean): List<ItemHabitEntity> =
//        if (sortUp)
//            habitsDao.getHabitsForSort()
//        else
//            habitsDao.getHabitsForSort().asReversed()

    override suspend fun getSortedHabits(
        typeSort: TypeSort,
        sortUp: Boolean
    ): List<ItemHabitEntity> {
        var sortedHabits: MutableList<ItemHabitEntity> = mutableListOf()
        getHabitsAll().collect{
            sortedHabits = it as MutableList<ItemHabitEntity>
        }
        sortedHabits.apply {
            if (sortUp)
                sortByDescending { item->
                    when(typeSort){
                        TypeSort.DATE -> return@sortByDescending item.date
                        TypeSort.PRIORITY -> return@sortByDescending item.priority.toLong()
                    }
                }
            else
                sortBy{ item->
                    when(typeSort){
                        TypeSort.DATE -> return@sortBy item.date
                        TypeSort.PRIORITY -> return@sortBy item.priority.toLong()
                    }
                }
        }
        return sortedHabits
    }

    override suspend fun getSearchedHabits(content: CharSequence):List<ItemHabitEntity>{
        val searchHabits:MutableList<ItemHabitEntity> = mutableListOf()
        getHabitsAll().collect{ habits ->
            habits.forEach{ item ->
                if (item.name.contains(content))
                    searchHabits.add(item)
            }
        }
        return searchHabits
    }

    override suspend fun setDoneDate(doneDates: String, uid: String) {
        habitsDao.setDoneDate(doneDates, uid)
    }

    companion object{
        private const val NO_ITEM = 0
    }

}