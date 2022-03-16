package com.sprill.habits

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.ActivityMainBinding
import com.sprill.habits.interfaces.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, Navigator{

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    companion object {
        const val BUNDLE_KEY_HABIT = "HABIT"
        const val BUNDLE_KEY_HABITS_LIST = "HABITS_LIST"
        const val BUNDLE_KEY_HABIT_RESULT = "HABIT_RESULT"
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
            R.id.habits_list_nav -> goToHabitsList()
            R.id.about_app_nav -> showAboutAppScreen()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    private fun setNavigationMenu(){
        binding.navigation.setCheckedItem(R.id.habits_list_nav)
    }

    override fun goBack() {
        setNavigationMenu()
        navController.popBackStack()
    }

    override fun goToHabitsList() {
        navController.popBackStack(R.id.typesViewPagerFragment, false)
    }

    override fun onBackPressed() {
        setNavigationMenu()
        super.onBackPressed()
    }

    override fun showCreateScreen() {
        launchDestination(
            R.id.createEditFragment,
            bundleOf(
                BUNDLE_KEY_HABIT to null,
                BUNDLE_KEY_ID to BUNDLE_KEY_ID_NULL,
                BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME to getString(R.string.create_screen_title)
            )
        )
    }

    override fun showEditScreen(itemHabit: ItemHabit, idItem: Int) {
        launchDestination(
            R.id.createEditFragment,
            bundleOf(
                BUNDLE_KEY_HABIT to itemHabit,
                BUNDLE_KEY_ID to idItem,
                BUNDLE_KEY_CREATE_EDIT_SCREEN_NAME to getString(R.string.edit_screen_title)
            )
        )
    }

    override fun showAboutAppScreen() {
        launchDestination(R.id.aboutAppFragment)
    }

    private fun launchDestination(destinationId: Int, args: Bundle? = null) {
        navController.navigate(destinationId, args)
    }

}