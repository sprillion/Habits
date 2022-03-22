package com.sprill.habits

import android.app.Application
import com.sprill.habits.models.Model

class App: Application() {
    val model = Model()
}