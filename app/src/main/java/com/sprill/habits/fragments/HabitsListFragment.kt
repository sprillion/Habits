package com.sprill.habits.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sprill.habits.adapters.HabitsListAdapter
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.model.room.entities.ItemHabit
import com.sprill.habits.databinding.FragmentHabitsListBinding
import com.sprill.habits.factory
import com.sprill.habits.interfaces.IAdapterCallBack
import com.sprill.habits.viewModels.HabitListViewModel
import java.util.ArrayList

class HabitsListFragment : Fragment(), IAdapterCallBack {

    private lateinit var binding: FragmentHabitsListBinding
    private var typeHabits: Int = MainActivity.KEY_TYPE_GOOD
    private val viewModel: HabitListViewModel by viewModels { factory() }

    companion object {
        fun newInstance(typeHabits: Int) = HabitsListFragment().apply {
            arguments = Bundle().apply {
                putInt(MainActivity.BUNDLE_KEY_TYPE, typeHabits)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHabitsListBinding.inflate(inflater)

        arguments?.let { bundle ->
            typeHabits = bundle.getInt(MainActivity.BUNDLE_KEY_TYPE)
        }

        setObservers()

        return binding.root
    }

    private fun setObservers(){
        viewModel.habits.observe(activity as LifecycleOwner, Observer {
                habits ->
            setAdapter(habits)
        })
        viewModel.sortedHabits.observe(activity as LifecycleOwner, Observer {
                habits ->
            setAdapter(habits)
        })
    }

    private fun setAdapter(habits: List<ItemHabit>){
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