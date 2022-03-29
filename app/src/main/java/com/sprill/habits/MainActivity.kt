package com.sprill.habits

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.sprill.habits.databinding.ActivityMainBinding
import com.sprill.habits.viewModels.HabitListViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var viewModel: HabitListViewModel

    companion object {
        const val BUNDLE_KEY_ID = "ID"
        const val BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME = "labelType"
        const val BUNDLE_KEY_TYPE = "TYPE"
        const val BUNDLE_KEY_ID_NULL = -1
        const val KEY_TYPE_GOOD = 0
        const val KEY_TYPE_BAD = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Repository.init(applicationContext)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HabitListViewModel(Repository.habitsRepository) as T
            }
        }).get(HabitListViewModel::class.java)

        binding.navigation.setNavigationItemSelectedListener(this)
        setNavigationMenu()
        navController = binding.fragmentContainer.getFragment<NavHostFragment>().navController
        setTitle()
    }

    private fun setTitle(){
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.habits_list_nav -> navController.popBackStack(R.id.typesViewPagerFragment, false)
            R.id.about_app_nav -> navController.navigate(R.id.aboutAppFragment)
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        setNavigationMenu()
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setNavigationMenu(){
        binding.navigation.setCheckedItem(R.id.habits_list_nav)
    }

    override fun onBackPressed() {
        setNavigationMenu()
        super.onBackPressed()
    }
}