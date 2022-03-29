package com.sprill.habits.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.Repository
import com.sprill.habits.databinding.FragmentCreateEditBinding
import com.sprill.habits.viewModels.CreateEditViewModel

class CreateEditFragment : Fragment() {

    private lateinit var binding: FragmentCreateEditBinding
    private lateinit var viewModel: CreateEditViewModel
    private var idItem: Int = MainActivity.BUNDLE_KEY_ID_NULL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idItem = requireArguments().getInt(MainActivity.BUNDLE_KEY_ID)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CreateEditViewModel(Repository.habitsRepository) as T
            }
        }).get(CreateEditViewModel::class.java)

        if (idItem == MainActivity.BUNDLE_KEY_ID_NULL)
            viewModel.setNew()
        else
            viewModel.setCurrentItem(idItem)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditBinding.inflate(inflater)
        binding.buttonSave.setOnClickListener{
            sendResult()
            findNavController().navigateUp()
        }

        viewModel.itemHabit.observe(viewLifecycleOwner, Observer {
                itemHabit ->
            fillData(itemHabit)
        })

        return binding.root
    }

    private fun sendResult(){
        viewModel.setItemHabit(getNewItem())
    }

    private fun fillData(itemHabit: ItemHabit?){
        itemHabit?.let {
            binding.apply {
                textInputName.setText(it.name)
                textInputDescription.setText(it.description)
                spinnerPriority.setSelection(it.priority)
                radioButtonFirst.isChecked = it.type == MainActivity.KEY_TYPE_GOOD
                radioButtonSecond.isChecked = it.type == MainActivity.KEY_TYPE_BAD
                textInputCountExecution.setText(it.countExecution)
                textInputPeriod.setText(it.period)
                colorPicker.setColor(it.color)
            }
        }
    }

    private fun getNewItem() : ItemHabit {
        return ItemHabit(
            if (idItem == MainActivity.BUNDLE_KEY_ID_NULL) 0 else idItem,
            binding.textInputName.text.toString(),
            binding.textInputDescription.text.toString(),
            binding.spinnerPriority.selectedItemPosition,
            if (binding.radioGroupType.checkedRadioButtonId == R.id.radioButtonFirst) MainActivity.KEY_TYPE_GOOD else MainActivity.KEY_TYPE_BAD,
            binding.textInputCountExecution.text.toString(),
            binding.textInputPeriod.text.toString(),
            binding.colorPicker.getColor()
        )
    }
}
