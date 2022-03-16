package com.sprill.habits.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.ItemHabitViewHolder
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.ItemHabitBinding
import com.sprill.habits.interfaces.Navigator

class HabitsListAdapter(private val habits: ArrayList<ItemHabit>, private val typeHabits: Int, private val context: Context?, private val navigator: Navigator) : RecyclerView.Adapter<ItemHabitViewHolder>() {

    private val habitsOfType = getTypesHabits()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHabitViewHolder(ItemHabitBinding.inflate(inflater, parent, false))
    }
    override fun getItemCount(): Int = habitsOfType.size

    override fun onBindViewHolder(holder: ItemHabitViewHolder, position: Int) {
        val item = habitsOfType[position]
        holder.bind(item, context!!)
        holder.itemView.setOnClickListener{
            onButtonPressed(habits.indexOf(item))
        }
    }

    private fun getTypesHabits(): ArrayList<ItemHabit>{
        val typesHabits = arrayListOf<ItemHabit>()
        habits.forEach{
            if (it.type == typeHabits) typesHabits.add(it)
        }
        return typesHabits
    }

    private fun onButtonPressed(idItem: Int){
        navigator.showEditScreen(habits[idItem], idItem)
    }
}