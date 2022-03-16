package com.sprill.habits.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.databinding.FragmentCreateEditBinding

class CreateEditFragment : Fragment() {

    private lateinit var binding: FragmentCreateEditBinding
    private var itemHabit: ItemHabit? = null
    private var idItem: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditBinding.inflate(inflater)
        itemHabit = requireArguments().getParcelable(MainActivity.BUNDLE_KEY_HABIT)
        idItem = requireArguments().getInt(MainActivity.BUNDLE_KEY_ID)

        binding.buttonSave.setOnClickListener{
            parentFragmentManager.setFragmentResult(
                MainActivity.BUNDLE_KEY_HABIT_RESULT,
                bundleOf(
                    MainActivity.BUNDLE_KEY_HABIT to getNewItem(),
                    MainActivity.BUNDLE_KEY_ID to idItem
                )
            )
            findNavController().popBackStack()
        }


        fillData()
        return binding.root
    }

    private fun fillData(){
        if (itemHabit == null) return
        binding.textInputName.setText(itemHabit!!.name)
        binding.textInputDescription.setText(itemHabit!!.description)
        binding.spinnerPriority.setSelection(itemHabit!!.priority)
        binding.radioButtonFirst.isChecked = itemHabit!!.type == MainActivity.KEY_TYPE_GOOD
        binding.radioButtonSecond.isChecked = itemHabit!!.type == MainActivity.KEY_TYPE_BAD
        binding.textInputCountExecution.setText(itemHabit!!.countExecution)
        binding.textInputPeriod.setText(itemHabit!!.period)
        binding.colorPicker.setColor(itemHabit!!.color)
    }

    private fun getNewItem() : ItemHabit {
        return ItemHabit(
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
