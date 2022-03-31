package com.sprill.habits.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.model.HabitsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HabitListViewModel(private val habitsRepository: HabitsRepository) : ViewModel() {

    private val mutableSortedHabits: MutableLiveData<List<ItemHabit>> = MutableLiveData()
    val sortedHabits: LiveData<List<ItemHabit>> = mutableSortedHabits

    var habits: LiveData<List<ItemHabit>> = habitsRepository.getHabitsAll()


    fun setSortPriority(sortUp: Boolean){
        viewModelScope.launch(Dispatchers.Main)  {
            val result =  withContext(Dispatchers.IO){
                habitsRepository.getHabitsPriority(sortUp)
            }
            mutableSortedHabits.value = result
        }

    }

    fun setSortId(sortUp: Boolean){
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                habitsRepository.getHabitsId(sortUp)
            }
            mutableSortedHabits.value = result
        }
    }

    fun setSearcher(content: CharSequence?){
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                content?.let {
                    habitsRepository.getSearchedHabits(it)
                }
            }
            mutableSortedHabits.value = result
        }
    }

}