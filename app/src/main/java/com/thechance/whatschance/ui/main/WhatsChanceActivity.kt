package com.thechance.whatschance.ui.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.ActivityWhatsChanceBinding
import com.thechance.whatschance.ui.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsChanceActivity : AppCompatActivity() {
    private val viewModel: WhatsChanceViewModel by viewModels()
    private lateinit var binding: ActivityWhatsChanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_whats_chance)
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