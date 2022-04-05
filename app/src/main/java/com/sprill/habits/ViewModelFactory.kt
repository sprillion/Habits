package com.sprill.habits

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sprill.habits.model.Http
import com.sprill.habits.viewModels.CreateEditViewModel
import com.sprill.habits.viewModels.HabitListViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            HabitListViewModel::class.java ->{
                HabitListViewModel.getHabitListViewModel(Http.habitsRepository)
            }
            CreateEditViewModel::class.java -> {
                CreateEditViewModel(Http.habitsRepository)
            }
            else -> IllegalStateException("Unknown view model")
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory()