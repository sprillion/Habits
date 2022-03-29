package com.sprill.habits.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.model.HabitsRepository


class HabitListViewModel(private val habitsRepository: HabitsRepository) : ViewModel() {

    private val mutableSortedHabits: MutableLiveData<List<ItemHabit>> = MutableLiveData()
    val sortedHabits: LiveData<List<ItemHabit>> = mutableSortedHabits

    var habits: LiveData<List<ItemHabit>> = habitsRepository.getHabitsAll()

    fun setSortPriority(sortUp: Boolean){
        mutableSortedHabits.value = habitsRepository.getHabitsPriority(sortUp)
    }

    fun setSortId(sortUp: Boolean){
        mutableSortedHabits.value = habitsRepository.getHabitsId(sortUp)
    }

    fun setSearcher(content: CharSequence?){
        content?.let {
            mutableSortedHabits.value = habitsRepository.getSearchedHabits(it)
        }
    }

}