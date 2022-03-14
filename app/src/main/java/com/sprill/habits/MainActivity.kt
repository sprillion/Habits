package com.sprill.habits

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.navigation.NavigationView
import com.sprill.habits.data.HabitResult
import com.sprill.habits.data.ItemHabit
import com.sprill.habits.databinding.ActivityMainBinding
import com.sprill.habits.fragments.AboutAppFragment
import com.sprill.habits.fragments.CreateEditFragment
import com.sprill.habits.fragments.TypesViewPagerFragment
import com.sprill.habits.interfaces.*

class MainActivity : AppCompatActivity(), Navigator, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val BUNDLE_KEY_HABIT = "habit"
        const val BUNDLE_KEY_HABITS_LIST = "habits_list"
        const val BUNDLE_KEY_HABIT_RESULT = "habit_result"
        const val BUNDLE_KEY_ID = "id"
        const val KEY_TYPE_GOOD = 0
        const val KEY_TYPE_BAD = 1
    }

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateTitle()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigation.setNavigationItemSelectedListener(this)
        setNavigationMenu()

        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, TypesViewPagerFragment.newInstance())
                    .commit()
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }
    override fun goBack() {
        onBackPressed()
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        setNavigationMenu()
    }

    override fun showCreateScreen() {
        launchFragment(CreateEditFragment())
    }

    override fun showEditScreen(itemHabit: ItemHabit, idItem: Int) {
        launchFragment(CreateEditFragment.newInstance(itemHabit, idItem))
    }

    override fun showAboutAppScreen() {
        launchFragment(AboutAppFragment.newInstance())
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    override fun publishResult(result: HabitResult) {
        supportFragmentManager.setFragmentResult(result.javaClass.name, bundleOf(BUNDLE_KEY_HABIT_RESULT to result))
    }

    override fun listenResult(
        habitResultClass: Class<HabitResult>,
        owner: LifecycleOwner,
        listener: ResultListener<HabitResult>
    ) {
        supportFragmentManager.setFragmentResultListener(habitResultClass.name, owner, FragmentResultListener { key, bundle ->
            listener.invoke(bundle.getParcelable(BUNDLE_KEY_HABIT_RESULT)!!)
        })
    }

    private fun updateTitle(){
        val fragment = currentFragment
        title = if (fragment is HasCustomTitle) {
            getString(fragment.getTitleRes())
        } else {
            getString(R.string.app_name)
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        else {
            super.onBackPressed()
            setNavigationMenu()
        }
    }

    private fun setNavigationMenu(){
        binding.navigation.setCheckedItem(R.id.habits_list_nav)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.habits_list_nav -> goToMenu()
            R.id.about_app_nav -> showAboutAppScreen()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}