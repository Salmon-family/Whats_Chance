package com.thechance.whatschance.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thechance.whatschance.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        var mainActivity: MainActivity? = null

        fun getInstance(): MainActivity? = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInstance()
        setTheme(R.style.Theme_WhatsChance)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        mainActivity = this
    }

    override fun onRestart() {
        super.onRestart()
        mainActivity = this
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity = null
    }
}