package com.thechance.whatschance.ui.main

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.ActivityWhatsChanceBinding
import com.thechance.whatschance.ui.authentication.AuthenticationActivity
import com.thechance.whatschance.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsChanceActivity : BaseActivity<ActivityWhatsChanceBinding>() {
    override val viewModel: WhatsChanceViewModel by viewModels()
    override val layoutIdActivity: Int = R.layout.activity_whats_chance

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.parseColor(viewModel.brandColor.value)
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(viewModel.brandColor.value)))
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(viewModel.brandColor.value)))
        setNavigationGraph()
    }

    private fun setNavigationGraph() {
        if (!viewModel.isUserLogin()) {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            this.finish()
        }

    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}