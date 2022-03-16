package com.sprill.habits.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sprill.habits.adapters.HabitsListAdapter
import com.sprill.habits.MainActivity
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.FragmentHabitsListBinding

class HabitsListFragment : Fragment() {

    private lateinit var binding: FragmentHabitsListBinding
    private var habits: ArrayList<ItemHabit> = arrayListOf()
    private var typeHabits: Int = MainActivity.KEY_TYPE_GOOD

    companion object {
        fun newInstance(habits: ArrayList<ItemHabit>, typeHabits: Int) = HabitsListFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(MainActivity.BUNDLE_KEY_HABITS_LIST, habits)
                putInt(MainActivity.BUNDLE_KEY_TYPE, typeHabits)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            habits = it.getParcelableArrayList(MainActivity.BUNDLE_KEY_HABITS_LIST) ?: arrayListOf()
            typeHabits = it.getInt(MainActivity.BUNDLE_KEY_TYPE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHabitsListBinding.inflate(inflater)

        setAdapter()
        return binding.root
    }

    private fun setAdapter(){
        binding.recycler.adapter = HabitsListAdapter(habits,typeHabits, context, findNavController())
    }
}
