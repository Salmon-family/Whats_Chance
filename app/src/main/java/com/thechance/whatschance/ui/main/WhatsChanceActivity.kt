package com.thechance.whatschance.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.ActivityWhatsChanceBinding
import com.thechance.whatschance.ui.authentication.AuthenticationActivity
import com.thechance.whatschance.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsChanceActivity : BaseActivity<ActivityWhatsChanceBinding>() {
    override val viewModel: WhatsChanceViewModel by viewModels()
    override val layoutIdActivity: Int = R.layout.activity_whats_chance

    override fun onStart() {
        super.onStart()
        window.statusBarColor = Color.parseColor(viewModel.brandColor.value)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.parseColor(viewModel.brandColor.value)
        setNavigationGraph()
    }

    private fun setNavigationGraph() {
        if (!viewModel.isUserLogin()) {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            this.finish()
        }

    }
}