package com.sprill.habits.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.fragments.HabitsListFragment
import java.util.ArrayList

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val habits: List<ItemHabit>) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = COUNT_FRAGMENTS

    override fun createFragment(position: Int): Fragment =
        HabitsListFragment.newInstance(position, ArrayList(habits))

    companion object{
        private const val COUNT_FRAGMENTS = 2
    }
}