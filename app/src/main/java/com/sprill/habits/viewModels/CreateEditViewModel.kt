package com.sprill.habits.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprill.habits.model.retrofit.IRetrofitHabitsRepository
import com.sprill.habits.model.retrofit.models.HabitDoneTuple
import com.sprill.habits.model.retrofit.models.ItemHabit
import com.sprill.habits.model.retrofit.models.UidTuple
import com.sprill.habits.model.room.entities.ItemHabitEntity
import com.sprill.habits.model.room.IRoomHabitsRepository
import com.sprill.habits.model.room.converters.ListDateConverter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.util.*

class CreateEditViewModel(
    private val localRepository: IRoomHabitsRepository,
    private val httpRepository: IRetrofitHabitsRepository
    ): ViewModel() {

    private val mutableItemHabitEntity: MutableLiveData<ItemHabitEntity?> = MutableLiveData()
    var itemHabit: LiveData<ItemHabitEntity?> = mutableItemHabitEntity


    private val mutableProgress: MutableLiveData<Int> = MutableLiveData()
    var progress: LiveData<Int> = mutableProgress

    fun setCurrentItem(idItem: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newItem = localRepository.getItemHabit(idItem)
                mutableItemHabitEntity.postValue(newItem)
            }
        }
    }

    fun sendItemHabit(itemHabitEntity: ItemHabitEntity){
        viewModelScope.launch  {
            mutableProgress.postValue(START_PROGRESS)
            val result = httpRepository.sendItemHabit(ItemHabit.converter(itemHabitEntity))
            withContext(Dispatchers.Main){
                if (result.isSuccessful){
                    withContext(Dispatchers.IO){
                        localRepository.sendItemHabit(ItemHabitEntity.newItem(itemHabitEntity, result.body()!!.uid))
                    }
                }
                else{
                    Log.d("ttt", "Error")
                }
            }
            mutableProgress.postValue(END_PROGRESS)
        }
    }

    fun deleteItem(){
        viewModelScope.launch  {
            mutableProgress.postValue(START_PROGRESS)
            val item = itemHabit.value!!
            val result = httpRepository.deleteItemHabit(UidTuple(item.uid))
            withContext(Dispatchers.Main){
                if (result.isSuccessful){
                    withContext(Dispatchers.IO){
                        itemHabit.value?.let { localRepository.deleteItemHabit(it) }
                    }
                }
                else{
                    Log.d("ttt", "Error")
                }
            }
            mutableProgress.postValue(END_PROGRESS)
        }
    }

    fun doneHabit(){
        if (itemHabit.value == null)
            return
        viewModelScope.launch {
            mutableProgress.postValue(START_PROGRESS)
            val habitDoneTuple = HabitDoneTuple(
                Date().time,
                itemHabit.value!!.uid
            )
            val result = httpRepository.habitDone(habitDoneTuple)

            withContext(Dispatchers.Main){
                if (result.isSuccessful){
                    val converter = ListDateConverter()
                    val dates = itemHabit.value!!.doneDates as MutableList
                    dates.add(habitDoneTuple.date)

                    localRepository.setDoneDate(converter.toString(dates), habitDoneTuple.uid)
                }
                else
                    Log.d("ttt", "Error")
            }
            mutableProgress.postValue(END_PROGRESS)
        }
    }

    companion object{
        const val START_PROGRESS = 0
        const val END_PROGRESS = 100
    }

}