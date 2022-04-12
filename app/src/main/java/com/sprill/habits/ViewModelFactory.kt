package com.sprill.habits

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sprill.habits.model.retrofit.HttpRepository
import com.sprill.habits.model.room.Repository
import com.sprill.habits.viewModels.CreateEditViewModel
import com.sprill.habits.viewModels.HabitListViewModel

@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            HabitListViewModel::class.java ->{
                HabitListViewModel.getHabitListViewModel(
                    Repository.habitsRepository,
                    HttpRepository.habitsRepository
                )
            }
            CreateEditViewModel::class.java -> {
                CreateEditViewModel(
                    Repository.habitsRepository,
                    HttpRepository.habitsRepository
                )
            }
            else -> IllegalStateException("Unknown view model")
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory()