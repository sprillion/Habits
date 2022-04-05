package com.sprill.habits.model.retrofit

import com.sprill.habits.model.HabitsApi
import com.sprill.habits.model.HabitsRepository
import retrofit2.Response

class RetrofitHabitsRepository(
    private val habitsApi: HabitsApi
): HabitsRepository {


    override suspend fun getHabitsAll() = habitsApi.getHabits()

    override suspend fun getItemHabits(idItem: String) = habitsApi.getItemHabit(idItem)

    override suspend fun sendItemHabit(itemHabit: ItemHabit) = habitsApi.putHabit(itemHabit)
    //var habits: Flow<List<ItemHabit>> = flowOf()

//    override suspend fun getHabitsAll(): Flow<List<ItemHabit>>  {
//
////        return habits.flatMapLatest {
////
////        }
//
//        val get = habitsApi.getHabits()
////
////        get.execute().body()?.let {
////            habits = flowOf(it)
////        }
////        return habits
//
//        get.enqueue(object : Callback<List<ItemHabit>> {
//            override fun onFailure(call: Call<List<ItemHabit>>, t: Throwable) {
//                Log.d("tttError", t.message!!)
//            }
//
//            override fun onResponse(
//                call: Call<List<ItemHabit>>,
//                response: Response<List<ItemHabit>>
//            ) {
//
//                if (response.isSuccessful){
//                    Log.d("ttt4", "hmm")
//                    Log.d("ttt3", response.body().toString())
//                    habits = flowOf(response.body().orEmpty())
//
//                    showHabits()
//                }
//                else{
//                    when(response.code()){
//                        404 ->Log.d("tttError", "Страница не найдена")
//                        500 -> Log.d("tttError", "Ошибка на сервере")
//                        else -> Log.d("tttError", "Какая-то ошибка")
//                    }
//
//                }
//            }
//        })
//
//        //showHabits()
//        return habits
//    }
//
//    private fun showHabits(){
//        GlobalScope.launch {
//            habits.collect() {
//                Log.d("ttt777", it.size.toString())
//            }
//        }
//
//
//    }

//    override fun getHabitsAll(): LiveData<List<ItemHabit>> = habitsDao.getHabitsAll()
//
//    override suspend fun sendItemHabit(itemHabit: ItemHabit) {
//        if (habitsDao.updateItemHabit(itemHabit) == NO_ITEM)
//            habitsDao.createItemHabit(itemHabit)
//    }
//
//    override suspend fun deleteItemHabit(itemHabit: ItemHabit) {
//        habitsDao.deleteItemHabit(itemHabit)
//    }
//
//    override suspend fun getItemHabit(idItem: Int) = habitsDao.getItemHabit(idItem)
//
//    override suspend fun getHabitsId(sortUp: Boolean): List<ItemHabit> =
//        if (sortUp)
//            habitsDao.getHabitsForSort()
//        else
//            habitsDao.getHabitsForSort().asReversed()
//
//
//    override suspend fun getHabitsPriority(sortUp:Boolean): List<ItemHabit>{
//        val sortedHabits = habitsDao.getHabitsForSort() as MutableList
//
//        sortedHabits.apply {
//            if (sortUp)
//                sortByDescending {
//                    it.priority
//                }
//            else
//                sortBy{
//                    it.priority
//                }
//        }
//        return sortedHabits
//    }
//
//    override suspend fun getSearchedHabits(content: CharSequence):ArrayList<ItemHabit>{
//        val searchHabits = arrayListOf<ItemHabit>()
//        habitsDao.getHabitsForSort().forEach{
//            if (it.name.contains(content))
//                searchHabits.add(it)
//        }
//        return searchHabits
//    }

}