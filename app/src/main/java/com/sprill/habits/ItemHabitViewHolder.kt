package com.sprill.habits

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.ItemHabitBinding

class ItemHabitViewHolder(val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root){

    @SuppressLint("SetTextI18n")
    fun bind(habit: ItemHabit, context: Context){
        binding.name.text = habit.name
        binding.description.text = habit.description
        binding.priority.text = context.getString(R.string.priority_word) + context.resources.getStringArray(R.array.priorities)[habit.priority]
        binding.period.text = "${context.getString(R.string.period_word)}${habit.countExecution} ${habit.period}"
        binding.item.setCardBackgroundColor(habit.color)
    }

}