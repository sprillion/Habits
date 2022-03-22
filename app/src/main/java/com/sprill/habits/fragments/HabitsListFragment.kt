package com.sprill.habits.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sprill.habits.adapters.HabitsListAdapter
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.FragmentHabitsListBinding
import com.sprill.habits.interfaces.IAdapterCallBack

class HabitsListFragment : Fragment(), IAdapterCallBack {

    private lateinit var binding: FragmentHabitsListBinding
    private var typeHabits: Int = MainActivity.KEY_TYPE_GOOD

    companion object {
        fun newInstance(typeHabits: Int, habits: ArrayList<ItemHabit>) = HabitsListFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(MainActivity.BUNDLE_KEY_HABITS, habits)
                putInt(MainActivity.BUNDLE_KEY_TYPE, typeHabits)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHabitsListBinding.inflate(inflater)

        arguments?.let { bundle ->
            typeHabits = bundle.getInt(MainActivity.BUNDLE_KEY_TYPE)
            val habits: ArrayList<ItemHabit>? = bundle.getParcelableArrayList(MainActivity.BUNDLE_KEY_HABITS)
            habits?.let { setAdapter(it) }
        }
        return binding.root
    }

    private fun setAdapter(habits: ArrayList<ItemHabit>){
        binding.recycler.adapter = HabitsListAdapter(habits, typeHabits, this, context)
    }

    override fun onItemClicked(idItem: Int) {
        findNavController().navigate(
            R.id.action_typesViewPagerFragment_to_createEditFragment,
            bundleOf(
                MainActivity.BUNDLE_KEY_ID to idItem,
                MainActivity.BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME to context?.getString(R.string.edit_screen_title)
            )
        )
    }
}