package com.sprill.habits.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sprill.habits.R
import com.sprill.habits.interfaces.HasCustomTitle


class AboutAppFragment : Fragment(), HasCustomTitle {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about_app, container, false)
    }

    override fun getTitleRes(): Int = R.string.about_app_screen_title

    companion object {
        fun newInstance() = AboutAppFragment()
    }
}