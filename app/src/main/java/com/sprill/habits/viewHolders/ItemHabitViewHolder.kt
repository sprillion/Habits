package com.sprill.habits.viewHolders

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.R
import com.sprill.habits.model.room.entities.ItemHabitEntity
import com.sprill.habits.databinding.ItemHabitBinding
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

class ItemHabitViewHolder(private val binding: ItemHabitBinding) : RecyclerView.ViewHolder(binding.root){

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(habitEntity: ItemHabitEntity, context: Context){
        binding.apply {
            name.text = habitEntity.name
            description.text = habitEntity.description
            priority.text = context.getString(R.string.priority_word) +
                    context.resources.getStringArray(R.array.priorities)[habitEntity.priority]
            period.text =
                context.getString(R.string.period_word) +
                "${habitEntity.countExecution} ${habitEntity.period}"
            item.setCardBackgroundColor(habitEntity.color)
            lastTime(
                habitEntity.doneDates,
                lastDone,
                context
            )
        }

    }

    @SuppressLint("SetTextI18n")
    private fun lastTime(doneDates: List<Long>?, lastDone: TextView, context: Context){
        if (doneDates == null || doneDates.isEmpty())
            lastDone.text = context.getString(R.string.done_in_progress)
        else {
            val dateFormat = SimpleDateFormat(PATTERN, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())
            lastDone.text =
                context.getString(R.string.done_word) +
                        dateFormat.format(Date(doneDates.last()))
        }
    }

    companion object{
        private const val PATTERN = "dd-MM-yyyy    HH:mm:ss"
    }

}

