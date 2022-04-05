package com.sprill.habits.viewModels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprill.habits.model.retrofit.ItemHabit
import com.sprill.habits.model.HabitsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateEditViewModel(private val habitsRepository: HabitsRepository): ViewModel() {

    private val mutableItemHabit: MutableLiveData<ItemHabit?> = MutableLiveData()

    var itemHabit: LiveData<ItemHabit?> = mutableItemHabit

    fun setCurrentItem(idItem: String){
        viewModelScope.launch {

            val response = habitsRepository.getItemHabits(idItem)

            withContext(Dispatchers.Main) {
                response.body()?.let {
                    mutableItemHabit.postValue(response.body())
                }
            }
        }
    }

    fun sendItemHabit(itemHabit: ItemHabit){
        viewModelScope.launch  {
            habitsRepository.sendItemHabit(itemHabit)
//            val response = habitsRepository.sendItemHabit(itemHabit)
//
//            withContext(Dispatchers.Main){
//
//            }
        }
    }
}


//
//    fun deleteItem(){
//        viewModelScope.launch  {
//            withContext(Dispatchers.IO){
//                itemHabit.value?.let { habitsRepository.deleteItemHabit(it) }
//            }
//        }
//    }
