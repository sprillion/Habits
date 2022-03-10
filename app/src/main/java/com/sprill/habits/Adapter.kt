package com.sprill.habits

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.sprill.habits.databinding.ItemHabitBinding

class Adapter(private val habits: ArrayList<ItemHabit>, private val context: Context?, private val activity: FragmentActivity?, private val fragment: HabitsListFragment) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemHabitBinding.inflate(inflater, parent, false))
    }
    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position], context!!)
        holder.itemView.setOnClickListener(){
            //openEditActivityForResult(position)
            openEditFragment(position)
        }
    }

    private fun openEditFragment(idItem: Int)
    {
        (activity as MainActivity).supportFragmentManager
            .beginTransaction()
            .add(R.id.main_layout, CreateEditFragment.newInstance(habits[idItem], idItem))
            .commit()
        (activity as MainActivity).supportFragmentManager
            .beginTransaction()
            .hide(fragment)
            .commit()
    }

    /*
    fun openEditActivityForResult(id: Int) {
        val intent = Intent(context, CreateEditActivity::class.java).apply {
            putExtra(MainActivity.BUNDLE_KEY_HABIT, habits[id])
            putExtra(MainActivity.BUNDLE_KEY_ID, id)
            putExtra(MainActivity.BUNDLE_KEY_IS_NEW, false)
        }
        resultLauncher.launch(intent)
    }

     */
}