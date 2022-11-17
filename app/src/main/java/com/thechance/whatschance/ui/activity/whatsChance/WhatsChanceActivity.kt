package com.thechance.whatschance.ui.activity.whatsChance

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.ActivityWhatsChanceBinding
import com.thechance.whatschance.ui.activity.authentication.AuthenticationActivity
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.graph
        if (viewModel.isUserLogin()) {
            navGraph.setStartDestination(
                R.id.homeFragment
            )
            navController.graph = navGraph
        } else {
            startActivity(Intent(this, AuthenticationActivity::class.java))
            this.finish()
        }

    }
}