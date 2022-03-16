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

    companion object {
        fun newInstance(habits: ArrayList<ItemHabit>) = HabitsListFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(MainActivity.BUNDLE_KEY_HABITS_LIST, habits)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            habits = it.getParcelableArrayList(MainActivity.BUNDLE_KEY_HABITS_LIST) ?: arrayListOf()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHabitsListBinding.inflate(inflater)

        setAdapter()
        return binding.root
    }

    private fun setAdapter(){
        binding.recycler.adapter = HabitsListAdapter(habits, context, findNavController())
    }
}
