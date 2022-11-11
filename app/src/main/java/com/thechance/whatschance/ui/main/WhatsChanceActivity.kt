package com.thechance.whatschance.ui.main

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.thechance.whatschance.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsChanceActivity : AppCompatActivity() {
    private val viewModel: WhatsChanceViewModel by viewModels()

    companion object {
        var mainActivity: WhatsChanceActivity? = null

        fun getInstance(): WhatsChanceActivity? = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInstance()
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.parseColor(viewModel.homeColorUiState.value)
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