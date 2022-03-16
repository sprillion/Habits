package com.sprill.habits.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.adapters.ViewPagerAdapter
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.FragmentTypesViewPagerBinding

class TypesViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentTypesViewPagerBinding
    private var habits: ArrayList<ItemHabit> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTypesViewPagerBinding.inflate(layoutInflater)

        binding.fab.setOnClickListener{
            onFubPressed()
        }

        listenerResult()
        setAdapter()
        setTabLayout()

        return binding.root
    }

    private fun listenerResult()
    {
        parentFragmentManager.setFragmentResultListener(MainActivity.BUNDLE_KEY_HABIT_RESULT, viewLifecycleOwner){
                _, data ->
            val itemHabit : ItemHabit? = data.getParcelable(MainActivity.BUNDLE_KEY_HABIT)
            val id: Int = data.getInt(MainActivity.BUNDLE_KEY_ID)

            if (itemHabit == null) return@setFragmentResultListener
            if (id == MainActivity.BUNDLE_KEY_ID_NULL)
                habits.add(itemHabit)
            else
                habits[id] = itemHabit
            setAdapter()
        }
    }

    private fun onFubPressed(){
        findNavController().navigate(
            R.id.action_typesViewPagerFragment_to_createEditFragment,
            bundleOf(
                MainActivity.BUNDLE_KEY_HABIT to null,
                MainActivity.BUNDLE_KEY_ID to MainActivity.BUNDLE_KEY_ID_NULL,
                MainActivity.BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME to getString(R.string.create_screen_title)
            )
        )
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