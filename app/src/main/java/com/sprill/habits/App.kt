package com.sprill.habits

import android.app.Application
import com.sprill.habits.model.AppModel
import com.sprill.habits.model.Model

class App: Application() {
    //val model = Model()
    val model = AppModel(applicationContext)
}