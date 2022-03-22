package com.sprill.habits.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.models.Model

class CreateEditViewModel(private val model:Model): ViewModel() {

    private val mutableItemHabit: MutableLiveData<ItemHabit?> = MutableLiveData()
    private val mutableIdItem: MutableLiveData<Int> = MutableLiveData()
    private var isNewItem = false

    var idItem: LiveData<Int> = mutableIdItem
    var itemHabit: LiveData<ItemHabit?> = mutableItemHabit

    private fun load(){
         mutableItemHabit.value = mutableIdItem.value?.let { model.getItemHabit(it) }
    }

    fun setNewId(){
        mutableIdItem.value = model.getNewId()
        isNewItem = true
    }

    fun setCurrentItem(idItem: Int){
        mutableIdItem.value  = idItem
        load()
    }

    fun setItemHabit(itemHabit: ItemHabit){
        if (isNewItem)
            model.addHabit(itemHabit)

        mutableIdItem.value?.let { model.changeHabit(itemHabit, it) }
        load()
    }
}