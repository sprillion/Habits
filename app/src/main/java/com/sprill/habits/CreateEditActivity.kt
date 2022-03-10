package com.sprill.habits

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.sprill.habits.databinding.ActivityCreateEditBinding

class CreateEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEditBinding
    private var isNew = true
    private lateinit var habit: ItemHabit
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        workWithIntent(intent)
    }

    private fun workWithIntent(intent: Intent?)
    {
        if (intent == null) return
        isNew = intent.getBooleanExtra(MainActivity.BUNDLE_KEY_IS_NEW, true)
        if (!isNew) {
            habit = intent.getSerializableExtra(MainActivity.BUNDLE_KEY_HABITS) as ItemHabit
            id = intent.getIntExtra(MainActivity.BUNDLE_KEY_ID, 0)
            fill(habit)
        }
        binding.buttonSave.setOnClickListener(){
            openMainActivity(save())
        }
    }
    private fun fill(itemHabit: ItemHabit){
        title = getString(R.string.edit_activity)
        binding.textInputName.setText(itemHabit.name)
        binding.textInputDescription.setText(itemHabit.description)
        binding.spinnerPriority.setSelection(itemHabit.priority)
        if (itemHabit.type != -1){
            val radioButton = binding.radioGroupType.findViewById(itemHabit.type) as RadioButton
            radioButton.isChecked = true
        }
        binding.textInputCountExecution.setText(itemHabit.countExecution)
        binding.textInputPeriod.setText(itemHabit.period)
        binding.colorPicker.setColor(itemHabit.color)
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

    private fun openMainActivity(habit: ItemHabit){
        val intent = Intent(this, MainActivity::class.java).apply {
           putExtra(MainActivity.BUNDLE_KEY_HABITS, habit)
           if (!isNew) putExtra(MainActivity.BUNDLE_KEY_ID, id)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
