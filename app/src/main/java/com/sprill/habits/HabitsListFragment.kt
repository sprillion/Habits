package com.sprill.habits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sprill.habits.databinding.FragmentHabitsListBinding

class HabitsListFragment : Fragment() {

    private lateinit var binding: FragmentHabitsListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHabitsListBinding.inflate(inflater)
        binding.fab.setOnClickListener{
            openCreateFragment()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }
    companion object {
        fun newInstance() = HabitsListFragment()
    }

    private fun openCreateFragment(){
        (activity as MainActivity).supportFragmentManager
            .beginTransaction()
            .add(R.id.main_layout, CreateEditFragment.newInstance())
            .commit()

        (activity as MainActivity).supportFragmentManager
            .beginTransaction()
            .hide(this)
            .commit()
    }

    fun setAdapter(){
        binding.recycler.adapter = Adapter(MainActivity.habits, context, activity, this)
    }

}