package com.sprill.habits

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.sprill.habits.databinding.FragmentCreateEditBinding

class CreateEditFragment : Fragment() {

    companion object {
        fun newInstance() = CreateEditFragment()

        fun newInstance(habit: ItemHabit?, idItem: Int) =
            CreateEditFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MainActivity.BUNDLE_KEY_HABIT, habit)
                    putInt(MainActivity.BUNDLE_KEY_ID, idItem)
                }
            }
    }

    private lateinit var binding: FragmentCreateEditBinding
    private var itemHabit: ItemHabit? = null
    private var idIem = -1
    private var callback: CallBack? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as CallBack
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemHabit = it.getSerializable(MainActivity.BUNDLE_KEY_HABIT) as ItemHabit
            idIem = it.getInt(MainActivity.BUNDLE_KEY_ID, -1)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateEditBinding.inflate(inflater)
        binding.buttonSave.setOnClickListener{
            callback?.onFragmentViewCreated(save(), idIem, this)
        }
        fill()

        return binding.root
    }

    private fun fill(){
        if (itemHabit == null) {
            (activity as MainActivity).title = getString(R.string.create_activity)
            return
        }
        (activity as MainActivity).title = getString(R.string.edit_activity)
        binding.textInputName.setText(itemHabit!!.name)
        binding.textInputDescription.setText(itemHabit!!.description)
        binding.spinnerPriority.setSelection(itemHabit!!.priority)
        if (itemHabit!!.type != -1){
            val radioButton = binding.radioGroupType.findViewById(itemHabit!!.type) as RadioButton
            radioButton.isChecked = true
        }
        binding.textInputCountExecution.setText(itemHabit!!.countExecution)
        binding.textInputPeriod.setText(itemHabit!!.period)
        binding.colorPicker.setColor(itemHabit!!.color)
    }

    private fun save() : ItemHabit {
        return ItemHabit(
            binding.textInputName.text.toString(),
            binding.textInputDescription.text.toString(),
            binding.spinnerPriority.selectedItemPosition,
            binding.radioGroupType.checkedRadioButtonId,
            binding.textInputCountExecution.text.toString(),
            binding.textInputPeriod.text.toString(),
            binding.colorPicker.getColor()
        )
    }
}

interface CallBack{
    fun onFragmentViewCreated(itemHabit: ItemHabit, idItem: Int, fragment: CreateEditFragment)
}