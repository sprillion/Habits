package com.sprill.habits.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.sprill.habits.adapters.HabitsListAdapter
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.model.retrofit.ItemHabit
import com.sprill.habits.databinding.FragmentHabitsListBinding
import com.sprill.habits.factory
import com.sprill.habits.interfaces.IAdapterCallBack
import com.sprill.habits.viewModels.HabitListViewModel

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

    //@SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun setObservers(){
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.habits.collect{ habits ->
//                    setAdapter(habits)
//                }
//            }
//        }
        viewModel.habits.observe(viewLifecycleOwner, Observer {
                habits ->
            setAdapter(habits)
        })
//        viewModel.sortedHabits.observe(activity as LifecycleOwner, Observer {
//                habits ->
//            setAdapter(habits)
//        })
    }

    private fun setAdapter(habits: List<ItemHabit>){
        binding.recycler.adapter = HabitsListAdapter(habits, typeHabits, this, context)
    }

    override fun onItemClicked(idItem: String) {
        findNavController().navigate(
            R.id.action_typesViewPagerFragment_to_createEditFragment,
            bundleOf(
                MainActivity.BUNDLE_KEY_ID to idItem,
                MainActivity.BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME to context?.getString(R.string.edit_screen_title)
            )
        )
    }
}