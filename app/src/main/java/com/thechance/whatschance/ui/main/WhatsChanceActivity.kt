package com.thechance.whatschance.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.thechance.whatschance.R
import com.thechance.whatschance.utils.changeStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsChanceActivity : AppCompatActivity() {
    private val viewModel: WhatsChanceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.changeStatusBarColor(viewModel.homeColorUiState.value.colorValueUi)
    }
}