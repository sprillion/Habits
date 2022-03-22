package com.sprill.habits.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.ItemHabitViewHolder
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.ItemHabitBinding
import com.sprill.habits.interfaces.IAdapterCallBack

class HabitsListAdapter(
    private val habits: ArrayList<ItemHabit>,
    private val typeHabits: Int,
    private val callBack: IAdapterCallBack,
    private val context: Context?,
    ) : RecyclerView.Adapter<ItemHabitViewHolder>() {

    private val typesHabits = getTypesHabits()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHabitViewHolder(ItemHabitBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = typesHabits.size

    override fun onBindViewHolder(holder: ItemHabitViewHolder, position: Int) {
        val itemHabit = typesHabits[position]
        holder.bind(itemHabit, context!!)
        holder.itemView.setOnClickListener{
            callBack.onItemClicked(itemHabit.id)
        }
    }

    private fun getTypesHabits(): ArrayList<ItemHabit>{
        val typesHabits = arrayListOf<ItemHabit>()
        habits.forEach{
            if (it.type == typeHabits) typesHabits.add(it)
        }
        return typesHabits
    }
}