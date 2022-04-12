package com.sprill.habits.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.viewHolders.ItemHabitViewHolder
import com.sprill.habits.model.room.entities.ItemHabitEntity
import com.sprill.habits.databinding.ItemHabitBinding
import com.sprill.habits.interfaces.IAdapterCallBack

class HabitsListAdapter(
    private val habits: List<ItemHabitEntity>,
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
        itemHabit.doneDates?.let { doneDates->
            if (doneDates.isEmpty())
            {
                //holder.itemView.layoutParams.height -= holder.itemView
            }
        }


        val params = holder.itemView.layoutParams
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        holder.itemView.layoutParams = params
        holder.itemView.setOnClickListener{
            itemHabit.uid?.let { callBack.onItemClicked(it) }
        }
    }

    private fun getTypesHabits(): List<ItemHabitEntity> = habits.filter { it.type == typeHabits }

}