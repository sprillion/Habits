package com.sprill.habits.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sprill.habits.data.HabitResult
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.databinding.FragmentCreateEditBinding
import com.sprill.habits.interfaces.*

class CreateEditFragment : Fragment(), HasCustomTitle {

    companion object {
        fun newInstance(habit: ItemHabit?, idItem: Int) =
            CreateEditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MainActivity.BUNDLE_KEY_HABIT, habit)
                    putInt(MainActivity.BUNDLE_KEY_ID, idItem)
                }
            }
    }

    private lateinit var binding: FragmentCreateEditBinding
    private var itemHabit: ItemHabit? = null
    private var idItem: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemHabit = it.getParcelable(MainActivity.BUNDLE_KEY_HABIT)
            idItem = it.getInt(MainActivity.BUNDLE_KEY_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditBinding.inflate(inflater)
        binding.buttonSave.setOnClickListener{
            navigator().publishResult(HabitResult(getNewItem(), idItem))
            navigator().goBack()
        }
        fillData()

        return binding.root
    }

    private fun fillData(){
        if (itemHabit == null) return
        binding.textInputName.setText(itemHabit!!.name)
        binding.textInputDescription.setText(itemHabit!!.description)
        binding.spinnerPriority.setSelection(itemHabit!!.priority)
        if (itemHabit!!.type == MainActivity.KEY_TYPE_GOOD)
            binding.radioButtonFirst.isChecked = true
        else binding.radioButtonSecond.isChecked = true
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

    override fun getTitleRes(): Int = if (idItem == null) R.string.create_screen_title else R.string.edit_screen_title
}
