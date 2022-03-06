package com.sprill.habits

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.databinding.ItemHabitBinding

class ViewHolder(val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root){

    @SuppressLint("SetTextI18n")
    fun bind(habit: ItemHabit, context: Context){
        binding.name.text = habit.name
        binding.description.text = habit.description
        binding.priority.text =  "Приоритет: ${context.resources.getStringArray(R.array.priorities)[habit.priority]}"
        binding.type.text = "Тип: ${when(habit.type){
            R.id.radioButtonFirst -> context.getString(R.string.radioButtonFirst)
            R.id.radioButtonSecond ->context.getString(R.string.radioButtonSecond)
            R.id.radioButtonThird ->context.getString(R.string.radioButtonThird)
            else -> "Не выбран"
        }}"
        binding.period.text = "Периодичность: ${habit.countExecution} ${habit.period}"
        binding.item.setCardBackgroundColor(habit.color)
    }

}