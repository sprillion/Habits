package com.sprill.habits.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sprill.habits.fragments.HabitsListFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = COUNT_FRAGMENTS

    override fun createFragment(position: Int): Fragment =
        HabitsListFragment.newInstance(position)

    companion object{
        private const val COUNT_FRAGMENTS = 2
    }
}