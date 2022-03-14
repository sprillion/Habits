package com.sprill.habits.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.adapters.ViewPagerAdapter
import com.sprill.habits.data.HabitResult
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.FragmentTypesViewPagerBinding
import com.sprill.habits.interfaces.navigator

class TypesViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentTypesViewPagerBinding
    private var habits: ArrayList<ItemHabit> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTypesViewPagerBinding.inflate(layoutInflater)

        binding.fab.setOnClickListener{
            onFubPressed()
        }

        navigator().listenResult(HabitResult::class.java, viewLifecycleOwner){
            if (it.itemHabit == null) return@listenResult
            if (it.idItem == null) habits.add(it.itemHabit)
            else habits[it.idItem] = it.itemHabit
            setAdapter()
        }
        setAdapter()
        setTabLayout()
        return binding.root
    }

    private fun onFubPressed(){
        navigator().showCreateScreen()
    }

    companion object {
        fun newInstance() = TypesViewPagerFragment()
    }

    private fun setAdapter(){
        binding.viewPager.adapter = activity?.let { ViewPagerAdapter(it, habits) }
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, pos ->
            when(pos){
                MainActivity.KEY_TYPE_GOOD ->{
                    tab.text = getString(R.string.good_type)
                }
                MainActivity.KEY_TYPE_BAD ->{
                    tab.text = getString(R.string.bad_type)
                }
            }
        }.attach()
    }

}