package com.sprill.habits.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.ItemHabitViewHolder
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.ItemHabitBinding
import com.sprill.habits.interfaces.Navigator

class HabitsListAdapter(private val habits: ArrayList<ItemHabit>, private val context: Context?, private val navigator: Navigator) : RecyclerView.Adapter<ItemHabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHabitViewHolder(ItemHabitBinding.inflate(inflater, parent, false))
    }
    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: ItemHabitViewHolder, position: Int) {
        holder.bind(habits[position], context!!)
        holder.itemView.setOnClickListener{
            onButtonPressed(position)
        }
    }

    private fun onButtonPressed(idItem: Int){
        navigator.showEditScreen(habits[idItem], idItem)
    }
}