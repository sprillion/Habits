package com.sprill.habits

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.sprill.habits.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val BUNDLE_KEY_HABITS = "habits"
        const val BUNDLE_KEY_ID = "id"
        const val BUNDLE_KEY_IS_NEW = "isNew"
    }

    private var habits: ArrayList<ItemHabit> = arrayListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener{
            openCreateActivityForResult()
        }
        setAdapter()
    }

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val id = result.data!!.getIntExtra(BUNDLE_KEY_ID, -1)
            val habit = result.data?.getSerializableExtra(BUNDLE_KEY_HABITS) as ItemHabit
            if (id == -1)
                habits.add(habit)
            else
                habits[id] = habit
            setAdapter()
        }
    }

    fun openCreateActivityForResult() {
        val intent = Intent(this, CreateEditActivity::class.java).apply {
            putExtra(BUNDLE_KEY_IS_NEW, true)
        }
        resultLauncher.launch(intent)
    }

    fun setAdapter(){
        binding.recycler.adapter = Adapter(habits, this, resultLauncher)
    }

    //Test
}