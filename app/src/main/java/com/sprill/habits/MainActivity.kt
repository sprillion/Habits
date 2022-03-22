package com.sprill.habits

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.sprill.habits.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    companion object {
        const val BUNDLE_KEY_HABITS = "HABITS"
        const val BUNDLE_KEY_HABIT = "HABIT"
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

    private fun setNavigationMenu(){
        binding.navigation.setCheckedItem(R.id.habits_list_nav)
    }

    override fun onBackPressed() {
        setNavigationMenu()
        super.onBackPressed()
    }
}