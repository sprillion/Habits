package com.sprill.habits.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.models.Model

class HabitListViewModel(private val model: Model) : ViewModel() {

    private val mutableHabits: MutableLiveData<ArrayList<ItemHabit>> by lazy {
        MutableLiveData()
    }

    var habits: LiveData<ArrayList<ItemHabit>> = mutableHabits

    init {
        load()
    }

    fun load(){
        mutableHabits.value = model.getHabitsAll()
    }

    fun sortPriorityHabits(sortUp: Boolean) {
        mutableHabits.value = model.getSortedPriorityHabits(sortUp)
    }

    fun searchHabits(content: CharSequence?){
        content?.let { mutableHabits.value = model.search(it) }
    }

    fun sortIdHabits(sortUp: Boolean){
        if (sortUp) load()
        else mutableHabits.value = model.getReversedHabits()
    }

}