package com.sprill.habits.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.model.HabitsRepository
import kotlinx.coroutines.*

class CreateEditViewModel(private val habitsRepository: HabitsRepository): ViewModel() {

    private var isNewItem = false
    private val mutableItemHabit: MutableLiveData<ItemHabit?> = MutableLiveData()

    var itemHabit: LiveData<ItemHabit?> = mutableItemHabit

    fun setNew(){
        isNewItem = true
    }

    fun setCurrentItem(idItem: Int){
        viewModelScope.launch(Dispatchers.Main) {
           val result =  withContext(Dispatchers.IO){
                habitsRepository.getItemHabit(idItem)
           }
           mutableItemHabit.value = result
        }
    }

    fun setItemHabit(itemHabit: ItemHabit){
        viewModelScope.launch(Dispatchers.Main)  {
            withContext(Dispatchers.IO){
                if (isNewItem)
                    habitsRepository.createItemHabit(itemHabit)
                else
                    habitsRepository.updateItemHabit(itemHabit)
            }
        }
    }

    fun deleteItem(){
        viewModelScope.launch(Dispatchers.Main)  {
            withContext(Dispatchers.IO){
                itemHabit.value?.let { habitsRepository.deleteItemHabit(it) }
            }
        }
    }
}