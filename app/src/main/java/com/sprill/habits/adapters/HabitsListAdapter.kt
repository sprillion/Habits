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

class HabitsListAdapter(private val habits: ArrayList<ItemHabit>, private val typeHabits: Int, private val context: Context?, private val navController: NavController) : RecyclerView.Adapter<ItemHabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHabitViewHolder(ItemHabitBinding.inflate(inflater, parent, false))
    }
    override fun getItemCount(): Int = getTypesHabits().size

    override fun onBindViewHolder(holder: ItemHabitViewHolder, position: Int) {
        val item = getTypesHabits()[position]
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
    private fun onButtonPressed(idItem: Int)
    {
        navController.navigate(R.id.action_typesViewPagerFragment_to_createEditFragment,
            bundleOf(
                MainActivity.BUNDLE_KEY_HABIT to habits[idItem],
                MainActivity.BUNDLE_KEY_ID to idItem,
                MainActivity.BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME to context?.getString(R.string.edit_screen_title)
            )
        )
    }
}