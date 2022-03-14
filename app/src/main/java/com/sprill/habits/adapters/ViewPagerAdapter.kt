package com.sprill.habits.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.fragments.HabitsListFragment

const val countFragments = 2
class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val habits: ArrayList<ItemHabit>) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = countFragments

    override fun createFragment(position: Int): Fragment =
            HabitsListFragment.newInstance(getTypesHabits(position))

    private fun getTypesHabits(type: Int): ArrayList<ItemHabit>{
        val typesHabits = arrayListOf<ItemHabit>()
        habits.forEach{
            if (it.type == type) typesHabits.add(it)
        }
        return typesHabits
    }
}