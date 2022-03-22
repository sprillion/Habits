package com.sprill.habits

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sprill.habits.viewModels.CreateEditViewModel
import com.sprill.habits.viewModels.HabitListViewModel

@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
class ViewModelFactory(private val app: App) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            HabitListViewModel::class.java ->{
                HabitListViewModel(app.model)
            }
            CreateEditViewModel::class.java -> {
                CreateEditViewModel(app.model)
            }
            else -> IllegalStateException("Unknown view model")
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)