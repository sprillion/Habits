package com.sprill.habits.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.model.HabitsRepository
import kotlinx.coroutines.*

class CreateEditViewModel(private val habitsRepository: HabitsRepository): ViewModel() {

    private val mutableItemHabit: MutableLiveData<ItemHabit?> = MutableLiveData()

    var itemHabit: LiveData<ItemHabit?> = mutableItemHabit

    fun setCurrentItem(idItem: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = withContext(Dispatchers.IO) {
                    habitsRepository.getItemHabit(idItem)
                }
                mutableItemHabit.postValue(result)
            }
        }
    }

    fun sendItemHabit(itemHabit: ItemHabit){
        viewModelScope.launch  {
            withContext(Dispatchers.IO){
                habitsRepository.sendItemHabit(itemHabit)
            }
        }
    }

    fun deleteItem(){
        viewModelScope.launch  {
            withContext(Dispatchers.IO){
                itemHabit.value?.let { habitsRepository.deleteItemHabit(it) }
            }
        }
    }
}