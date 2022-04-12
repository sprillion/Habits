package com.sprill.habits.fragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sprill.habits.model.room.entities.ItemHabitEntity
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.databinding.FragmentCreateEditBinding
import com.sprill.habits.factory
import com.sprill.habits.viewModels.CreateEditViewModel
import java.util.*

class CreateEditFragment : Fragment() {

    private lateinit var binding: FragmentCreateEditBinding
    private val viewModel: CreateEditViewModel by viewModels { factory() }

    private var idItem: String = MainActivity.BUNDLE_KEY_ID_NULL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idItem = requireArguments().getString(MainActivity.BUNDLE_KEY_ID) ?: MainActivity.BUNDLE_KEY_ID_NULL

        if (idItem != MainActivity.BUNDLE_KEY_ID_NULL)
            viewModel.setCurrentItem(idItem)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateEditBinding.inflate(inflater)
        binding.buttonSave.setOnClickListener{
            sendResult()
            goBack()
        }
        showButtons()

        viewModel.itemHabit.observe(viewLifecycleOwner, Observer {
                itemHabit ->
            fillData(itemHabit)
        })

        return binding.root
    }

    private fun sendResult(){
        viewModel.sendItemHabit(getNewItem())
    }

    private fun fillData(itemHabitEntity: ItemHabitEntity?){
        itemHabitEntity?.let {
            binding.apply {
                textInputName.setText(it.name)
                textInputDescription.setText(it.description)
                spinnerPriority.setSelection(it.priority)
                radioButtonFirst.isChecked = it.type == MainActivity.KEY_TYPE_GOOD
                radioButtonSecond.isChecked = it.type == MainActivity.KEY_TYPE_BAD
                textInputCountExecution.setText(it.countExecution.toString())
                textInputPeriod.setText(it.period.toString())
                colorPicker.setColor(it.color)
            }
        }
    }

    private fun getNewItem() : ItemHabitEntity {
        return ItemHabitEntity(
            uid = idItem,
            name = getString(binding.textInputName.text),
            description = getString(binding.textInputDescription.text),
            priority = binding.spinnerPriority.selectedItemPosition,
            type = if (binding.radioGroupType.checkedRadioButtonId == R.id.radioButtonFirst) MainActivity.KEY_TYPE_GOOD else MainActivity.KEY_TYPE_BAD,
            countExecution = getInt(binding.textInputCountExecution.text),
            period = getInt(binding.textInputPeriod.text),
            color = binding.colorPicker.getColor(),
            date = Date().time,
            doneDates = listOf()
        )
    }

    private fun showButtons(){
        if (idItem == MainActivity.BUNDLE_KEY_ID_NULL) {
            binding.apply {
                buttonDelete.isVisible = false
                buttonDone.isVisible = false
            }
        }
        else {
            binding.apply {
                buttonDelete.setOnClickListener {
                    viewModel.deleteItem()
                    goBack()
                }
                buttonDone.setOnClickListener {
                    viewModel.doneHabit()
                    goBack()
                }
            }


        }
    }

    private fun goBack(){
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            if (it == CreateEditViewModel.END_PROGRESS)
                findNavController().navigateUp()
        })
    }


    private fun getInt(editable: Editable?): Int{
        if (editable== null || editable.isEmpty())
            return 0
        return editable.toString().toInt()
    }

    private fun getString(editable: Editable?): String{
        if (editable== null || editable.isEmpty())
            return "-"
        return editable.toString()
    }
}