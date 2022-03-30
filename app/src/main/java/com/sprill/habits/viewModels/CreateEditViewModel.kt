package com.sprill.habits.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.model.HabitsRepository

class CreateEditViewModel(private val habitsRepository: HabitsRepository): ViewModel() {

    private var isNewItem = false
    private val mutableItemHabit: MutableLiveData<ItemHabit?> = MutableLiveData()

    var itemHabit: LiveData<ItemHabit?> = mutableItemHabit

    fun setNew(){
        isNewItem = true
    }

    fun setCurrentItem(idItem: Int){
        mutableItemHabit.value = habitsRepository.getItemHabit(idItem)
    }

    fun setItemHabit(itemHabit: ItemHabit){
        if (isNewItem)
            habitsRepository.createItemHabit(itemHabit)
        else habitsRepository.updateItemHabit(itemHabit)
    }

    fun deleteItem(){
        itemHabit.value?.let { habitsRepository.deleteItemHabit(it) }
    }
}