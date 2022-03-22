package com.sprill.habits.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.sprill.habits.MainActivity
import com.sprill.habits.R
import com.sprill.habits.adapters.ViewPagerAdapter
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.FragmentTypesViewPagerBinding
import com.sprill.habits.factory
import com.sprill.habits.viewModels.HabitListViewModel

class TypesViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentTypesViewPagerBinding
    private val viewModel: HabitListViewModel by viewModels{factory()}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTypesViewPagerBinding.inflate(layoutInflater)

        setAdapter()
        setTabLayout()
        setSearcher()
        setBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.load()
        viewModel.habits.observe(viewLifecycleOwner, Observer {
                habits ->
            setAdapter(habits)
        })
    }

    private fun setBinding(){
        binding.apply {
            fab.setOnClickListener{
                onFubPressed()
            }
            bottomSheetLayout.apply {

                filterIdDown.setOnClickListener{
                    viewModel.sortIdHabits(false)
                }
                filterIdUp.setOnClickListener{
                    viewModel.sortIdHabits(true)
                }
                filterPriorityDown.setOnClickListener{
                    viewModel.sortPriorityHabits(false)
                }
                filterPriorityUp.setOnClickListener{
                    viewModel.sortPriorityHabits(true)
                }
            }
        }
    }

    private fun onFubPressed(){
        findNavController().navigate(
            R.id.action_typesViewPagerFragment_to_createEditFragment,
            bundleOf(
                MainActivity.BUNDLE_KEY_ID to MainActivity.BUNDLE_KEY_ID_NULL,
                MainActivity.BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME to getString(R.string.create_screen_title)
            )
        )
    }

    private fun setAdapter(habits: ArrayList<ItemHabit> = arrayListOf()){
        binding.viewPager.adapter = activity?.let { ViewPagerAdapter(it, habits) }
    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, pos ->
            tab.text = when(pos){
                MainActivity.KEY_TYPE_GOOD ->{
                     getString(R.string.good_type)
                }
                MainActivity.KEY_TYPE_BAD ->{
                    getString(R.string.bad_type)
                }
                else -> {""}
            }
        }.attach()
    }

    private fun setSearcher(){
        binding.bottomSheetLayout.textSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchHabits(text)
            }
        })
    }

}

