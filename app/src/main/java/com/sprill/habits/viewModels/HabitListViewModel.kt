package com.sprill.habits.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprill.habits.model.retrofit.IRetrofitHabitsRepository
import com.sprill.habits.model.retrofit.models.ItemHabit
import com.sprill.habits.model.room.IRoomHabitsRepository
import com.sprill.habits.model.room.TypeSort
import com.sprill.habits.model.room.entities.ItemHabitEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HabitListViewModel(
    private val localRepository: IRoomHabitsRepository,
    private val httpRepository: IRetrofitHabitsRepository
    ) : ViewModel() {

    private val mutableSortedHabits: MutableLiveData<List<ItemHabitEntity>> = MutableLiveData()
    val sortedHabits: LiveData<List<ItemHabitEntity>> = mutableSortedHabits

    //private var mutableHabits: MutableStateFlow<List<ItemHabitEntity>> = MutableStateFlow(listOf())

    val habits: Flow<List<ItemHabitEntity>> = localRepository.getHabitsAll()

    //var habits: LiveData<List<ItemHabitEntity>> = getHabitsAll()

    init {
        getHabitsAll()
    }


   // private fun getHabitsAll(): Flow<List<ItemHabitEntity>>{
    private fun getHabitsAll(){
        viewModelScope.launch {
            val result =  httpRepository.getHabitsAll().body()
            withContext(Dispatchers.Main){
                val habitsEntities = result?.map {
                        itemHabit -> ItemHabitEntity.converter(itemHabit)
                }
                withContext(Dispatchers.IO){
                    habitsEntities?.let {
                        localRepository.fillHabits(it)
                        localRepository.getHabitsAll().collect()
                    }
                }
            }
        }

    }

    fun setSortedHabits(typeSort: TypeSort, sortUp: Boolean){
        viewModelScope.launch {
            Log.d("ttt", "hmm")
            val result = withContext(Dispatchers.IO) {
                localRepository.getSortedHabits(typeSort, sortUp)
            }
            mutableSortedHabits.postValue(result)
        }
    }
//
//    fun setSortPriority(sortUp: Boolean){
//        viewModelScope.launch {
//            val result =  withContext(Dispatchers.IO){
//                localRepository.getHabitsPriority(sortUp)
//            }
//            mutableSortedHabits.postValue(result)
//        }
//
//    }
//
//    fun setSortId(sortUp: Boolean){
//        viewModelScope.launch {
//            val result = withContext(Dispatchers.IO) {
//                localRepository.getSortedHabits(TypeSort.DATE, sortUp)
//            }
//            mutableSortedHabits.postValue(result)
//        }
//    }

    fun setSearcher(content: CharSequence?){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                content?.let {
                    localRepository.getSearchedHabits(it)
                }
            }
            mutableSortedHabits.postValue(result)
        }
    }

    companion object{
        private var viewModel: HabitListViewModel? = null

        fun getHabitListViewModel(
            localRepository: IRoomHabitsRepository,
            httpRepository: IRetrofitHabitsRepository
        ): HabitListViewModel{
            if (viewModel == null)
                viewModel = HabitListViewModel(localRepository, httpRepository)
            return viewModel!!
        }
    }
}